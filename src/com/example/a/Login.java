package com.example.a;

import java.io.UnsupportedEncodingException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
	private EditText mUser; // �ʺű༭��
	private EditText mPassword; // ����༭��
	private boolean res;
	private String username;
	private String password;
	private Handler loginhandler;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_passwd_edit);
       
    }

    public void login_mainweixin(View v) {
    	  username = mUser.getText().toString();
          try {
			username = new String(username.getBytes("ISO8859-1"), "UTF-8");
			password = mPassword.getText().toString();
	        password = new String(password.getBytes("ISO8859-1"), "UTF-8");
	        
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          
          loginhandler=new Handler(){
        	  @Override 
              public void handleMessage(Message msg) { 
                  // TODO Auto-generated method stub 
                 
                  super.handleMessage(msg); 
                  // �˴����Ը���UI 
                  res=msg.getData().getBoolean("login");
                  if(res)   //�ж� �ʺź�����
                  {
                	  //Ӧ�ý���½�û����������ݿ⣬����־��������ǵ���
                	  
                	  
                	  
                       Intent intent = new Intent();
                       intent.setClass(Login.this,LoadingActivity.class);
                       startActivity(intent);
                       //Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
                    }
                  else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   //�ж� �ʺź�����
                  {
                  	new AlertDialog.Builder(Login.this)
          			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
          			.setTitle("��¼����")
          			.setMessage("�ʺŻ������벻��Ϊ�գ�\n��������ٵ�¼��")
          			.create().show();
                   }
                  else{
                     
                  	new AlertDialog.Builder(Login.this)
          			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
          			.setTitle("��¼ʧ��")
          			.setMessage("�ʺŻ������벻��ȷ��\n������������룡")
          			.create().show();
                  }
                   
       
              } 
          };
          
          new Thread(){
        	  public void run(){
        		  
        		  res =LoginService.save(username,password);
        		  Message msg=new Message();
        		  Bundle b = new Bundle();// ������� 
        		  b.putBoolean("login", res);
        		  msg.setData(b);
        		  loginhandler.sendMessage(msg);
        	  }
    	
          }.start();
  		
          
    	
    	
    	//��¼��ť
    	/*
      	Intent intent = new Intent();
		intent.setClass(Login.this,Whatsnew.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
		this.finish();*/
      }  
    public void login_back(View v) {     //������ ���ذ�ť
      	this.finish();
      	
      }  
    public void login_pw(View v) {     //�������밴ť
    	Uri uri = Uri.parse("http://3g.qq.com"); 
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
    	startActivity(intent);
    	//Intent intent = new Intent();
    	//intent.setClass(Login.this,Whatsnew.class);
        //startActivity(intent);
      }  
}
