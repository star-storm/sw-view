package com.taiji.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiji.admin.model.SUser;
import com.taiji.admin.model.SUserExample;

public interface SUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    long countByExample(SUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int deleteByExample(SUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int insert(SUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int insertSelective(SUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    List<SUser> selectByExample(SUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    SUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SUser record, @Param("example") SUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SUser record, @Param("example") SUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SUser record);
    
    /**
     * 参数联合查询
     * @param sUser
     * @return
     */
    List<SUser> selectByUser(SUser sUser);

    /**
	 * 用户所有信息：基本信息，角色，权限等
	 * @param uid
	 * @return
	 */
	SUser selectUserFullMsg(Integer id);
	
	/**
	 * 用户所有信息：基本信息，角色，权限等
	 * @param uid
	 * @return
	 */
	SUser selectByUserCode(String userCode);
    
    int updateByUserCodeSelective(SUser record);

	SUser selectUserByLoginName(String loginName);
	
	List<SUser> selectUserByName(String name);

	List<SUser> allUserInfo();
}