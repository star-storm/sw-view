package com.taiji.admin.model;

import java.io.Serializable;
import java.util.Date;

public class TFile implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9072145276691024151L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.ori_name
     *
     * @mbg.generated
     */
    private String oriName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.type
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.path
     *
     * @mbg.generated
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.del_flg
     *
     * @mbg.generated
     */
    private String delFlg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.create_by
     *
     * @mbg.generated
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.update_by
     *
     * @mbg.generated
     */
    private Integer updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_file.remark
     *
     * @mbg.generated
     */
    private String remark;

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.id
     *
     * @return the value of t_file.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.id
     *
     * @param id the value for t_file.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.name
     *
     * @return the value of t_file.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.name
     *
     * @param name the value for t_file.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.ori_name
     *
     * @return the value of t_file.ori_name
     *
     * @mbg.generated
     */
    public String getOriName() {
        return oriName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.ori_name
     *
     * @param oriName the value for t_file.ori_name
     *
     * @mbg.generated
     */
    public void setOriName(String oriName) {
        this.oriName = oriName == null ? null : oriName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.type
     *
     * @return the value of t_file.type
     *
     * @mbg.generated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.type
     *
     * @param type the value for t_file.type
     *
     * @mbg.generated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.path
     *
     * @return the value of t_file.path
     *
     * @mbg.generated
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.path
     *
     * @param path the value for t_file.path
     *
     * @mbg.generated
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.del_flg
     *
     * @return the value of t_file.del_flg
     *
     * @mbg.generated
     */
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.del_flg
     *
     * @param delFlg the value for t_file.del_flg
     *
     * @mbg.generated
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.create_by
     *
     * @return the value of t_file.create_by
     *
     * @mbg.generated
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.create_by
     *
     * @param createBy the value for t_file.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.create_date
     *
     * @return the value of t_file.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.create_date
     *
     * @param createDate the value for t_file.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.update_by
     *
     * @return the value of t_file.update_by
     *
     * @mbg.generated
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.update_by
     *
     * @param updateBy the value for t_file.update_by
     *
     * @mbg.generated
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.update_date
     *
     * @return the value of t_file.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.update_date
     *
     * @param updateDate the value for t_file.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_file.remark
     *
     * @return the value of t_file.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_file.remark
     *
     * @param remark the value for t_file.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TFile other = (TFile) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getOriName() == null ? other.getOriName() == null : this.getOriName().equals(other.getOriName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getDelFlg() == null ? other.getDelFlg() == null : this.getDelFlg().equals(other.getDelFlg()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_file
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOriName() == null) ? 0 : getOriName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getDelFlg() == null) ? 0 : getDelFlg().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}