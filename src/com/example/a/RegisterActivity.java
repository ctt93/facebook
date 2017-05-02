package com.example.a;


import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText mUser; // �ʺű༭��
	private EditText mPassword; // ����༭��
	private EditText rePas;
	private EditText mEmail;
	private EditText mNickname;
	private boolean res;
	
	private String username;
	private String password;
	private String email;
	private String nickname;
	private String password2;
	
	private Handler regHandler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        mUser=(EditText)findViewById(R.id.reg_name);
        mPassword=(EditText)findViewById(R.id.reg_pas);
        rePas=(EditText)findViewById(R.id.reg_pas2);
        mEmail=(EditText)findViewById(R.id.reg_email);
        mNickname=(EditText)findViewById(R.id.reg_nickname);
	}
	
   
    public void register(View v) { 
    	  
        username = mUser.getText().toString();
		password = mPassword.getText().toString();
		
		email=mEmail.getText().toString();
		
		nickname=mNickname.getText().toString();
      
		password2=rePas.getText().toString();
          regHandler=new Handler(){
        	  @Override 
              public void handleMessage(Message msg) { 
                  // TODO Auto-generated method stub 
                 
                  super.handleMessage(msg); 
                  // �˴����Ը���UI 
                  res=msg.getData().getBoolean("register");
                 
                  if(username==null||username.trim().equals("")){
                	  res=false;
                	  Toast.makeText(RegisterActivity.this,  "�û�������Ϊ�գ�", Toast.LENGTH_SHORT).show();
              	}else{
              		if(!username.matches("[A-Za-z]{3,8}")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,   "�û���������3-8λ��ĸ", Toast.LENGTH_SHORT).show();
              		}
              	}
              	
              	
              	if(password==null||password.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this,   "���벻��Ϊ�գ�", Toast.LENGTH_SHORT).show(); 
              	}else{
              		if(!password.matches("\\d{3,8}")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "���������3-8λ����", Toast.LENGTH_SHORT).show(); 
              		}
              	}

                  
              	if(password2==null||password2.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this, "ȷ�����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show(); 
              	}else{
              		if(!password2.equals(password)){
              			res=false;
              			Toast.makeText(RegisterActivity.this, "�����������һ��", Toast.LENGTH_SHORT).show();  
              		}
              	}

              	if(email==null||email.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this, "���䲻��Ϊ�գ�", Toast.LENGTH_SHORT).show();  
              		
              		
              	}else{
              		if(!email.matches("\\w+@\\w+(\\.\\w+)+")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "�����ʽ����", Toast.LENGTH_SHORT).show(); 
              			
              		}
              	}

              
              	
              	if(nickname==null||nickname.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this,  "�ǳƲ���Ϊ�գ�", Toast.LENGTH_SHORT).show(); 
              		 
              	}else{
              		if(!nickname.matches("[\u4e00-\u9fa5]+")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "�ǳƱ����Ǻ���", Toast.LENGTH_SHORT).show(); 
              			
              		}
              	
              		
              else if(res) {  //�ж� �ʺź����� 
              	Intent intent = new Intent();
        		intent.setClass(RegisterActivity.this,Login.class);
        		startActivity(intent);
        		Toast.makeText(RegisterActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
        		//this.finish();
              } 
              
          }
        	  }
          };
          
          new Thread(){
        	  public void run(){
        		  res =RegisterService.save(username,password,password2,email, nickname);
        		  Message msg=new Message();
        		  Bundle b = new Bundle();// ������� 
        		  b.putBoolean("register", res);
        		  msg.setData(b);
        		  regHandler.sendMessage(msg);
        	  }
    	
          }.start();
    }
}
          
          
      
      
      
    
   

