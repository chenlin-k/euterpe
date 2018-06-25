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

@Component
public class QQemailImpl implements EmailTemplate {
    private static final Logger logger = Logger.getLogger(QQemailImpl.class);

    @Autowired
    private BillCycleMapper bm;

    public JSONObject mailAnaly(String[] sts, String idcard) throws Exception{
        //service
        String sendMail = null;//发件人
        String receiveMail = null;//收件人
        String subject = null;//标题
        String payments = null;//本期应还款额
        String creditLine = null;//信用额度
        String paymentsDate = null;//还款日
        String creditNumber = null;//信用卡卡号
        String sendTime = null;//发送时间
        List<BillCycleInfoEntity> listbills = new ArrayList<>();
        for (String html : sts) {
            BillCycleInfoEntity bcie = new BillCycleInfoEntity();
            bcie.setIdCard(idcard);
            try {
                Document doc = Jsoup.parse(html);
                Elements rows = doc.select("div[class=readmail_item_contactDetailWrap]").select("div");
                sendMail = rows.select("div[class=readmail_item_contactDetailItem_cnt]").get(0).select("span[class=readmail_item_contactDetailItem_addr]").text();//发件人
                receiveMail = rows.select("div[class=readmail_item_contactDetailItem_cnt]").get(1).select("span[class=readmail_item_contactDetailItem_addr]").text();//收件人
                sendTime = rows.select("div[class=readmail_item_contactDetailItem_cnt]").get(2).text();//发送时间
                subject = doc.select("div[class=readmail_item_head_title]").text();
                bcie.setSenderUrl(sendMail);
                bcie.setReceiveAddUrl(receiveMail);
                sendTime = DateUtil.getDateformat(sendTime);
                bcie.setSendTime(sendTime);
                bcie.setSubject(subject);
                if (sendMail != null && !sendMail.equals("")) {
                    //1.中信银行邮件解析citic
                    if (/*sendMail.equals("<1479055311@qq.com>") &&*/ subject.contains("中信银行信用卡电子账单")) {
                        payments = doc.select("span[id=fixBand16]").text();
                        creditLine = doc.select("span[id=fixBand12]").text();
                        paymentsDate = doc.select("span[id=fixBand36]").text();
                        creditNumber = doc.select("span[id=fixBand5]").text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            if (payments.contains("RMB") && creditLine.contains("RMB")) {
                                payments = payments.replaceAll("[a-zA-Z,]", "").trim();
                                creditLine = creditLine.replaceAll("[a-zA-Z,]", "").trim();
                            }
                            paymentsDate = DateUtil.getDateformat(paymentsDate);
                            creditNumber = creditNumber.substring(creditNumber.length() - 19, creditNumber.length());
                            bcie.setBankName("citic");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（中信银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //2.交通银行邮件解析boConm
                    else if (/*sendMail.equals("<pccc@bocomcc.com>") && */subject.contains("交通银行信用卡电子账单")) {
                        Elements es = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(5).getElementsByTag("td");
                        paymentsDate = es.get(2).text();
                        payments = es.get(7).text();
                        creditLine = es.get(17).text();
                        String ess = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(4).getElementsByTag("p").get(2).text();
                        creditNumber = ess.substring(ess.length() - 16, ess.length());
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            if (payments.contains("￥") && creditLine.contains("￥")) {
                                payments = payments.replaceAll("[a-zA-Z￥]", "").trim();
                                creditLine = creditLine.replaceAll("[a-zA-Z￥]", "").trim();
                            }
                            paymentsDate = DateUtil.getDateformat(paymentsDate);
                            bcie.setBankName("boConmm");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（交通银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //3.民生银行CMSB
                    else if (/*sendMail.equals("<master@creditcard.cmbc.com.cn>") &&*/ subject.contains("民生信用卡")) {
                        payments = doc.select("span[id=fixBand14]").select("div").get(2).text();
                        paymentsDate = doc.select("span[id=fixBand8]").select("div").get(2).text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(paymentsDate)) {
                            if (payments.contains("RMB")) {
                                payments = payments.replaceAll("[a-zA-Z,]", "");
                            }
                            bcie.setBankName("cmsb");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（招商银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //4.光大银行Ceb   注意(由于邮件是转发的邮件，并非原件，因此可能在获取原件时会有所出入)
                    else if (/*sendMail.equals("<cebbank@cardcenter.cebbank.com>") && */subject.contains("光大银行信用卡电子对账单")) {
                        Elements el = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(10).getElementsByTag("tr").get(1).getElementsByTag("td");
                        payments = el.get(3).text();
                        creditLine = el.get(2).text();
                        paymentsDate = el.get(1).text();
                        creditNumber = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(14).getElementsByTag("tr").get(5).getElementsByTag("td").get(0).text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            if (payments.contains("￥") && creditLine.contains("￥")) {
                                payments = payments.replaceAll("[a-zA-Z￥,]", "");
                                creditLine = creditLine.replaceAll("[a-zA-Z￥,]", "");
                            }
                            bcie.setBankName("ceb");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（光大银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //5.招商银行cmb
                    else if (/*sendMail.equals("<ccsvc@message.cmbchina.com>") && */subject.contains("招商银行信用卡电子账单")) {
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
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（招商银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //6.建设银行ccb
                    else if (/*sendMail.equals("<service@vip.ccb.com>") &&*/ subject.contains("中国建设银行信用卡电子账单")) {
                        Elements es = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(8).getElementsByTag("td");
                        creditLine = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(7).getElementsByTag("td").get(4).text();
                        payments = es.get(10).text();
                        paymentsDate = es.get(4).text();
                        creditNumber = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(15).select("td").get(7).text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            if (creditLine.contains("CNY")) {
                                creditLine = creditLine.replaceAll("[a-zA-Z,]", "");
                            }
                            payments = payments.replaceAll("[a-zA-Z,]", "");
                            creditNumber = creditNumber.replaceAll("[\\u00A0]+", "");//去除ascll为160的空格
                            bcie.setBankName("ccb");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（中国建设银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //7.广发银行Cgb
                    else if (/*sendMail.equals("<creditcard@cgbchina.com.cn>") &&*/ subject.contains("广发")) {
                        Elements es = doc.select("div[id=fixBand4]").select("div");
                        payments = es.get(3).text();
                        creditLine = es.get(7).text();
                        paymentsDate = es.get(5).text();
                        creditNumber = es.get(2).text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            creditLine = creditLine.replaceAll("[a-zA-Z,]", "");
                            payments = payments.replaceAll("[a-zA-Z,]", "");
                            bcie.setBankName("cgb");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber(creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（广发银行信用卡）银行解析账单有误...");
                        }
                        saveBillCycleInfoEntity(bcie);
                        listbills.add(bcie);
                        continue;
                    }
                    //8.江西银行Jxbank
                    else if (/*sendMail.equals("<creditcard@cc.jx-bank.com>") && */subject.contains("江西银行信用卡电子账单")) {
                        Elements es = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(2).getElementsByTag("td");
                        creditLine = es.get(5).text();
                        paymentsDate = es.get(10).text();
                        payments = es.get(14).text();
                        creditNumber = doc.select("div[class=readmail_item_contentNormal qmbox]").select("table").get(11).getElementsByTag("td").get(10).text();
                        if (!StringUtils.isEmpty(payments) && !StringUtils.isEmpty(creditLine) && !StringUtils.isEmpty(paymentsDate) && !StringUtils.isEmpty(creditNumber)) {
                            //处理金额的格式(暂时只拿到人民币的模板，未出现美元等模板)
                            if (payments.contains("￥")) {
                                payments = payments.replaceAll("[a-zA-Z￥,]", "");
                            }
                            creditLine = creditLine.replaceAll("[\\u4e00-\\u9fa5,]", "");
                            paymentsDate = DateUtil.getDateformat(paymentsDate);
                            bcie.setBankName("jxbank");
                            bcie.setCreditLine(creditLine);
                            bcie.setCreditNumber("尾号：" + creditNumber);
                            bcie.setInfoSource("QQ邮箱");
                            bcie.setPayments(payments);
                            paymentsDate = paymentsDate.replaceAll("[\\u4e00-\\u9fa5,：]", "");
                            bcie.setPaymentsDate(paymentsDate);
                            bcie.setIsSuccess(0);
                        } else {
                            bcie.setIsSuccess(1);
                            bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                            bcie.setErrorInfo("请注意,可能是新类型账单，（江西银行信用卡）银行解析账单有误...");
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
                        String parse = doc.text(); //new TextExtract().parse(html);//剔除html中的特殊字符css，div等标签，获取主体内容进行存储
                        bcie.setErrorContent("邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                        bcie.setErrorInfo("请注意，暂不支持的此类银行账单...");
                        bm.insert(bcie);
                        listbills.add(bcie);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                bcie.setIsSuccess(3);
                bcie.setErrorContent("异常邮件保存的路径为："+StringUtil.saveAsFileWriter(html));
                bcie.setErrorInfo("请注意，解析邮件出现异常...");
                try {
                    bm.insert(bcie);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
        int i = 0;
        i = bm.countByBankOrDate(bcie);
        if (i > 0) {
            bm.updateBi(bcie);
        } else {
            bm.insert(bcie);
        }
    }

}
