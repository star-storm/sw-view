package com.taiji.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiji.admin.model.TFile;
import com.taiji.admin.model.TFileExample;

public interface TFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    long countByExample(TFileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int deleteByExample(TFileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int insert(TFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int insertSelective(TFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    List<TFile> selectByExample(TFileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    TFile selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TFile record, @Param("example") TFileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TFile record, @Param("example") TFileExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TFile record);
    
    int delFile(Integer id);
    
    int activeFile(Integer id);
}