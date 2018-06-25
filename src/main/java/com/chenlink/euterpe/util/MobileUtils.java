package com.chenlink.euterpe.util;

import com.chenlink.euterpe.entity.MobileEntity;
import com.chenlink.euterpe.entity.MobileOperatorEntity;
import com.chenlink.euterpe.entity.UniomEntity;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * Describe: 三大运营商数据解析工具类
 * Author:   chenlink
 * Data:     2018/5/31.
 */

public class MobileUtils {


    /**
     * 获取联通运营商解析的数据
     * @param idcard
     * @param phone
     * @param mobileType
     * @param param
     * @return
     */
    public List<MobileOperatorEntity> getCuccPeopleData(String idcard, String phone, String mobileType, String param){
        List<MobileOperatorEntity> list=new ArrayList<>();
//        Gson gson=new Gson();
//        UniomEntity list1=gson.fromJson(param,UniomEntity.class);
        List<UniomEntity> Uniomlist=JSONArray.toList(JSONArray.fromObject(param),new UniomEntity(),new JsonConfig());
        for(UniomEntity uni:Uniomlist){
            MobileOperatorEntity me=new MobileOperatorEntity();
            me.setIdcard(idcard);
            me.setPhone(phone);
            me.setMobiletype(mobileType);
            me.setCreatetime(DateUtil.getNowDate());
            if(uni.isIssuccess()){
                me.setAllfee(Double.valueOf(uni.getResult().getAllfee()));
                me.setCertnum(uni.getUserinfo().getCertnum());
                me.setCustlvl(uni.getUserinfo().getCustlvl());
                me.setCustname(uni.getUserinfo().getCustname());
                SimpleDateFormat sdf=new SimpleDateFormat();
                me.setCycledate(uni.getResult().getCycleid());
                me.setUsernumber(uni.getUserinfo().getUsernumber());
                me.setIserror(0);
            }else{
                me.setIserror(1);
                me.setReason("运营商信息获取资料为空！");
            }
            list.add(me);
        }
        return list;
    }


    /**
     * 获取移动运营商解析的数据
     * @param idcard
     * @param phone
     * @param mobileType
     * @param param
     * @return
     */
    public List<MobileOperatorEntity> getCmccPeopleData(String idcard,String phone,String mobileType,String param){
        List<MobileOperatorEntity> list=new ArrayList<>();
        Gson gson=new Gson();
        MobileOperatorEntity mo=new MobileOperatorEntity();
        MobileEntity me=gson.fromJson(param,MobileEntity.class) ;
        mo.setIdcard(idcard);
        mo.setPhone(phone);
        mo.setMobiletype(mobileType);
        if(me.getRetCode().equals("000000")){//受理成功
            for(MobileEntity.DataBean db:me.getData()){
                MobileOperatorEntity mo1=new MobileOperatorEntity();
                mo1.setIdcard(idcard);
                mo1.setPhone(phone);
                mo1.setMobiletype(mobileType);
                mo1.setCreatetime(DateUtil.getNowDate());
                mo1.setCycledate(db.getBillMonth());
                mo1.setAllfee(Double.valueOf(db.getBillFee()));
                mo1.setIserror(0);
                list.add(mo1);
            }
        }else{//受理失败
            mo.setCreatetime(DateUtil.getNowDate());
            mo.setIserror(1);
            mo.setReason("移动运营商受理失败！");
            list.add(mo);
        }
        return list;
    }


    /**
     * 获取电信运营商解析的数据
     * @param idcard
     * @param phone
     * @param mobileType
     * @param param
     * @return
     */
    public MobileOperatorEntity getCtccPeopleData(String idcard,String phone,String mobileType,String param) {
        MobileOperatorEntity me = new MobileOperatorEntity();
        Document doc = Jsoup.parse(param);
        String custname = doc.select("div.container div.welcome p b").text();
        String usernumber = doc.select("div.container div.details div#billList.bill-list div.panel.phone div.panel-head.phone-list span.number").get(0).text();
        String total_month = doc.select("div.container div.details div#billList.bill-list div.bill-head.bill-over span b#total-month").text();
        String bill_over = doc.select("div.container div.details div#billList.bill-list div.bill-head.bill-over span b").text();
        if (!StringUtils.isEmpty(custname)&&!StringUtils.isEmpty(usernumber)&&!StringUtils.isEmpty(total_month)) {
            bill_over = bill_over.replaceAll(total_month, "");
            Double allfee = Double.valueOf(bill_over);
            me.setAllfee(allfee);
            //处理月份
            total_month = DateUtil.StringMonthAddYear(total_month);
            me.setCustname(custname);
            me.setUsernumber(usernumber);
            me.setCycledate(total_month);
            me.setIserror(0);
        }else{
            me.setIserror(0);
            me.setReason("解析电信通信账单失败！");
        }
        me.setIdcard(idcard);
        me.setPhone(phone);
        me.setMobiletype(mobileType);
        me.setCreatetime(DateUtil.getNowDate());
        return me;
    }



}
