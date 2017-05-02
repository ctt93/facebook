package com.example.a;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class LoginService {
	  /**
	   * ��¼��֤
	   * @param username ����
	   * @param password ����
	   * @return
	   */
	  public static boolean save(String username, String password){
	    String path = "http://10.0.2.2:8080/day09_user/servlet/LoginAndroid"; 
	    Map<String, String> student = new HashMap<String, String>();
	    student.put("username", username);
	    student.put("password", password);
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
	   * ����GET����
	   * @param path  ����·��
	   * @param student  �������
	   * @return �����Ƿ�ɹ�
	   * @throws Exception
	   */
	  private static String SendGETRequest(String path, Map<String, String> student, String ecoding) throws Exception{
	    // http://127.0.0.1:8080/Register/ManageServlet?name=1233&password=abc
	    StringBuilder url = new StringBuilder(path);
	    url.append("?");
	    url.append("username"+"="+student.get("username")+"&"+"password"+"="+student.get("password"));
	     
	   
	    //Log.d("test", url+"");
	   
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

