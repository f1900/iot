package com.mit.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
@Component
public class SMS {
	public String send(String phone,String text){
		HttpClient httpClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet("http://v.juhe.cn/sms/send?tpl_id=5257&key=5f92025969bcb21da2ffabe084ce53e1&tpl_value=%23code%23%3D"+text+"&mobile="+phone);
		
		httpGet.setConfig(RequestConfig.custom().setConnectionRequestTimeout(15000).setConnectTimeout(15000).setSocketTimeout(15000).build());
		try {
			HttpResponse httpResponse=httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			byte[] responesText=EntityUtils.toByteArray(entity);
			
			String result=new String(responesText,Charset.forName("UTF-8"));
			System.out.println(result);
			
//			JSONObject jo=JSON.parseObject(new String(responesText));
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "短信接口异常";
	}
	
	public static void main(String[] args) {
		new SMS().send("15220087352", "fdfd");
	}
}
