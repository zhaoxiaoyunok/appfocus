package com.appfocusbase.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class UIElementActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.button7,click="btnClick")Button button7;
    @AfIocView(id = R.id.button8,click="btnClick")Button button8;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.ui_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.ui_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
    } 
	
	public void btnClick(View v){
		Intent intent = null;
		switch (v.getId()) {
		case R.id.button1:
			//滑动按钮
			intent = new Intent(UIElementActivity.this, SlidingButtonActivity.class);
			startActivity(intent);
			break;
		case R.id.button2:
			//图片轮播
			intent = new Intent(UIElementActivity.this, SlidingPlayViewActivity.class);
			startActivity(intent);
			break;
		case R.id.button3:
			//日历选择器
			intent = new Intent(UIElementActivity.this, CalendarActivity.class);
			startActivity(intent);
			break;
		case R.id.button4:
			//POP提示框
			intent = new Intent(UIElementActivity.this, PopoverActivity.class);
			startActivity(intent);
			break;
		case R.id.button5:
			//轮子选择控件
			intent = new Intent(UIElementActivity.this, WheelActivity.class);
			startActivity(intent);
			break;
		case R.id.button6:
			//欢迎页面
			intent = new Intent(UIElementActivity.this, WelcomeActivity.class);
			startActivity(intent);
			break;
		case R.id.button7:
			//拍照和相册选取图片
			intent = new Intent(UIElementActivity.this, AddPhotoActivity.class);
			startActivity(intent);
			break;
		case R.id.button8:
			//拍照和相册选取图片
			intent = new Intent(UIElementActivity.this, CityListActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
    
}
