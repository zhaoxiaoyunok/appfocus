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
 * 名称：ChartActivity
 * 描述：图表
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class SlidingMenuActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.sliding_menu);
        
        application = (MyApplication)mAfApplication;
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.sliding_menu_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
       
        Button btn1  = (Button)this.findViewById(R.id.btn1);
        Button btn2  = (Button)this.findViewById(R.id.btn2);
        Button btn3  = (Button)this.findViewById(R.id.btn3);
        Button btn4  = (Button)this.findViewById(R.id.btn4);
        Button btn5  = (Button)this.findViewById(R.id.btn5);
        Button btn6  = (Button)this.findViewById(R.id.btn6);
        Button btn7  = (Button)this.findViewById(R.id.btn7);
        Button btn8  = (Button)this.findViewById(R.id.btn8);
        
        btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingLeftActivity.class); 
 				startActivity(intent);
			}
		});
        
        
        btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuLeftActivity.class); 
 				startActivity(intent);
			}
		});
        
        btn3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuRightActivity.class); 
 				startActivity(intent);
			}
		});
        
        btn4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuLeftRightActivity.class); 
 				startActivity(intent);
			}
		});
        
        btn5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuTitleHoldActivity.class); 
 				startActivity(intent);
			}
		});

        btn6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuScaleActivity.class); 
 				startActivity(intent);
			}
		});
        
        btn7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuSlideActivity.class); 
 				startActivity(intent);
			}
		});
        
        btn8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SlidingMenuActivity.this,SlidingMenuZoomActivity.class); 
 				startActivity(intent);
			}
		});
        
        
      } 
    
}
