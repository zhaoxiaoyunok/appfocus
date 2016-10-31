package com.appfocusbase.login;

import android.os.Bundle;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class AgreementActivity extends AfActivity {
	
    private MyApplication application;
    private AfTitleBar mAfTitleBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.agreement);
		application = (MyApplication) mAfApplication;
	    
	    mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.agreement_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);
		this.setTitleBarOverlay(true);
		
	}
	
}
