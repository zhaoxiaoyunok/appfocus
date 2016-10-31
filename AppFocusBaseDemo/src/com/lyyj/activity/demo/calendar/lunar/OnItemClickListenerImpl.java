package com.lyyj.activity.demo.calendar.lunar;

import com.lyyj.activity.demo.calendar.lunar.adapter.CalendarAdapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class OnItemClickListenerImpl implements OnItemClickListener {
	
	private CalendarAdapter adapter = null;
	private LunarActivity activity = null;
	public OnItemClickListenerImpl(CalendarAdapter adapter, LunarActivity activity) {
		this.adapter = adapter;
		this.activity = activity;
	}
	
	public void onItemClick(AdapterView<?> gridView, View view, int position, long id) {
		if (activity.currList.get(position).isThisMonth() == false) {
			return;
		}
		adapter.setSelectedPosition(position);  
		adapter.notifyDataSetInvalidated(); 
		activity.lastSelected = activity.currList.get(position).getDate();
	}
	
}
