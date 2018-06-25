package com.chenlink.euterpe.mybatis.service;

import net.sf.json.JSONObject;

public interface EmailTemplate {

    public JSONObject mailAnaly(String[] sts, String idcard)throws Exception;

    public JSONObject mailAnalys(String[] sts, String param) throws Exception;

}
