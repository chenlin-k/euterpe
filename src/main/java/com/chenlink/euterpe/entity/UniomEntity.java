package com.chenlink.euterpe.entity;

import java.util.List;


/**
 * 联通数据解析vo类
 */
public class UniomEntity {

//    private List<databean> list = new ArrayList<>();
//
//    public List<databean> getList() {
//        return list;
//    }
//
//    public void setList(List<databean> list) {
//        this.list = list;
//    }
//
//    public static class databean {
        private String trxid;
        private UserinfoBean userinfo;
        private String rspts;
        private String rspsign;
        private boolean isnotsuccess;
        private boolean existexception;
        private String transid;
        private String rspdesc;
        private String ecsbusiorder;
        private boolean issuccess;
        private String querytime;
        private String rspcode;
        private ResultBean result;
        private String busiorder;

        public String getTrxid() {
            return trxid;
        }

        public void setTrxid(String trxid) {
            this.trxid = trxid;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public String getRspts() {
            return rspts;
        }

        public void setRspts(String rspts) {
            this.rspts = rspts;
        }

        public String getRspsign() {
            return rspsign;
        }

        public void setRspsign(String rspsign) {
            this.rspsign = rspsign;
        }

        public boolean isIsnotsuccess() {
            return isnotsuccess;
        }

        public void setIsnotsuccess(boolean isnotsuccess) {
            this.isnotsuccess = isnotsuccess;
        }

        public boolean isExistexception() {
            return existexception;
        }

        public void setExistexception(boolean existexception) {
            this.existexception = existexception;
        }

        public String getTransid() {
            return transid;
        }

        public void setTransid(String transid) {
            this.transid = transid;
        }

        public String getRspdesc() {
            return rspdesc;
        }

        public void setRspdesc(String rspdesc) {
            this.rspdesc = rspdesc;
        }

        public String getEcsbusiorder() {
            return ecsbusiorder;
        }

        public void setEcsbusiorder(String ecsbusiorder) {
            this.ecsbusiorder = ecsbusiorder;
        }

        public boolean isIssuccess() {
            return issuccess;
        }

        public void setIssuccess(boolean issuccess) {
            this.issuccess = issuccess;
        }

        public String getQuerytime() {
            return querytime;
        }

        public void setQuerytime(String querytime) {
            this.querytime = querytime;
        }

        public String getRspcode() {
            return rspcode;
        }

        public void setRspcode(String rspcode) {
            this.rspcode = rspcode;
        }

        public ResultBean getResult() {
            return result;
        }

        public void setResult(ResultBean result) {
            this.result = result;
        }

        public String getBusiorder() {
            return busiorder;
        }

        public void setBusiorder(String busiorder) {
            this.busiorder = busiorder;
        }

        public static class UserinfoBean {

            private String areacode;
            private String usernumber;
            private String custname;
            private String custlvl;
            private String nickname;
            private boolean is_20;
            private String brand;
            private String currentid;
            private String laststatdate;
            private String opendate;
            private String nettype;
            private String packageid;
            private String packagename;
            private String paytype;
            private String certnum;
            private String subscrbstat;
            private String producttype;
            private String provincecode;
            private String productid;
            private String customid;
            private String brand_name;
            private String is_wo;
            private String citycode;
            private String expiretime;
            private boolean is_36;
            private String logintype;

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }

            public String getUsernumber() {
                return usernumber;
            }

            public void setUsernumber(String usernumber) {
                this.usernumber = usernumber;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public boolean isIs_20() {
                return is_20;
            }

            public void setIs_20(boolean is_20) {
                this.is_20 = is_20;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getCurrentid() {
                return currentid;
            }

            public void setCurrentid(String currentid) {
                this.currentid = currentid;
            }

            public String getLaststatdate() {
                return laststatdate;
            }

            public void setLaststatdate(String laststatdate) {
                this.laststatdate = laststatdate;
            }

            public String getOpendate() {
                return opendate;
            }

            public void setOpendate(String opendate) {
                this.opendate = opendate;
            }

            public String getNettype() {
                return nettype;
            }

            public void setNettype(String nettype) {
                this.nettype = nettype;
            }

            public String getPackageid() {
                return packageid;
            }

            public void setPackageid(String packageid) {
                this.packageid = packageid;
            }

            public String getPackagename() {
                return packagename;
            }

            public void setPackagename(String packagename) {
                this.packagename = packagename;
            }

            public String getPaytype() {
                return paytype;
            }

            public void setPaytype(String paytype) {
                this.paytype = paytype;
            }

            public String getCertnum() {
                return certnum;
            }

            public void setCertnum(String certnum) {
                this.certnum = certnum;
            }

            public String getSubscrbstat() {
                return subscrbstat;
            }

            public void setSubscrbstat(String subscrbstat) {
                this.subscrbstat = subscrbstat;
            }

            public String getProducttype() {
                return producttype;
            }

            public void setProducttype(String producttype) {
                this.producttype = producttype;
            }

            public String getProvincecode() {
                return provincecode;
            }

            public void setProvincecode(String provincecode) {
                this.provincecode = provincecode;
            }

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public String getCustomid() {
                return customid;
            }

            public void setCustomid(String customid) {
                this.customid = customid;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getIs_wo() {
                return is_wo;
            }

            public void setIs_wo(String is_wo) {
                this.is_wo = is_wo;
            }

            public String getCitycode() {
                return citycode;
            }

            public void setCitycode(String citycode) {
                this.citycode = citycode;
            }

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public boolean isIs_36() {
                return is_36;
            }

            public void setIs_36(boolean is_36) {
                this.is_36 = is_36;
            }

            public String getLogintype() {
                return logintype;
            }

            public void setLogintype(String logintype) {
                this.logintype = logintype;
            }
        }

        public static class ResultBean {
            private String areacode;
            private boolean issuccess;
            private String deratefee;
            private String presentfeeused;
            private String respdesc;
            private String userid;
            private String allfee;
            private String cycleid;
            private String recvfeeused;
            private String balance;
            private String backfee;
            private String busiorder;
            private String serialnumber;
            private String adjustfee;
            private String writeofffee;
            private String respcode;
            private String actionfeeused;
            private List<BillinfoBean> billinfo;
            private List<List<BillinfoitemBean>> billinfoitem;
            private List<ScoreinfoBean> scoreinfo;

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }

            public boolean isIssuccess() {
                return issuccess;
            }

            public void setIssuccess(boolean issuccess) {
                this.issuccess = issuccess;
            }

            public String getDeratefee() {
                return deratefee;
            }

            public void setDeratefee(String deratefee) {
                this.deratefee = deratefee;
            }

            public String getPresentfeeused() {
                return presentfeeused;
            }

            public void setPresentfeeused(String presentfeeused) {
                this.presentfeeused = presentfeeused;
            }

            public String getRespdesc() {
                return respdesc;
            }

            public void setRespdesc(String respdesc) {
                this.respdesc = respdesc;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getAllfee() {
                return allfee;
            }

            public void setAllfee(String allfee) {
                this.allfee = allfee;
            }

            public String getCycleid() {
                return cycleid;
            }

            public void setCycleid(String cycleid) {
                this.cycleid = cycleid;
            }

            public String getRecvfeeused() {
                return recvfeeused;
            }

            public void setRecvfeeused(String recvfeeused) {
                this.recvfeeused = recvfeeused;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getBackfee() {
                return backfee;
            }

            public void setBackfee(String backfee) {
                this.backfee = backfee;
            }

            public String getBusiorder() {
                return busiorder;
            }

            public void setBusiorder(String busiorder) {
                this.busiorder = busiorder;
            }

            public String getSerialnumber() {
                return serialnumber;
            }

            public void setSerialnumber(String serialnumber) {
                this.serialnumber = serialnumber;
            }

            public String getAdjustfee() {
                return adjustfee;
            }

            public void setAdjustfee(String adjustfee) {
                this.adjustfee = adjustfee;
            }

            public String getWriteofffee() {
                return writeofffee;
            }

            public void setWriteofffee(String writeofffee) {
                this.writeofffee = writeofffee;
            }

            public String getRespcode() {
                return respcode;
            }

            public void setRespcode(String respcode) {
                this.respcode = respcode;
            }

            public String getActionfeeused() {
                return actionfeeused;
            }

            public void setActionfeeused(String actionfeeused) {
                this.actionfeeused = actionfeeused;
            }

            public List<BillinfoBean> getBillinfo() {
                return billinfo;
            }

            public void setBillinfo(List<BillinfoBean> billinfo) {
                this.billinfo = billinfo;
            }

            public List<List<BillinfoitemBean>> getBillinfoitem() {
                return billinfoitem;
            }

            public void setBillinfoitem(List<List<BillinfoitemBean>> billinfoitem) {
                this.billinfoitem = billinfoitem;
            }

            public List<ScoreinfoBean> getScoreinfo() {
                return scoreinfo;
            }

            public void setScoreinfo(List<ScoreinfoBean> scoreinfo) {
                this.scoreinfo = scoreinfo;
            }

            public static class BillinfoBean {

                private String discnt;
                private String adjustbefore;
                private String integrateitemcode;
                private String adjustafter;
                private String fee;
                private String integrateitem;
                private String parentitemcode;
                private String balance;
                private String usedvalue;

                public String getDiscnt() {
                    return discnt;
                }

                public void setDiscnt(String discnt) {
                    this.discnt = discnt;
                }

                public String getAdjustbefore() {
                    return adjustbefore;
                }

                public void setAdjustbefore(String adjustbefore) {
                    this.adjustbefore = adjustbefore;
                }

                public String getIntegrateitemcode() {
                    return integrateitemcode;
                }

                public void setIntegrateitemcode(String integrateitemcode) {
                    this.integrateitemcode = integrateitemcode;
                }

                public String getAdjustafter() {
                    return adjustafter;
                }

                public void setAdjustafter(String adjustafter) {
                    this.adjustafter = adjustafter;
                }

                public String getFee() {
                    return fee;
                }

                public void setFee(String fee) {
                    this.fee = fee;
                }

                public String getIntegrateitem() {
                    return integrateitem;
                }

                public void setIntegrateitem(String integrateitem) {
                    this.integrateitem = integrateitem;
                }

                public String getParentitemcode() {
                    return parentitemcode;
                }

                public void setParentitemcode(String parentitemcode) {
                    this.parentitemcode = parentitemcode;
                }

                public String getBalance() {
                    return balance;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public String getUsedvalue() {
                    return usedvalue;
                }

                public void setUsedvalue(String usedvalue) {
                    this.usedvalue = usedvalue;
                }
            }

            public static class BillinfoitemBean {

                private String discnt;
                private String adjustbefore;
                private String integrateitemcode;
                private int level;
                private String adjustafter;
                private String fee;
                private String integrateitem;
                private String parentitemcode;
                private String balance;
                private String usedvalue;

                public String getDiscnt() {
                    return discnt;
                }

                public void setDiscnt(String discnt) {
                    this.discnt = discnt;
                }

                public String getAdjustbefore() {
                    return adjustbefore;
                }

                public void setAdjustbefore(String adjustbefore) {
                    this.adjustbefore = adjustbefore;
                }

                public String getIntegrateitemcode() {
                    return integrateitemcode;
                }

                public void setIntegrateitemcode(String integrateitemcode) {
                    this.integrateitemcode = integrateitemcode;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getAdjustafter() {
                    return adjustafter;
                }

                public void setAdjustafter(String adjustafter) {
                    this.adjustafter = adjustafter;
                }

                public String getFee() {
                    return fee;
                }

                public void setFee(String fee) {
                    this.fee = fee;
                }

                public String getIntegrateitem() {
                    return integrateitem;
                }

                public void setIntegrateitem(String integrateitem) {
                    this.integrateitem = integrateitem;
                }

                public String getParentitemcode() {
                    return parentitemcode;
                }

                public void setParentitemcode(String parentitemcode) {
                    this.parentitemcode = parentitemcode;
                }

                public String getBalance() {
                    return balance;
                }

                public void setBalance(String balance) {
                    this.balance = balance;
                }

                public String getUsedvalue() {
                    return usedvalue;
                }

                public void setUsedvalue(String usedvalue) {
                    this.usedvalue = usedvalue;
                }
            }

            public static class ScoreinfoBean {

                private String rsrvscore1;
                private String scoreusevalue;
                private String rsrvscore2;
                private String rsrvscoreadjust;
                private String scoreidle_value;
                private String rsrvscore3;

                public String getRsrvscore1() {
                    return rsrvscore1;
                }

                public void setRsrvscore1(String rsrvscore1) {
                    this.rsrvscore1 = rsrvscore1;
                }

                public String getScoreusevalue() {
                    return scoreusevalue;
                }

                public void setScoreusevalue(String scoreusevalue) {
                    this.scoreusevalue = scoreusevalue;
                }

                public String getRsrvscore2() {
                    return rsrvscore2;
                }

                public void setRsrvscore2(String rsrvscore2) {
                    this.rsrvscore2 = rsrvscore2;
                }

                public String getRsrvscoreadjust() {
                    return rsrvscoreadjust;
                }

                public void setRsrvscoreadjust(String rsrvscoreadjust) {
                    this.rsrvscoreadjust = rsrvscoreadjust;
                }

                public String getScoreidle_value() {
                    return scoreidle_value;
                }

                public void setScoreidle_value(String scoreidle_value) {
                    this.scoreidle_value = scoreidle_value;
                }

                public String getRsrvscore3() {
                    return rsrvscore3;
                }

                public void setRsrvscore3(String rsrvscore3) {
                    this.rsrvscore3 = rsrvscore3;
                }
            }
        }
//    }
}
