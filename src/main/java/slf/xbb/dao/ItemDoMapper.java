package slf.xbb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import slf.xbb.domain.ItemDo;
import slf.xbb.domain.ItemDoExample;

public interface ItemDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    long countByExample(ItemDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int deleteByExample(ItemDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int insert(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int insertSelective(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    List<ItemDo> selectByExample(ItemDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    ItemDo selectByPrimaryKey(Integer id);

    /**
     * 获取所有的items order by sales desc
     *
     * @return
     */
    List<ItemDo> listItem();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByExampleSelective(@Param("record") ItemDo record, @Param("example") ItemDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByExample(@Param("record") ItemDo record, @Param("example") ItemDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByPrimaryKeySelective(ItemDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Thu Apr 02 09:42:15 CST 2020
     */
    int updateByPrimaryKey(ItemDo record);

    int increseSales(@Param("id") Integer id, @Param("amount") Integer amount);
}