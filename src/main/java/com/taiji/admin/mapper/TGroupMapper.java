package com.taiji.admin.mapper;

import com.taiji.msg.model.TGroup;
import com.taiji.msg.model.TGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TGroupMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    long countByExample(TGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int deleteByExample(TGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int insert(TGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int insertSelective(TGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    List<TGroup> selectByExample(TGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    TGroup selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TGroup record, @Param("example") TGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TGroup record, @Param("example") TGroupExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TGroup record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TGroup record);
}