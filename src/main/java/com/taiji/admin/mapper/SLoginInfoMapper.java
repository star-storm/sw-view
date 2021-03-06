package com.taiji.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.taiji.admin.model.SLoginInfo;
import com.taiji.admin.model.SLoginInfoExample;

public interface SLoginInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    long countByExample(SLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int deleteByExample(SLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int insert(SLoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int insertSelective(SLoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    List<SLoginInfo> selectByExample(SLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    SLoginInfo selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SLoginInfo record, @Param("example") SLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SLoginInfo record, @Param("example") SLoginInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SLoginInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SLoginInfo record);
    
    SLoginInfo selectByLoginName(String loginName);
}