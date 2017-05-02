package com.example.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.wolf.Player;
import com.example.wolf.PlayerAdapter;

import com.example.a.R;

public class GameActivity extends Activity {

	private List<Player> playerList= new ArrayList<Player>();
	
	private Button button_start;
	private Button seeall;
	
	private int[] list={1,1,1,1,2,2,2,2,3,4,5,6};
	 private List<String> rolelist=new ArrayList<String>();
	 
	 private RadioGroup rg;
	 private boolean startflag=true; 
     
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1,"狼");
		map.put(2, "村民");
		map.put(3, "预言家");
		map.put(4, "女巫");
        map.put(5, "猎人");
        map.put(6, "白痴");
        map.put(7, "守卫");
        map.put(8, "白狼王");
        
      
        initlist(startflag);
        list=shufflecard();
        
       
        for(int i=0;i<list.length;i++){
			
			
        	
			
			
        	Player f1=new Player(map.get(list[i]),R.drawable.background);
        	playerList.add(f1);
			rolelist.add(map.get(list[i])+",");
			//Log.d("MainActivity",map.get(list[i])+" ");
		}    
    	
       
        PlayerAdapter adapter=new PlayerAdapter(GameActivity.this, R.layout.player_item, playerList);
        ListView listview=(ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Player fruit=playerList.get(position);
				Toast.makeText(GameActivity.this, "你的身份是 "+fruit.getName(), Toast.LENGTH_SHORT).show();
			}
        	
        });
        
        button_start=(Button)findViewById(R.id.start);
        button_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				initlist(startflag);
				
				 list=shufflecard();
			        
				 if(playerList!=null){
					 playerList.clear();
						rolelist.clear();
					}
			        for(int i=0;i<12;i++){
						
						
						
						
			        	Player f1=new Player(map.get(list[i]),R.drawable.background);
			        	playerList.add(f1);
						rolelist.add(map.get(list[i])+"\r\n");
					}    
				
					
			}
		});
        
        seeall=(Button)findViewById(R.id.seeall);
        seeall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String data=rolelist.toString();
				Intent intent=new Intent(GameActivity.this,SeeallActivity.class);
				intent.putExtra("rolelist", data);
				startActivity(intent);
				
			}
		});
        
       rg=(RadioGroup)findViewById(R.id.rg);
       rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedid) {
			// TODO Auto-generated method stub
			switch(group.getCheckedRadioButtonId()){
			case R.id.normal:
				startflag=true;
				initlist(startflag);
				break;
			case R.id.king:
				startflag=false;
				initlist(startflag);
				break;
				default:
					break;
			}
		}
	});
        
        
    
        
    }
    
    private void initlist(boolean startflag) {
    	
		// 标准局
    	if(startflag){
    		for(int i=0;i<4;i++){
    		list[i]=1;
    		}
    		for(int i=4;i<8;i++){
    		list[i]=2;	
    		}
    		list[8]=3;
    		list[9]=4;
    		list[10]=5;
    		list[11]=6;
    		
    		
    	}
    	//狼王局
    	else if(!startflag){
    		list[0]=8;
    		for(int i=1;i<4;i++){
        		list[i]=1;
        		}
        		for(int i=4;i<8;i++){
        		list[i]=2;	
        		}
        		list[8]=3;
        		list[9]=4;
        		list[10]=5;
        		list[11]=7;
    		
    	}
		}
	

	protected void onDestroy(){
    	super.onDestroy();
    	
    }

	
	
	
	
	
    

	
	
	
	 public int[] shufflecard()//洗牌
	    {
	        Random rd = new Random();
	        for(int i=0;i<list.length;i++)
	        {
	            int j = rd.nextInt(list.length);//生成随机数
	            int temp = list[i];//交换
	            list[i]=list[j];
	            list[j]=temp;
	        }
	        return list;
	    }


	    

	
	
	
		
		
	}

	



