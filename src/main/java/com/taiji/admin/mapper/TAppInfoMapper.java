package com.taiji.admin.mapper;

import com.taiji.app.model.TAppInfo;
import com.taiji.app.model.TAppInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAppInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    long countByExample(TAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int deleteByExample(TAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int insert(TAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int insertSelective(TAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    List<TAppInfo> selectByExample(TAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    TAppInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TAppInfo record, @Param("example") TAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TAppInfo record, @Param("example") TAppInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TAppInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_app_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TAppInfo record);

	List<TAppInfo> members();
}