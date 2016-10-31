package com.appfocusbase.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * © 2015 lyyj.com
 * 名称：AnimationActivity.java 
 * 描述：动画控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class AnimationActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.button7,click="btnClick")Button button7;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.anim_main);
        
        AfTitleBar mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText(R.string.anim_name);
        mAbTitleBar.setLogo(R.drawable.button_selector_back);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
    } 
	
	public void btnClick(View v){
		Intent intent = null;
		switch (v.getId()) {
		case R.id.button1:
			break;
		case R.id.button2:
			break;
		case R.id.button3:
			break;
		case R.id.button4:
			break;
		case R.id.button5:
			break;
		case R.id.button6:
			break;
		case R.id.button7:
			break;
		default:
			break;
		}
	}
    
}
