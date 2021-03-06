package slf.xbb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import slf.xbb.domain.ItemStockDo;
import slf.xbb.domain.ItemStockDoExample;

public interface ItemStockDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    long countByExample(ItemStockDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int deleteByExample(ItemStockDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int insert(ItemStockDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int insertSelective(ItemStockDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    List<ItemStockDo> selectByExample(ItemStockDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    ItemStockDo selectByPrimaryKey(Integer id);

    /**
     * 根据商品item_id获取商品库存
     *
     * @param id
     * @return
     */
    ItemStockDo selectByItemId(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int updateByExampleSelective(@Param("record") ItemStockDo record, @Param("example") ItemStockDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int updateByExample(@Param("record") ItemStockDo record, @Param("example") ItemStockDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int updateByPrimaryKeySelective(ItemStockDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item_stock
     *
     * @mbg.generated Fri Apr 03 19:08:00 CST 2020
     */
    int updateByPrimaryKey(ItemStockDo record);

    /**
     * 削减库存
     * @param itemId
     * @param amount
     * @return
     */
    int decreaseStock(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}