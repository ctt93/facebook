package com.example.a;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.methods.HttpPost;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Qiandao extends Activity {
	private TextView qiandaoText; 
	private TextView mTime;
	private boolean res;
	private String qiandaotime;
	private String dakatime;
	private String latitude;
	private String longtitude;
	private String preqiandaotime;
	private LinearLayout layout;
	private static final int msgKey1 = 1;

	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.qiandao);
        
        qiandaoText = (TextView)findViewById(R.id.qiandao);
        mTime=(TextView)findViewById(R.id.mytime);
       
        preqiandaotime=getIntent().getExtras().getString("preqiandaotime");
        String[] datetime=preqiandaotime.split(" ");
        qiandaotime=datetime[1];
        
		dakatime=getIntent().getExtras().getString("dakatime");
		latitude=getIntent().getExtras().getString("latitude");
		longtitude=getIntent().getExtras().getString("longtitude");
		Log.d("test", preqiandaotime+" "+dakatime+" "+latitude+" "+longtitude);

  	   String type;
	if(dakatime.equals(0+""))
  		   type="签到";
  	   else type="打卡";
  	   qiandaoText.setText("刷脸时间为"+"\n"+preqiandaotime+"\n"+"刷脸类型为"+"\n"+type+"\n"+"刷脸地点为"+"\n"+"纬度："+latitude+"\n"+"经度："+longtitude);
  	 new TimeThread().start();
    }
    
    public class TimeThread extends Thread {
    	         @Override
    	         public void run () {
    	             do {
    	                 try {
    	                     Thread.sleep(1000);
    	                     Message msg = new Message();
    	                     msg.what = msgKey1;
    	                     mHandler.sendMessage(msg);
    	                 }
    	                 catch (InterruptedException e) {
    	                     e.printStackTrace();
    	                 }
    	             } while(true);
    	         }
    	     }
    private Handler mHandler = new Handler() {
    	         @Override
    	         public void handleMessage (Message msg) {
    	             super.handleMessage(msg);
    	             switch (msg.what) {
    	                 case msgKey1:
    	                	 SimpleDateFormat    df    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");
    	                	 try
    	                	 {
    	                	 Date d1 = df.parse(preqiandaotime);
    	                	
    	                	 Date d2 = new Date(System.currentTimeMillis());//你也可以获取当前时间 
    	                	 long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
    	                	 long days = diff / (1000 * 60 * 60 * 24);
    	                	 long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
    	                	 long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
    	                	 long miao=(diff-days*(1000*60*60*24)-hours*(1000*60*60)-minutes*(1000*60))/1000;
    	                	 mTime.setText("当前已签到时长："+"\n"+""+days+"天"+hours+"小时"+minutes+"分"+miao+"秒");
    	                	 }
    	                	 catch (Exception e)
    	                	 {
    	                		 e.printStackTrace();
    	                	 }
    	                     
    	                     break;
    	                 
    	                 default:
    	                     break;
    	             }
    	         }
    	     };
    	     
    	     
    	    public void card_back(View v){
    	    	finish();
    	    }



}
	
		
   		
    
    
  
    	
    	
    	
        
          
    	
    	


      

            
           
      

   

