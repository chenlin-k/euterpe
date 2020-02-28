package com.chenlink.euterpe.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.chenlink.euterpe.constant.SysConfig;
import jodd.util.Base64;
import org.apache.log4j.Logger;


/**
 * 验证码识别工具类
 * @author weixiaoyu
 * @date 2016年12月14日
 */
public class CheckCodeUtil {
	private static Logger logger = Logger.getLogger(CheckCodeUtil.class);

	public static final String INPUTNAME = "ocrfile";
	public static final String OCR_URL = "http://lab.ocrking.com/ok.html";
//	public static final String apiKey = "54507931a0efb5cce1UDaBimrYer3MjFxdrgcJz7BEx8xn6RfzZeQ3dJAQK2Zy3IPOpno5UbM";
	
	/**
	 * 获得验证码识别结果
	 * @param path 验证码图片存放地址
	 * @return
	 * @throws Exception
	 */
	public static String getCodeResult(String path) throws Exception{
		if(SysConfig.discernType ==1){
			return getCodeResultByOcr(path);
		}else{
			return getvercode(path);
		}

	}
	
	/**
	 * OcrKing验证码识别
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getCodeResultByOcr(String path) throws Exception{		
		//图像预处理，增加识别率
		ImageUtils.addWhite(8,2,path);
		Map<String, String> dataMap = new HashMap<String, String>();

		dataMap.put("service", "OcrKingForCaptcha");
//		dataMap.put("service", "OcrKingForNumber");
		dataMap.put("language", "eng");
		dataMap.put("charset", "1");
		dataMap.put("type", "http://www.unknown.com");
		dataMap.put("apiKey", SysConfig.apiKey);
		String result = postMultipart(OCR_URL,dataMap,path);
		logger.info("OcrKing验证码识别结果\n"+result);
		result = result.substring(result.indexOf("<Result>")+8,result.indexOf("</Result>"));
		return result;
	}
/*	
	<Results>
	  <ResultList>
	    <Item>
	      <SrcFile Width="56" Height="14">http://file.ocrking.net:6080/small/20161214/wpzClcKawph/wqfCj8Obw4PClsKWwqbCmcKWZcOXwpLCmHDCqA/780885ef-bd87-4c2d-b852-e02a8a86e702.bmp?Uw2RwGG3p9OtLVpTjKiquULWkJSX8iqcSe2XydOzw1n6c8VyYY976/LtDh5Do9RQ</SrcFile>
	      <DesFile Width="56" Height="14">http://file.ocrking.net:6080/small/20161214/wpzClcKawph/wqfCj8Obw4PClsKWwqbCmcKWZcOXwpLCmHDCqA/780885ef-bd87-4c2d-b852-e02a8a86e702_debug.png?Uw2RwGG3p9OtLVpTjKiquULWkJSX8iqcSe2XydOzw1n6c8VyYY976/LtDh5Do9RQ</DesFile>
	      <Result>8710</Result>
	      <Status>true</Status>
	      <Note />
	    </Item>
	  </ResultList>
	  <Timestamp>1481717115</Timestamp>
	  <Version>3.3.2.1158</Version>
	  <CopyRights>Aven's Lab 2015</CopyRights>
	  <WebSite>http://www.ocrking.com</WebSite>
	</Results>
	*/
	/**
	 * OcrKing验证码识别提交
	 * @param urlStr
	 * @param dataMap
	 * @param path 文件路径
	 * @return
	 * @throws Exception 
	 */
	public static String postMultipart(String urlStr, Map<String, String> dataMap, String path) throws Exception {
		String res = "";
		HttpURLConnection conn = null;
		String boundary = "----------------------------OcrKing_Client_Aven_s_Lab";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Referer", "http://lab.ocrking.com/?javaclient0.1)");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; zh-CN; rv:1.9.1.3) Gecko/20100101 Firefox/8.0");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// data   
			if (dataMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = dataMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(boundary).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file  
			File file = new File(path);
			System.out.println("文件是否存在"+file.exists());
			if(!file.exists()){
				throw new Exception("验证码临时文件不存在");
			}
				
			String filename = file.getName();
			StringBuffer strBuf2 = new StringBuffer();
			strBuf2.append("\r\n").append("--").append(boundary).append("\r\n");
			strBuf2.append("Content-Disposition: form-data; name=\"" + INPUTNAME + "\"; filename=\"" + filename + "\"\r\n");
			strBuf2.append("Content-Type:application/octet-stream\r\n\r\n");
			out.write(strBuf2.toString().getBytes());
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();
			
			byte[] endData = ("\r\n--" + boundary + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// handle the response 
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
//			System.out.println("error " + urlStr);
			e.printStackTrace();
			logger.error("提交验证码到OcrKing异常",e);
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	private static String getvercode(String path){
		String base64=null;
		String result=null;
		// file
		File file = new File(path);
		System.out.println("文件是否存在"+file.exists());
		if(!file.exists()){
			logger.error("验证码临时文件不存在");
		}
		InputStream in=null;
		try {
			in=new FileInputStream(file);
			byte[] bytes = new byte[in.available()];
			int length = in.read(bytes);
			base64 = Base64.encodeToString(bytes,true);
			result=vercode.getRequest(base64);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {

		String result = "<Result>8710</Result>";
		result = result.substring(result.indexOf("<Result>")+8,result.indexOf("</Result>"));
		System.out.println(result);
	}
}
