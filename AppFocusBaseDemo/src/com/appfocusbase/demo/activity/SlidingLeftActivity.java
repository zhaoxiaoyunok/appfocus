package com.appfocusbase.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.af.view.sliding.AfSlidingMenuView;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 
 * © 2015 lyyj.com
 * 名称：SlidingLeftActivity.java 
 * 描述：功能简单单一的一个示例
 * @author kaka
 * 
 * @version v1.0
 */
public class SlidingLeftActivity extends Activity{
	private LayoutInflater mInflater = null;
	private AfSlidingMenuView mAbSlidingView;
	private MyApplication application = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
		application = (MyApplication) this.getApplication();
		mInflater = LayoutInflater.from(this);
		LinearLayout.LayoutParams layoutParamsFF = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mAbSlidingView = new AfSlidingMenuView(this);
		mAbSlidingView.setLayoutParams(layoutParamsFF);
		
		View mainView = mInflater.inflate(R.layout.right, null);
		View leftView = mInflater.inflate(R.layout.left, null);
		mAbSlidingView.addView(leftView, layoutParamsFF);
		mAbSlidingView.addView(mainView, layoutParamsFF);
		setContentView(mAbSlidingView);
		
		Button rightBtn = (Button)mainView.findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (mAbSlidingView.getScreenState() == AfSlidingMenuView.SCREEN_STATE_CLOSE) {
					mAbSlidingView.open();
				}
			}
		});
    }

	
    
}


