package com.lyyj.activity.demo.listview.swipemenulistview.sample;
 

import com.appfocusbase.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SwipeMenuListViewMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_lv_swipemenu_activity_main);

	}
	
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.button1:
			startActivity(new Intent(this, SimpleActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(this, DifferentMenuActivity.class));
			break;
		}
	}
}
