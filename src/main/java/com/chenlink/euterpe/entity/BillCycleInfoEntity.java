package com.chenlink.euterpe.entity;


/**
 * 账单解析类
 * @author chenlink
 * @version 1.0, 2018-05-08
 */

public class BillCycleInfoEntity {

    private int id;//主键
    private String idCard;//身份证号
    private	String subject;//邮件主题
    private	String senderUrl;//用户发件人的邮箱
    private	String receiveAddUrl;//用户收件人的邮箱
    private String payments;//本期应还款额
    private String creditLine;//信用额度
    private String paymentsDate;//还款日
    private String creditNumber;//信用卡卡号
    private	String infoSource;//邮箱信息来源
    private String bankName;//银行名称
    private String sendTime;//发送时间
    private int isSuccess;//银行解析是否成功 0：是1：否 2:不支持的银行解析 3：邮件解析抛出异常
    private String errorInfo;//解析失败原因
    private String ErrorContent;//失败时邮箱邮件内容存放路径

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getErrorContent() {
        return ErrorContent;
    }

    public void setErrorContent(String errorContent) {
        ErrorContent = errorContent;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSenderUrl() {
        return senderUrl;
    }

    public void setSenderUrl(String senderUrl) {
        this.senderUrl = senderUrl;
    }

    public String getReceiveAddUrl() {
        return receiveAddUrl;
    }

    public void setReceiveAddUrl(String receiveAddUrl) {
        this.receiveAddUrl = receiveAddUrl;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public String getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(String paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BillCycleInfoEntity{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", senderUrl='" + senderUrl + '\'' +
                ", receiveAddUrl='" + receiveAddUrl + '\'' +
                ", payments='" + payments + '\'' +
                ", creditLine='" + creditLine + '\'' +
                ", paymentsDate='" + paymentsDate + '\'' +
                ", creditNumber='" + creditNumber + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", bankName='" + bankName + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", isSuccess=" + isSuccess +
                ", errorInfo='" + errorInfo + '\'' +
                ", ErrorContent='" + ErrorContent + '\'' +
                '}';
    }
}
