package com.taiji.admin.model;

import java.util.Date;

public class SLoginInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.last_update
     *
     * @mbg.generated
     */
    private String lastUpdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.fail_num
     *
     * @mbg.generated
     */
    private Integer failNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.is_lock
     *
     * @mbg.generated
     */
    private String isLock;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.lock_time
     *
     * @mbg.generated
     */
    private String lockTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.del_flg
     *
     * @mbg.generated
     */
    private String delFlg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.create_by
     *
     * @mbg.generated
     */
    private Integer createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.update_by
     *
     * @mbg.generated
     */
    private Integer updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_login_info.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.user_id
     *
     * @return the value of s_login_info.user_id
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.user_id
     *
     * @param userId the value for s_login_info.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.last_update
     *
     * @return the value of s_login_info.last_update
     *
     * @mbg.generated
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.last_update
     *
     * @param lastUpdate the value for s_login_info.last_update
     *
     * @mbg.generated
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate == null ? null : lastUpdate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.fail_num
     *
     * @return the value of s_login_info.fail_num
     *
     * @mbg.generated
     */
    public Integer getFailNum() {
        return failNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.fail_num
     *
     * @param failNum the value for s_login_info.fail_num
     *
     * @mbg.generated
     */
    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.is_lock
     *
     * @return the value of s_login_info.is_lock
     *
     * @mbg.generated
     */
    public String getIsLock() {
        return isLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.is_lock
     *
     * @param isLock the value for s_login_info.is_lock
     *
     * @mbg.generated
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock == null ? null : isLock.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.lock_time
     *
     * @return the value of s_login_info.lock_time
     *
     * @mbg.generated
     */
    public String getLockTime() {
        return lockTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.lock_time
     *
     * @param lockTime the value for s_login_info.lock_time
     *
     * @mbg.generated
     */
    public void setLockTime(String lockTime) {
        this.lockTime = lockTime == null ? null : lockTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.del_flg
     *
     * @return the value of s_login_info.del_flg
     *
     * @mbg.generated
     */
    public String getDelFlg() {
        return delFlg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.del_flg
     *
     * @param delFlg the value for s_login_info.del_flg
     *
     * @mbg.generated
     */
    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg == null ? null : delFlg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.create_by
     *
     * @return the value of s_login_info.create_by
     *
     * @mbg.generated
     */
    public Integer getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.create_by
     *
     * @param createBy the value for s_login_info.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.create_date
     *
     * @return the value of s_login_info.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.create_date
     *
     * @param createDate the value for s_login_info.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.update_by
     *
     * @return the value of s_login_info.update_by
     *
     * @mbg.generated
     */
    public Integer getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.update_by
     *
     * @param updateBy the value for s_login_info.update_by
     *
     * @mbg.generated
     */
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.update_date
     *
     * @return the value of s_login_info.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.update_date
     *
     * @param updateDate the value for s_login_info.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_login_info.remark
     *
     * @return the value of s_login_info.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_login_info.remark
     *
     * @param remark the value for s_login_info.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
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
        SLoginInfo other = (SLoginInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLastUpdate() == null ? other.getLastUpdate() == null : this.getLastUpdate().equals(other.getLastUpdate()))
            && (this.getFailNum() == null ? other.getFailNum() == null : this.getFailNum().equals(other.getFailNum()))
            && (this.getIsLock() == null ? other.getIsLock() == null : this.getIsLock().equals(other.getIsLock()))
            && (this.getLockTime() == null ? other.getLockTime() == null : this.getLockTime().equals(other.getLockTime()))
            && (this.getDelFlg() == null ? other.getDelFlg() == null : this.getDelFlg().equals(other.getDelFlg()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_login_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLastUpdate() == null) ? 0 : getLastUpdate().hashCode());
        result = prime * result + ((getFailNum() == null) ? 0 : getFailNum().hashCode());
        result = prime * result + ((getIsLock() == null) ? 0 : getIsLock().hashCode());
        result = prime * result + ((getLockTime() == null) ? 0 : getLockTime().hashCode());
        result = prime * result + ((getDelFlg() == null) ? 0 : getDelFlg().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}