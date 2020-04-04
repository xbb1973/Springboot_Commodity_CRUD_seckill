package slf.xbb.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import slf.xbb.controller.view.OrderVo;
import slf.xbb.dao.OrderDoMapper;
import slf.xbb.dao.SequenceDoMapper;
import slf.xbb.domain.OrderDo;
import slf.xbb.domain.SequenceDo;
import slf.xbb.error.BussinessException;
import slf.xbb.error.EmBusinessError;
import slf.xbb.service.ItemService;
import slf.xbb.service.OrderService;
import slf.xbb.service.UserService;
import slf.xbb.service.model.ItemModel;
import slf.xbb.service.model.OrderModel;
import slf.xbb.service.model.UserModel;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/4 7:41 上午
 * @description：订单服务实现
 * @modifiedBy：
 * @version:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDoMapper orderDoMapper;

    @Autowired
    private SequenceDoMapper sequenceDoMapper;

    /**
     * 创建订单，对应的请求体需要参数为：用户id 商品id 购买数量
     *
     * @param userId
     * @param itemId
     * @param amount
     * @return
     */
    @Transactional
    @Override
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BussinessException {
        // 1、检验下单状态，下单的商品是否存在，用户是否合法，购买数量是否正确
        UserModel userModel = userService.getUserById(userId);
        ItemModel itemModel = itemService.getItemById(itemId);
        if (userModel == null || itemModel == null || (amount <= 0 || amount > 99)) {
            throw new BussinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "商品/用户信息/购买数量：参数异常");
        }

        // 2、落单减库存 / 支付减库存（支付减库存会出现超卖的问题）
        boolean hasDecreaseStock = itemService.decreaseStock(itemId, amount);
        if (!hasDecreaseStock) {
            throw new BussinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        // 3、订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setItemId(itemId);
        orderModel.setUserId(userId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));

        // 生成交易流水号，这里的order没有定义自增id
        OrderDo orderDo = new OrderDo();
        try {
            orderModel.setId(generateOrderNo());
            orderDo = convertFromOrderModelToOrderDo(orderModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderDoMapper.insertSelective(orderDo);
        // 加上商品销量
        itemService.increseSales(itemId, amount);
        // 4、返回前端

        return orderModel;
    }


    /**
     * 订单号有16位：
     * 1、前8位为时间信息，年月日   （当数据太多以时间纬度归档）
     * 2、中间6位为自增序列          （保证订单号不重复）
     * 3、最后2位为分库分表位         （）
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo() {
        // 订单号有16位：
        StringBuilder stringBuilder = new StringBuilder();
        // 1、前8位为时间信息，年月日   （当数据太多以时间纬度归档）
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        stringBuilder.append(nowDate);
        // 2、中间6位为自增序列          （保证订单号不重复）
        // mysql和orcal不同，因此建立sequence_info表来实现自增序列
        // 需要实现sequence_info的MBG
        int sequence = 0;
        // sequence要保证唯一，因此需要使用行级锁
        SequenceDo sequenceDo = sequenceDoMapper.getSequenceByName("order_info");
        sequence = sequenceDo.getCurrentValue();
        sequenceDo.setCurrentValue(sequenceDo.getCurrentValue() + sequenceDo.getStep());
        sequenceDoMapper.updateByPrimaryKeySelective(sequenceDo);

        stringBuilder.append(String.format("%06d", sequence));

        // 3、最后2位为分库分表位         （）
        // 暂时写死
        stringBuilder.append("00");
        // Integer userId = 1000122;
        // 分库分表位 = userId % 100;
        return stringBuilder.toString();
    }


    private OrderDo convertFromOrderModelToOrderDo(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderDo orderDo = new OrderDo();
        BeanUtils.copyProperties(orderModel, orderDo);
        return orderDo;
    }

}
