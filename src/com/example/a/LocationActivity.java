package com.example.a;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity {

private MapView mapView;
private BaiduMap baiduMap;
private TextView locate;
private CheckBox check;
private LocationManager locationManager;

private String provider;

private boolean isFirstLocate=true; 

private Handler locate1Handler;
private Handler locate2Handler;
private String preqiandaotime;
private String qiandaotime;
private String dakatime;
private String latitude;
private String longtitude;
private boolean res;
	
private final double MISLA=0.098516d;
private final double MISLO=0.05341d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏  
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.locate);
        mapView=(MapView)findViewById(R.id.map_view);
        locate=(TextView)findViewById(R.id.location);
        check=(CheckBox)findViewById(R.id.check);
        
        baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providerList= locationManager.getProviders(true);
        if(providerList.contains(LocationManager.GPS_PROVIDER)){
        	provider=LocationManager.GPS_PROVIDER;
        }else if(providerList.contains(LocationManager.NETWORK_PROVIDER)){
        	provider=LocationManager.NETWORK_PROVIDER;
        }else{
        	Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
        	return;
        }
        Location location=locationManager.getLastKnownLocation(provider);
        
        
        if(location !=null){
        	navigaTo(location);
        }
        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
        
        
      if(getIntent().getStringExtra("preqiandaotime")!=null){
        	   // Restore state members from saved instance  
            preqiandaotime = getIntent().getStringExtra("preqiandaotime");
            dakatime=getIntent().getStringExtra("dakatime");
            latitude=getIntent().getStringExtra("latitude");
            longtitude=getIntent().getStringExtra("longtitude");
            if(dakatime.equals(0+"")){
            	locate.setText(preqiandaotime+" 签到成功！");
            	 check.setChecked(true);
            }
            else {
            	locate.setText(preqiandaotime+" 打卡成功！");
            }
           
        }else{
        
        

        latitude=location.getLatitude()+MISLA+"";
		longtitude=location.getLongitude()-MISLO+"";
        }
        
      
    }
    
    private void navigaTo(Location location) {
		// TODO Auto-generated method stub
    	
		if(isFirstLocate){
			LatLng ll=new LatLng(location.getLatitude()+MISLA, location.getLongitude()-MISLO);
			
			Log.d("test", location.getLatitude()+" "+location.getLongitude());
			MapStatusUpdate update=MapStatusUpdateFactory.newLatLng(ll);
			baiduMap.animateMapStatus(update);
			update=MapStatusUpdateFactory.zoomBy(16f);
			baiduMap.animateMapStatus(update);
			isFirstLocate=false;
		}
		MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
		locationBuilder.latitude(location.getLatitude()+MISLA);
		locationBuilder.longitude(location.getLongitude()-MISLO);
		MyLocationData locationData=locationBuilder.build();
		baiduMap.setMyLocationData(locationData);
		
		
	}
    
    LocationListener locationListener=new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			if(location!=null){
				navigaTo(location);
			}
		}
	};
	
	

	@Override
    protected void onDestroy(){
    	super.onDestroy();
    	baiduMap.setMyLocationEnabled(false);
    	mapView.onDestroy();
    	if(locationManager!=null){
    		locationManager.removeUpdates(locationListener);
    	}
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	mapView.onPause();
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	mapView.onResume();
    }
    
    public void in(View v){
    	/*
          runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");       
				Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
				String    str    =    formatter.format(curDate);
				
				  StringBuilder sb=new StringBuilder();
			        String[] datetime=str.split(" ");
			        sb.append(datetime[0]);
			        sb.append("%20");
			        sb.append(datetime[1]);
			        qiandaotime=sb.toString();
				dakatime=0+"";
				//判断地理位置
				if(!check.isChecked()){
				locate.setText(str+" 签到成功！");
				check.setChecked(true);
				}else{
					Toast.makeText(LocationActivity.this, "已签到", Toast.LENGTH_SHORT).show();
				}
				 Intent intent=new Intent();
		          intent.setClass(LocationActivity.this,Qiandao.class);
		          intent.putExtra("qiandaotime", qiandaotime);
		          intent.putExtra("dakatime", dakatime);
		          intent.putExtra("latitude", latitude);
		          intent.putExtra("longtitude", longtitude);
		          startActivity(intent);
				
			}
		});
		*/
         
		
    	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");       
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
		preqiandaotime   =    formatter.format(curDate);
		 StringBuilder sb=new StringBuilder();
	        String[] datetime=preqiandaotime.split(" ");
	        sb.append(datetime[0]);
	        sb.append("%20");
	        sb.append(datetime[1]);
	        qiandaotime=sb.toString();
    	dakatime=0+"";
    	
    	locate1Handler=new Handler(){
    		 @Override 
             public void handleMessage(Message msg) { 
                 // TODO Auto-generated method stub 
                
                 super.handleMessage(msg); 
                 // 此处可以更新UI 
                 res=msg.getData().getBoolean("qiandao");
                 
                	 if(res){
     				locate.setText(preqiandaotime+" 签到成功！");
     				check.setChecked(true);
                	 }
     				else{
     					Toast.makeText(LocationActivity.this, "已签到", Toast.LENGTH_SHORT).show();
     				}
    		 }
                 
    	};
    	  new Thread(){
        	  public void run(){
        		  if(!check.isChecked())
        		 res =LocationService.save(qiandaotime, dakatime, latitude, longtitude);
        		  else res=false;
        		  Message msg=new Message();
        		  Bundle b = new Bundle();// 存放数据 
        		  b.putBoolean("qiandao", res);
        		  msg.setData(b);
        		  locate1Handler.sendMessage(msg);
        	  }
    	
          }.start();
          
    	
    	
    }
    
    public void card(View v){
    	if(check.isChecked()){
    	 Intent intent=new Intent();
         intent.setClass(LocationActivity.this,Qiandao.class);
         intent.putExtra("preqiandaotime", preqiandaotime);
         intent.putExtra("dakatime", dakatime);
         intent.putExtra("latitude", latitude);
         intent.putExtra("longtitude", longtitude);
         startActivity(intent);
    	}else{
    		Toast.makeText(LocationActivity.this, "还未签到或已打卡", Toast.LENGTH_SHORT).show();
    	}
    }
    
    public void out(View v){
    	/*
    	 runOnUiThread(new Runnable() {
 			
 			@Override
 			public void run() {
 				// TODO Auto-generated method stub
 				SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");       
 				Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
 				String    str    =    formatter.format(curDate);
 				//判断地理位置
 				if(check.isChecked()){
 				locate.setText(str+" 打卡成功！");
 				check.setChecked(false);
 				}else{
 					Toast.makeText(LocationActivity.this, "还未签到或已打卡", Toast.LENGTH_SHORT).show();
 				}
 			}
 		});
 		*/
    
    	SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss");       
		Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间       
		preqiandaotime   =    formatter.format(curDate);
		 StringBuilder sb=new StringBuilder();
	        String[] datetime=preqiandaotime.split(" ");
	        sb.append(datetime[0]);
	        sb.append("%20");
	        sb.append(datetime[1]);
	        qiandaotime=sb.toString();
    	dakatime=1+"";
    	locate2Handler=new Handler(){
    		 @Override 
             public void handleMessage(Message msg) { 
                 // TODO Auto-generated method stub 
                
                 super.handleMessage(msg); 
                 // 此处可以更新UI 
                 res=msg.getData().getBoolean("daka");
                 
                	 if(res){
     				locate.setText(preqiandaotime+" 打卡成功！");
     				check.setChecked(false);
                	 }
     				else{
     					Toast.makeText(LocationActivity.this, "还未签到或已打卡", Toast.LENGTH_SHORT).show();
     				}
    		 }
                 
    	};
    	  new Thread(){
        	  public void run(){
        		  if(check.isChecked())
        		 res =LocationService.save(qiandaotime, dakatime, latitude, longtitude);
        		  else res=false;
        		  Message msg=new Message();
        		  Bundle b = new Bundle();// 存放数据 
        		  b.putBoolean("daka", res);
        		  msg.setData(b);
        		  locate2Handler.sendMessage(msg);
        	  }
    	
          }.start();
    }
    
    
    
   
     
       
   @Override
    public void onBackPressed(){
    	Intent backintent=new Intent();
    	  backintent.putExtra("preqiandaotime", preqiandaotime);
          backintent.putExtra("dakatime", dakatime);
          backintent.putExtra("latitude", latitude);
          backintent.putExtra("longtitude", longtitude);
       
		this.setResult(RESULT_OK,backintent);
    	this.finish();
    }
   
   public void locate_back(View v){
	   Intent backintent=new Intent();
 	  backintent.putExtra("preqiandaotime", preqiandaotime);
       backintent.putExtra("dakatime", dakatime);
       backintent.putExtra("latitude", latitude);
       backintent.putExtra("longtitude", longtitude);
    
	this.setResult(RESULT_OK,backintent);
 	this.finish();
   }
    
    }

