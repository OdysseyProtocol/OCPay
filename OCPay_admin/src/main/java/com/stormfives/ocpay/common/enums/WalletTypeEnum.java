package com.stormfives.ocpay.common.enums;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/16
 * Time: 下午3:01
 */
public enum WalletTypeEnum {

    RECHARGE(1,"充值"),
    GAS(2,"gas"),
    WITHDRAW(3,"提现");



    private Integer type;

    private String typeString;

    WalletTypeEnum(Integer type, String typeString) {
        this.type = type;
        this.typeString = typeString;
    }

    public static String getTypeString(int index) {
        for (WalletTypeEnum a : WalletTypeEnum.values()) {
            if (a.getType() == index) {
                return a.typeString;
            }
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeString() {
        return typeString;
    }

    public void setTypeString(String typeString) {
        this.typeString = typeString;
    }
}
