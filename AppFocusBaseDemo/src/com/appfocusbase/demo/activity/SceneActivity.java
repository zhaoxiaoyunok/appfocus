package com.appfocusbase.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 名称：SceneActivity
 * 描述：场景化UI
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class SceneActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.scene_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.scene_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button analogClock  = (Button)this.findViewById(R.id.analogClock);
        Button numberClock  = (Button)this.findViewById(R.id.numberClock);
        Button calendar  = (Button)this.findViewById(R.id.calendar);
        
        analogClock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SceneActivity.this,AnalogClockActivity.class); 
 				startActivity(intent);
			}
		});
        
        
        numberClock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SceneActivity.this,NumberClockActivity.class); 
 				startActivity(intent);
			}
		});
        
        calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SceneActivity.this,DeskCalendarActivity.class); 
 				startActivity(intent);
			}
		});
      } 
    
}
