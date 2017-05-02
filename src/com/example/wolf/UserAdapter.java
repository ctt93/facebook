package com.example.wolf;

import java.util.List;

import com.example.a.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class UserAdapter extends ArrayAdapter<User> {
	
	public UserAdapter(Context context, int godItem, List<User> listItems) {
		// TODO Auto-generated constructor stub
		super(context,godItem,listItems);
	}
	private int resourceId;

	
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		User user=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.roleid=(TextView)view.findViewById(R.id.roleid);
			viewHolder.role=(TextView)view.findViewById(R.id.rolename);
			
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder)view.getTag();
		}
		viewHolder.roleid.setText(user.getId()+"ºÅÍæ¼Ò");
		viewHolder.role.setText(user.getRole());
		return view;
	}
	}

