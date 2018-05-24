package com.stormfives.ocpay.common.entity;

/**
 * FCM 消息请求
 * Created by tlw on 2017/4/17.
 */
public class FCMRequest {
    private String to;  //目标
    private String notificationTitle;   //弹窗通知标题
    private String notificationBody;    //弹窗通知窗体
    private String notificationSound;   //声音 firebase属性
    private String dataType;         //消息类型 0正常  1跳转H5    2跳转到指定页面  3弹窗显示活动, 4-最新的闪送订单
    private String dataTitle;       //消息标题
    private String dataContent;      //消息内容
    private String dataViewControllerType;  //指定页面
    private String dataWebUrl;      //H5连接
    private String dataImageUrl;    //弹窗显示活动 图片链接
    private String dataSaveMesaage;     //当前消息是否需要保存到本地 非必填 默认保存 0保存 1不保存
    private String dataTemplateType;    //模板类型
    private String dataSound;           //声音 自定义属性
    private String priority;            //优先级
    private String dataActivityTitle;  //活动标题
    private String dataActivityContent;  //活动内容
    private String dataNewDeliveryOrder;  //最新的闪送订单


    public FCMRequest() {
    }

    public FCMRequest(String to, String notificationTitle, String notificationBody, String dataType, String dataTitle, String dataContent) {
        this.to = to;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.dataType = dataType;
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
    }

    @Deprecated
    public FCMRequest(String to, String notificationTitle, String dataType, String dataTitle, String dataContent) {
        this.to = to;
        this.notificationTitle = notificationTitle;
        this.dataType = dataType;
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
    }

    public FCMRequest(String to, String notificationTitle, String dataType, String dataTitle, String dataContent, String dataViewControllerType, String priority) {
        this.to = to;
        this.notificationTitle = notificationTitle;
        this.dataType = dataType;
        this.dataTitle = dataTitle;
        this.dataContent = dataContent;
        this.dataViewControllerType = dataViewControllerType;
        this.priority = priority;
    }

    public String getDataSound() {
        return dataSound;
    }

    public void setDataSound(String dataSound) {
        this.dataSound = dataSound;
    }

    public String getNotificationSound() {
        return notificationSound;
    }

    public void setNotificationSound(String notificationSound) {
        this.notificationSound = notificationSound;
    }

    public String getDataActivityContent() {
        return dataActivityContent;
    }

    public void setDataActivityContent(String dataActivityContent) {
        this.dataActivityContent = dataActivityContent;
    }

    public String getDataTemplateType() {
        return dataTemplateType;
    }

    public void setDataTemplateType(String dataTemplateType) {
        this.dataTemplateType = dataTemplateType;
    }

    public String getDataSaveMesaage() {
        return dataSaveMesaage;
    }

    public void setDataSaveMesaage(String dataSaveMesaage) {
        this.dataSaveMesaage = dataSaveMesaage;
    }

    public String getDataWebUrl() {
        return dataWebUrl;
    }

    public void setDataWebUrl(String dataWebUrl) {
        this.dataWebUrl = dataWebUrl;
    }

    public String getDataImageUrl() {
        return dataImageUrl;
    }

    public void setDataImageUrl(String dataImageUrl) {
        this.dataImageUrl = dataImageUrl;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public String getDataViewControllerType() {
        return dataViewControllerType;
    }

    public void setDataViewControllerType(String dataViewControllerType) {
        this.dataViewControllerType = dataViewControllerType;
    }
    @Deprecated
    public void setViewControllerType(String dataViewControllerType) {
        this.dataViewControllerType = dataViewControllerType;
    }
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDataActivityTitle() {
        return dataActivityTitle;
    }

    public void setDataActivityTitle(String dataActivityTitle) {
        this.dataActivityTitle = dataActivityTitle;
    }

    public String getDataNewDeliveryOrder() {
        return dataNewDeliveryOrder;
    }

    public void setDataNewDeliveryOrder(String dataNewDeliveryOrder) {
        this.dataNewDeliveryOrder = dataNewDeliveryOrder;
    }
}
