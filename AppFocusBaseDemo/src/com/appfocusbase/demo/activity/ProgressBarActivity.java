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
 * 名称：ProgressBarActivity
 * 描述：2种漂亮的进度条
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class ProgressBarActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.progress_bar_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.progressbar_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button mCircleView  = (Button)this.findViewById(R.id.mCircleView);
        Button mHorizontalView  = (Button)this.findViewById(R.id.mHorizontalView);
        
        mCircleView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProgressBarActivity.this,ProgressBarCircleActivity.class); 
 				startActivity(intent);
			}
		});
        
        mHorizontalView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProgressBarActivity.this,ProgressBarHorizontalActivity.class); 
 				startActivity(intent);
			}
		});
       
      } 
    
}
