package com.chenlink.euterpe.entity;

public class MobileOperatorEntity {

    private int id;
    private String idcard;
    private String phone;
    private String usernumber;//运营商手机号
    private String mobiletype;//运营商类型
    private String cycledate;//账单日期
    private Double allfee;//消费总额
    private String custname;//用户姓名
    private String custlvl;//用户级别
    private String certnum;//运营商中身份证号
    private String remark;
    private String def0;
    private String def1;
    private String def2;
    private String def3;
    private String def4;
    private int iserror;
    private String reason;
    private String createtime;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getIserror() {
        return iserror;
    }

    public void setIserror(int iserror) {
        this.iserror = iserror;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCycledate() {
        return cycledate;
    }

    public void setCycledate(String cycledate) {
        this.cycledate = cycledate;
    }

    public Double getAllfee() {
        return allfee;
    }

    public void setAllfee(Double allfee) {
        this.allfee = allfee;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustlvl() {
        return custlvl;
    }

    public void setCustlvl(String custlvl) {
        this.custlvl = custlvl;
    }

    public String getCertnum() {
        return certnum;
    }

    public void setCertnum(String certnum) {
        this.certnum = certnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDef0() {
        return def0;
    }

    public void setDef0(String def0) {
        this.def0 = def0;
    }

    public String getDef1() {
        return def1;
    }

    public void setDef1(String def1) {
        this.def1 = def1;
    }

    public String getDef2() {
        return def2;
    }

    public void setDef2(String def2) {
        this.def2 = def2;
    }

    public String getDef3() {
        return def3;
    }

    public void setDef3(String def3) {
        this.def3 = def3;
    }

    public String getDef4() {
        return def4;
    }

    public void setDef4(String def4) {
        this.def4 = def4;
    }
}
