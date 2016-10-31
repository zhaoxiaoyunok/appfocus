package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class IocViewActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.mBtn,click="btnClick")Button button;
	@AfIocView(id = R.id.mText)TextView mTextView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.ioc_view);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.ioc_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        mAfTitleBar.getLogoView().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    	
    }
    
    public void btnClick(View v){
		mTextView.setText("我变了");
	}
}


