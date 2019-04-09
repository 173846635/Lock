package com.dhy.yycompany.lock.bean;

public class KeyInfo {
    private Integer kId;

    private String kUuid;

    private Integer kLockId;

    private Integer kUserId;

    private String kPassword;

    private Integer kAvailableTimes;

    private Integer kIsModify;

    private Integer kDelete;

    private String kFailureTime;

    private String userName;//创建者

    private int type;//密码类型 1.永久型，2.时限型，3.次限制

    private String typeStr;//密码类型Str

    private int status;//密码状态 1.（使用中|时限内），2.以失效|以过期，0.剩X次

    private String statusStr;//密码状态Str

    public Integer getkId() {
        return kId;
    }

    public void setkId(Integer kId) {
        this.kId = kId;
    }

    public String getkUuid() {
        return kUuid;
    }

    public void setkUuid(String kUuid) {
        this.kUuid = kUuid == null ? null : kUuid.trim();
    }

    public Integer getkLockId() {
        return kLockId;
    }

    public void setkLockId(Integer kLockId) {
        this.kLockId = kLockId;
    }

    public Integer getkUserId() {
        return kUserId;
    }

    public void setkUserId(Integer kUserId) {
        this.kUserId = kUserId;
    }

    public String getkPassword() {
        return kPassword;
    }

    public void setkPassword(String kPassword) {
        this.kPassword = kPassword == null ? null : kPassword.trim();
    }

    public Integer getkAvailableTimes() {
        return kAvailableTimes;
    }

    public void setkAvailableTimes(Integer kAvailableTimes) {
        this.kAvailableTimes = kAvailableTimes;
    }

    public Integer getkIsModify() {
        return kIsModify;
    }

    public void setkIsModify(Integer kIsModify) {
        this.kIsModify = kIsModify;
    }

    public Integer getkDelete() {
        return kDelete;
    }

    public void setkDelete(Integer kDelete) {
        this.kDelete = kDelete;
    }

    public String getkFailureTime() {
        return kFailureTime;
    }

    public void setkFailureTime(String kFailureTime) {
        this.kFailureTime = kFailureTime == null ? null : kFailureTime.trim();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "KeyInfo{" +
                "kId=" + kId +
                ", kUuid='" + kUuid + '\'' +
                ", kLockId=" + kLockId +
                ", kUserId=" + kUserId +
                ", kPassword='" + kPassword + '\'' +
                ", kAvailableTimes=" + kAvailableTimes +
                ", kIsModify=" + kIsModify +
                ", kDelete=" + kDelete +
                ", kFailureTime='" + kFailureTime + '\'' +
                ", type=" + type +
                ", typeStr='" + typeStr + '\'' +
                ", status=" + status +
                ", statusStr='" + statusStr + '\'' +
                '}';
    }
}