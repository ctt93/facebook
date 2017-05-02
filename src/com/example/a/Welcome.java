package com.example.a;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Welcome extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome);
    }
    public void welcome_login(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Welcome.this,Login.class);
		startActivity(intent);
		//this.finish();
      }  
    public void welcome_register(View v) {  
      	Intent intent = new Intent();
		intent.setClass(Welcome.this,RegisterActivity.class);
		startActivity(intent);
		//this.finish();
      }  
}
