package com.stormfives.ocpay.account.controller.req;

/**
 * Created with IntelliJ IDEA.
 * User: lyc
 * Date: 2018/3/14
 * Time: 下午3:51
 */
public class AdminReq {

    private Integer pageNum=1;

    private String password;

    private String phone;

    private String name;

    private String realName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
