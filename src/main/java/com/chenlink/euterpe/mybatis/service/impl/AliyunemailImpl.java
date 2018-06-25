package com.chenlink.euterpe.mybatis.service.impl;

import com.chenlink.euterpe.entity.BillCycleInfoEntity;
import com.chenlink.euterpe.mybatis.mapper.BillCycleMapper;
import com.chenlink.euterpe.mybatis.service.EmailTemplate;
import com.chenlink.euterpe.util.DateUtil;
import com.chenlink.euterpe.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Component
public class AliyunemailImpl implements EmailTemplate {

    private static final Logger logger = Logger.getLogger(AliyunemailImpl.class);

    @Autowired
    private BillCycleMapper bm;

    @Override
    public JSONObject mailAnaly(String[] sts, String idcard)throws Exception {
        BillCycleInfoEntity bcie = new BillCycleInfoEntity();
        List<BillCycleInfoEntity> listbills = new ArrayList<>();
        String sendMail = null;//发件人
        String receiveMail = null;//收件人
        String subject = null;//标题
        String sendTime = null;//发送时间
        String payments = null;//本期应还款额
        String creditLine = null;//信用额度
        String paymentsDate = null;//还款日
        String creditNumber = null;//信用卡卡号
        for (String html : sts) {
            try {
                Document doc = Jsoup.parse(html);
                sendMail = doc.select("span.print_text_gray").get(0).text();
                receiveMail = doc.select("span.print_text_gray").get(1).text();
                subject = doc.select("div.mail_header_subject ellipsis fs16 bold").text();
                sendTime = doc.select("div.mail_header_line_text").text();
                sendTime = DateUtil.getDateformat(sendTime);
                bcie.setIdCard(idcard);
                bcie.setSenderUrl(sendMail);
                bcie.setReceiveAddUrl(receiveMail);
                bcie.setSendTime(sendTime);
                bcie.setSubject(subject);
                bcie.setInfoSource("Aliyun邮箱");
                //1.中信银行邮件解析citic
                if (sendMail.equals("<1479055311@qq.com>") && subject.contains("中信银行信用卡电子账单")) {
                    payments = doc.select("span[id=fixBand16]").text();
                    creditLine = doc.select("span[id=fixBand12]").text();
                    paymentsDate = doc.select("span[id=fixBand36]").text();
                    creditNumber = doc.select("span[id=fixBand5]").text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (payments.contains("RMB") && creditLine.contains("RMB")) {
                            payments = payments.replaceAll("[a-zA-Z,]", "").trim();
                            creditLine = creditLine.replaceAll("[a-zA-Z,]", "").trim();
                        }
                        bcie.setBankName("citic");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        paymentsDate=paymentsDate.replaceAll("[\\u00A0]+", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+ StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<中信银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //2.交通银行邮件解析boConm
                else if (sendMail.equals("<pccc@bocomcc.com>") && subject.contains("交通银行信用卡电子账单")) {
                    Elements rows = doc.select("div#item_body_detail.item_body_detail").select("span");
                    payments = rows.get(3).text();
                    creditLine = rows.get(9).text();
                    paymentsDate = rows.get(1).text();
                    creditNumber = doc.select("div#item_body_detail.item_body_detail").select("p").get(3).text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (payments.contains("￥") && creditLine.contains("￥")) {
                            payments = payments.replaceAll("[a-zA-Z￥,]", "").trim();
                            creditLine = creditLine.replaceAll("[a-zA-Z￥,]", "").trim();
                        }
                        bcie.setBankName("pccc");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<交通银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //3.民生银行CMSB
                else if (sendMail.equals("<master@creditcard.cmbc.com.cn>") && subject.contains("民生信用卡")) {
                    payments = doc.select("span[id=fixBand14]").select("div").get(2).text();
                    paymentsDate = doc.select("span[id=fixBand8]").select("div").get(2).text();
                    creditNumber = doc.select("span[id=fixBand2]").select("div").get(0).text();
                    if (!StringUtils.isEmpty(payments)  && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (payments.contains("RMB") && creditLine.contains("RMB")) {
                            payments = payments.replaceAll("[a-zA-Z￥,]", "").trim();
                        }
                        bcie.setBankName("cmsb");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,： ]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<民生银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //4.光大银行Ceb   注意(由于邮件是转发的邮件，并非原件，因此可能在获取原件时会有所出入)
                else if (sendMail.equals("<cebbank@cardcenter.cebbank.com>") && subject.contains("光大银行信用卡电子对账单")) {
                    Elements es = doc.select("div#item_body_detail.item_body_detail").select("table").get(6).getElementsByTag("td");
                    payments = es.get(14).text();
                    paymentsDate = es.get(12).text();
                    creditLine = es.get(13).text();
                    creditNumber = doc.select("div#item_body_detail.item_body_detail").select("table").get(14).getElementsByTag("td").get(10).text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (payments.contains("￥") && creditLine.contains("￥")) {
                            payments = payments.replaceAll("[a-zA-Z￥,]", "").trim();
                            creditLine = creditLine.replaceAll("[a-zA-Z￥,]", "").trim();
                        }
                        bcie.setBankName("ceb");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<光大银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;

                }
                //5.招商银行cmb
                else if (sendMail.equals("<ccsvc@message.cmbchina.com>") && subject.contains("招商银行信用卡电子账单")) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy");
                    Date day = new Date();
                    payments = doc.select("span[id=fixband57]").text();
                    paymentsDate = DateUtil.getDateformat(df.format(day) + "/" + doc.select("span[id=fixband18]").text()).replaceAll("[\\u00A0]+ ", "");
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(paymentsDate)) {
                        if (payments.contains("￥")) {
                            payments = payments.replaceAll("[a-zA-Z￥,]", "");
                        }
                        bcie.setBankName("cmb");
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setPayments(payments);
                        paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<招商银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //6.建设银行ccb
                else if (sendMail.equals("<service@vip.ccb.com>") && subject.contains("中国建设银行信用卡电子账单")) {
                    Elements rows = doc.select("div#item_body_detail.item_body_detail").select("table").get(8).select("td");
                    payments = rows.get(10).text();
                    paymentsDate = rows.get(4).text();
                    creditLine = doc.select("div#item_body_detail.item_body_detail").select("table").get(7).select("td").get(4).text();
                    creditNumber = doc.select("div#item_body_detail.item_body_detail").select("table").get(15).select("td").get(7).text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (creditLine.contains("CNY")) {
                            creditLine = creditLine.replaceAll("[a-zA-Z￥,]", "").trim();
                        }
                        payments = payments.replaceAll("[a-zA-Z,]", "");
                        creditNumber = creditNumber.replaceAll("[\\u00A0]+", "");//去除ascll为160的空格
                        bcie.setBankName("ccb");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<建设银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //7.广发银行Cgb
                else if (sendMail.equals("<creditcard@cgbchina.com.cn>") && subject.contains("广发")) {
                    Elements rows = doc.select("div#fixBand4").get(1).select("font");
                    payments = rows.get(1).text();
                    paymentsDate = rows.get(3).text();
                    creditLine = rows.get(5).text();
                    creditNumber = rows.get(0).text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        payments = payments.replaceAll("[a-zA-Z,]", "");
                        creditLine = creditLine.replaceAll("[a-zA-Z,]", "");
                        bcie.setBankName("cgb");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber(creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<广发银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //8.江西银行Jxbank
                else if (sendMail.equals("<creditcard@cc.jx-bank.com>") && subject.contains("江西银行信用卡电子账单")) {
                    Elements rows = doc.select("div#item_body_detail.item_body_detail").select("table");
                    payments = rows.get(4).getElementsByTag("td").get(5).text();
                    paymentsDate = rows.get(4).getElementsByTag("td").get(1).text();
                    creditLine = rows.get(3).getElementsByTag("td").get(3).text();
                    creditNumber = rows.get(11).select("td").get(10).text();
                    if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                        if (payments.contains("￥")) {
                            payments = payments.replaceAll("[a-zA-Z￥,]", "");
                        }
                        creditLine = creditLine.replaceAll("[\\u4e00-\\u9fa5,]", "");
                        paymentsDate = DateUtil.getDateformat(paymentsDate);
                        bcie.setBankName("jxbank");
                        bcie.setPayments(payments);
                        paymentsDate = DateUtil.getDateformat(paymentsDate).replaceAll("[\\u4e00-\\u9fa5,：]", "");
                        bcie.setPaymentsDate(paymentsDate);
                        bcie.setCreditLine(creditLine);
                        bcie.setCreditNumber("尾号：" + creditNumber);
                        bcie.setIsSuccess(0);
                    } else {
                        bcie.setIsSuccess(1);
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意,可能是新类型账单，获取账单失败---<江西银行信用卡>");
                    }
                    saveBillCycleInfoEntity(bcie);
                    listbills.add(bcie);
                    continue;
                }
                //9.工商银行Icbc
                else if (sendMail.equals("") && subject.contains("工商银行信用卡电子账单")) {

                    continue;
                }
                //10.兴业银行Cib
                else if (sendMail.equals("") && subject.contains("兴业银行信用卡电子账单")) {

                    continue;
                }
                //11.华夏银行Huaxia
                else if (sendMail.equals("") && subject.contains("华夏银行信用卡电子账单")) {

                    continue;
                }
                //12.中国银行china
                else if (sendMail.equals("") && subject.contains("中国银行信用卡电子账单")) {

                    continue;
                }
                //13.平安银行PingAn
                else if (sendMail.equals("") && subject.contains("平安银行信用卡电子账单")) {

                    continue;
                }
                //14.浦发银行Spd
                else if (sendMail.equals("") && subject.contains("浦发银行信用卡电子账单")) {

                    continue;
                }else {
                    bcie.setIsSuccess(2);
                    bcie.setErrorContent("异常邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                    bcie.setErrorInfo("请注意，暂不支持的此类银行账单！");
                    bm.insert(bcie);
                    listbills.add(bcie);
                }
            } catch (Exception e) {
                bcie.setIsSuccess(3);
                bcie.setErrorContent("异常信息为："+e.getMessage()+"。异常邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                bcie.setErrorInfo("请注意，解析邮件出现异常！");
                listbills.add(bcie);
                logger.error("解析邮件出现异常："+e);
            }
        }
        JSONObject json = new JSONObject();
        json.put("list", listbills);
        return json;
    }

    @Override
    public JSONObject mailAnalys(String[] sts, String param) throws Exception {
        return null;
    }

    public void saveBillCycleInfoEntity(BillCycleInfoEntity bcie) throws Exception {
        int m = 0;
        m = bm.countByBankOrDate(bcie);
        if (m > 0) {
            bm.updateBi(bcie);
        } else {
            bm.insert(bcie);
        }
    }
}
