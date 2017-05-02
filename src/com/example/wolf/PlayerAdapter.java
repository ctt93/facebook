package com.example.wolf;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wolf.Player;
import com.example.a.R;
import com.example.wolf.ViewHolder;

public class PlayerAdapter extends ArrayAdapter<Player> {
	private int resourceId;
	public PlayerAdapter(Context context,int textViewResourceId,List<Player> objects){
		super(context,textViewResourceId,objects);
		resourceId=textViewResourceId;
	}
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		Player player=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.playerIamge=(ImageView)view.findViewById(R.id.player_image);
			viewHolder.playername=(TextView)view.findViewById(R.id.player_name);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.playerIamge.setImageResource(player.getImageId());
		viewHolder.playername.setText((position+1)+"ºÅÍæ¼Ò");
		return view;
	}
	}
