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
	private EditText mUser; // 帐号编辑框
	private EditText mPassword; // 密码编辑框
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
                  // 此处可以更新UI 
                  res=msg.getData().getBoolean("register");
                 
                  if(username==null||username.trim().equals("")){
                	  res=false;
                	  Toast.makeText(RegisterActivity.this,  "用户名不能为空！", Toast.LENGTH_SHORT).show();
              	}else{
              		if(!username.matches("[A-Za-z]{3,8}")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,   "用户名必须是3-8位字母", Toast.LENGTH_SHORT).show();
              		}
              	}
              	
              	
              	if(password==null||password.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this,   "密码不能为空！", Toast.LENGTH_SHORT).show(); 
              	}else{
              		if(!password.matches("\\d{3,8}")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "密码必须是3-8位数字", Toast.LENGTH_SHORT).show(); 
              		}
              	}

                  
              	if(password2==null||password2.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this, "确认密码不能为空！", Toast.LENGTH_SHORT).show(); 
              	}else{
              		if(!password2.equals(password)){
              			res=false;
              			Toast.makeText(RegisterActivity.this, "俩次密码必须一致", Toast.LENGTH_SHORT).show();  
              		}
              	}

              	if(email==null||email.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this, "邮箱不能为空！", Toast.LENGTH_SHORT).show();  
              		
              		
              	}else{
              		if(!email.matches("\\w+@\\w+(\\.\\w+)+")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "邮箱格式不对", Toast.LENGTH_SHORT).show(); 
              			
              		}
              	}

              
              	
              	if(nickname==null||nickname.trim().equals("")){
              		res=false;
              		Toast.makeText(RegisterActivity.this,  "昵称不能为空！", Toast.LENGTH_SHORT).show(); 
              		 
              	}else{
              		if(!nickname.matches("[\u4e00-\u9fa5]+")){
              			res=false;
              			Toast.makeText(RegisterActivity.this,  "昵称必须是汉字", Toast.LENGTH_SHORT).show(); 
              			
              		}
              	
              		
              else if(res) {  //判断 帐号和密码 
              	Intent intent = new Intent();
        		intent.setClass(RegisterActivity.this,Login.class);
        		startActivity(intent);
        		Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        		//this.finish();
              } 
              
          }
        	  }
          };
          
          new Thread(){
        	  public void run(){
        		  res =RegisterService.save(username,password,password2,email, nickname);
        		  Message msg=new Message();
        		  Bundle b = new Bundle();// 存放数据 
        		  b.putBoolean("register", res);
        		  msg.setData(b);
        		  regHandler.sendMessage(msg);
        	  }
    	
          }.start();
    }
}
          
          
      
      
      
    
   

