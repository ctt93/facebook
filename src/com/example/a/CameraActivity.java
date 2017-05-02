package com.example.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a.R;

public class CameraActivity extends Activity {

	public static final int TAKE_PHOTO=1;
	public static final int CROP_PHOTO=2;
	private Button takePhoto;
	private ImageView picture;
	private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        
        takePhoto=(Button)findViewById(R.id.take_photo);
        picture=(ImageView)findViewById(R.id.picture);
        takePhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				File outputImage=new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
				try{
					if(outputImage.exists()){
						outputImage.delete();
					}
					outputImage.createNewFile();
				}catch(IOException e){
					e.printStackTrace();
				}
				imageUri=Uri.fromFile(outputImage);
				Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
			}
		});
    }
    
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
    	switch(requestCode){
    	case TAKE_PHOTO:
    		if(resultCode==RESULT_OK){
    			Intent intent=new Intent("com.android.camera.action.CROP");
    			intent.setDataAndType(imageUri, "image/*");
    			intent.putExtra("scale", true);
    			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
    			startActivityForResult(intent, CROP_PHOTO);
    		}
    		break;
    	default:
    		break;
    	}
    }
    

	}
    
    

