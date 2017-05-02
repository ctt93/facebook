package com.example.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import java.security.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class LocationService {
	  /**
	   * 登录验证
	   * @param username 姓名
	   * @param password 密码
	   * @return
	   */
	  public static boolean save(String qiandaotime,String dakatime,String latitude,String longtitude){
	    String path = "http://10.0.2.2:8080/day09_user/servlet/QiandaoServlet"; 
	    Map<String, String> student = new HashMap<String, String>();
	    
	    student.put("qiandaotime", qiandaotime);
	    student.put("dakatime", dakatime);
	    student.put("latitude", latitude);
	    student.put("longtitude", longtitude);
	    try {
	    	 if(SendGETRequest(path, student, "UTF-8").equals("ok"))return true;
		      else if(SendGETRequest(path, student, "UTF-8").equals("fail"))return false;
		     
	    } catch (Exception e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return false;
	  }
	  /**
	   * 发送GET请求
	   * @param path  请求路径
	   * @param student  请求参数
	   * @return 请求是否成功
	   * @throws Exception
	   */
	  private static String SendGETRequest(String path, Map<String, String> student, String ecoding) throws Exception{
	    // http://127.0.0.1:8080/Register/ManageServlet?name=1233&password=abc
	    StringBuilder url = new StringBuilder(path);
	    url.append("?");
	    url.append("qiandaotime"+"="+student.get("qiandaotime")+"&"+"dakatime"+"="+student.get("dakatime")+
	    		"&"+"latitude"+"="+student.get("latitude")+"&"+"longtitude"+"="+student.get("longtitude"));
	     
	   
	    Log.d("test", url+"");
	   
	    HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url.toString());
		HttpResponse response=httpClient.execute(httpGet);
		StringBuilder sb = new StringBuilder();  
		
		 HttpEntity entity = response.getEntity();  
         if (entity != null) {  
             BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"), 8192);  
               
             String line = null;  
             while ((line = reader.readLine())!= null){  
                 sb.append(line);  
             }  
             reader.close();  
            
             
         }  
         Log.d("test",sb.toString()+"");
	    
         return sb.toString();
	  }
	}

