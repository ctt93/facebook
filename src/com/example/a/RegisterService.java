package com.example.a;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class RegisterService {
	  /**
	   * 登录验证
	   * @param username 姓名
	   * @param password 密码
	   * @return
	   */
	  public static boolean save(String username, String password,String password2,String email,String nickname){
	    String path = "http://10.0.2.2:8080/day09_user/servlet/RegisterServlet"; 
	    Map<String, String> student = new HashMap<String, String>();
	    student.put("username", username);
	    student.put("password", password);
	    student.put("password2", password2);
	    student.put("email", email);
	    student.put("nickname", nickname);
	    try {
	      return SendGETRequest(path, student, "UTF-8");
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
	  private static boolean SendGETRequest(String path, Map<String, String> student, String ecoding) throws Exception{
	    // http://127.0.0.1:8080/Register/ManageServlet?name=1233&password=abc
	    StringBuilder url = new StringBuilder(path);
	    url.append("?");
	    url.append("username"+"="+student.get("username")+"&"+"password"+"="+student.get("password")+"&"+"password2"+"="+student.get("password2")+
	    		"&"+"email"+"="+student.get("email")+"&"+"nickname"+"="+student.get("nickname"));
	     
	   
	    Log.d("test", url+"");
	   
	    HttpClient httpClient=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url.toString());
		HttpResponse httpResponse=httpClient.execute(httpGet);
		if(httpResponse.getStatusLine().getStatusCode()==200)return true;
	    
	   
		return false;
	  }
	}

