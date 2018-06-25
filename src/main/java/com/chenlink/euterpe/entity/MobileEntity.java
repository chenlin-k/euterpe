package com.chenlink.euterpe.entity;

import java.util.List;

/**
 * 移动数据解析VO类
 */

public class MobileEntity {


    /**
     * data : [{"billEndDate":"20180630","billFee":"23.04","billMonth":"201806","billStartDate":"20180601","billMaterials":[{"billEntriy":"01","billEntriyValue":"18.00","billMaterialInfos":[{"itemName":"包月使用费","itemValue":"18.00","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"5.04","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"5.04","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null},{"billEndDate":"20180531","billFee":"33.84","billMonth":"201805","billStartDate":"20180501","billMaterials":[{"billEntriy":"01","billEntriyValue":"27.90","billMaterialInfos":[{"itemName":"GPRS套餐费","itemValue":"9.90","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null},{"itemName":"包月使用费","itemValue":"18.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"5.94","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"5.94","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null},{"billEndDate":"20180430","billFee":"53.60","billMonth":"201804","billStartDate":"20180401","billMaterials":[{"billEntriy":"01","billEntriyValue":"37.90","billMaterialInfos":[{"itemName":"来电显示","itemValue":"0.00","remark":null},{"itemName":"GPRS套餐费","itemValue":"19.90","remark":null},{"itemName":"包月使用费","itemValue":"18.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"15.70","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"15.70","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null},{"billEndDate":"20180331","billFee":"225.26","billMonth":"201803","billStartDate":"20180301","billMaterials":[{"billEntriy":"01","billEntriyValue":"198.00","billMaterialInfos":[{"itemName":"包月使用费","itemValue":"18.00","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null},{"itemName":"GPRS套餐费-包年费","itemValue":"180.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"20.38","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"20.38","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"6.88","billMaterialInfos":[{"itemName":"gprs费(cmnet)_本地（LTE）","itemValue":"6.88","remark":null}],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null},{"billEndDate":"20180228","billFee":"70.14","billMonth":"201802","billStartDate":"20180201","billMaterials":[{"billEntriy":"01","billEntriyValue":"57.90","billMaterialInfos":[{"itemName":"GPRS套餐费","itemValue":"39.90","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null},{"itemName":"包月使用费","itemValue":"18.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"12.24","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"12.24","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null},{"billEndDate":"20180131","billFee":"47.58","billMonth":"201801","billStartDate":"20180101","billMaterials":[{"billEntriy":"01","billEntriyValue":"28.00","billMaterialInfos":[{"itemName":"来电显示","itemValue":"0.00","remark":null},{"itemName":"GPRS套餐费","itemValue":"10.00","remark":null},{"itemName":"包月使用费","itemValue":"18.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"9.72","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"9.72","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"9.86","billMaterialInfos":[{"itemName":"gprs费(cmnet)_本地","itemValue":"0.00","remark":null},{"itemName":"gprs费(cmnet)_本地（LTE）","itemValue":"9.86","remark":null}],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}],"remark":null}]
     * retCode : 000000
     * retMsg : 受理成功
     * sOperTime : 20180621150353
     */

    private String retCode;
    private String retMsg;
    private String sOperTime;
    private List<DataBean> data;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public String getSOperTime() {
        return sOperTime;
    }

    public void setSOperTime(String sOperTime) {
        this.sOperTime = sOperTime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * billEndDate : 20180630
         * billFee : 23.04
         * billMonth : 201806
         * billStartDate : 20180601
         * billMaterials : [{"billEntriy":"01","billEntriyValue":"18.00","billMaterialInfos":[{"itemName":"包月使用费","itemValue":"18.00","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null}],"remark":null},{"billEntriy":"02","billEntriyValue":"5.04","billMaterialInfos":[{"itemName":"基本通话费","itemValue":"5.04","remark":null}],"remark":null},{"billEntriy":"03","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"04","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"05","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"06","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"09","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"11","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"12","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null},{"billEntriy":"13","billEntriyValue":"0.00","billMaterialInfos":[],"remark":null}]
         * remark : null
         */

        private String billEndDate;
        private String billFee;
        private String billMonth;
        private String billStartDate;
        private Object remark;
        private List<BillMaterialsBean> billMaterials;

        public String getBillEndDate() {
            return billEndDate;
        }

        public void setBillEndDate(String billEndDate) {
            this.billEndDate = billEndDate;
        }

        public String getBillFee() {
            return billFee;
        }

        public void setBillFee(String billFee) {
            this.billFee = billFee;
        }

        public String getBillMonth() {
            return billMonth;
        }

        public void setBillMonth(String billMonth) {
            this.billMonth = billMonth;
        }

        public String getBillStartDate() {
            return billStartDate;
        }

        public void setBillStartDate(String billStartDate) {
            this.billStartDate = billStartDate;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public List<BillMaterialsBean> getBillMaterials() {
            return billMaterials;
        }

        public void setBillMaterials(List<BillMaterialsBean> billMaterials) {
            this.billMaterials = billMaterials;
        }

        public static class BillMaterialsBean {
            /**
             * billEntriy : 01
             * billEntriyValue : 18.00
             * billMaterialInfos : [{"itemName":"包月使用费","itemValue":"18.00","remark":null},{"itemName":"来电显示","itemValue":"0.00","remark":null}]
             * remark : null
             */

            private String billEntriy;
            private String billEntriyValue;
            private Object remark;
            private List<BillMaterialInfosBean> billMaterialInfos;

            public String getBillEntriy() {
                return billEntriy;
            }

            public void setBillEntriy(String billEntriy) {
                this.billEntriy = billEntriy;
            }

            public String getBillEntriyValue() {
                return billEntriyValue;
            }

            public void setBillEntriyValue(String billEntriyValue) {
                this.billEntriyValue = billEntriyValue;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public List<BillMaterialInfosBean> getBillMaterialInfos() {
                return billMaterialInfos;
            }

            public void setBillMaterialInfos(List<BillMaterialInfosBean> billMaterialInfos) {
                this.billMaterialInfos = billMaterialInfos;
            }

            public static class BillMaterialInfosBean {
                /**
                 * itemName : 包月使用费
                 * itemValue : 18.00
                 * remark : null
                 */

                private String itemName;
                private String itemValue;
                private Object remark;

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public String getItemValue() {
                    return itemValue;
                }

                public void setItemValue(String itemValue) {
                    this.itemValue = itemValue;
                }

                public Object getRemark() {
                    return remark;
                }

                public void setRemark(Object remark) {
                    this.remark = remark;
                }
            }
        }
    }
}
