package com.dhy.yycompany.lock.bean;

public class DailyRecordInfo {
    private Integer dId;

    private String dUuid;

    private Integer dUserId;

    private Integer dLockId;

    private String dTime;

    private String userName;//开门者名

    private String identity;//开门者身份

    private String phone;//联系方式

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdUuid() {
        return dUuid;
    }

    public void setdUuid(String dUuid) {
        this.dUuid = dUuid == null ? null : dUuid.trim();
    }

    public Integer getdUserId() {
        return dUserId;
    }

    public void setdUserId(Integer dUserId) {
        this.dUserId = dUserId;
    }

    public Integer getdLockId() {
        return dLockId;
    }

    public void setdLockId(Integer dLockId) {
        this.dLockId = dLockId;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime == null ? null : dTime.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}