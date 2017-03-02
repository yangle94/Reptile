package com.zs5s.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 极光发送通知
 * Created by Angle on 2017/2/27.
 */
public class UserIdCodeDTO implements Serializable{
    private int id;

    /**
     * 内部用户id
     */
    private String userId;

    /**
     * 用户对应极光推送的ID
     */
    private String identifyingCode;
    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 删除标识（0 未删除， 1 已删除）
     */
    private int deleteFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentifyingCode() {
        return identifyingCode;
    }

    public void setIdentifyingCode(String identifyingCode) {
        this.identifyingCode = identifyingCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public UserIdCodeDTO() {
    }

    public UserIdCodeDTO(String userId, String identifyingCode) {
        this.userId = userId;
        this.identifyingCode = identifyingCode;
    }

    @Override
    public String toString() {
        return "UserIdCodeDTO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", identifyingCode='" + identifyingCode + '\'' +
                ", creatTime=" + creatTime +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
