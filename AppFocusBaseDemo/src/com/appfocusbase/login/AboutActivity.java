package com.appfocusbase.login;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
import com.appfocusbase.main.MainContentFragment;

public class AboutActivity extends AfActivity {
	
	private MyApplication application;
	private AboutFragment mAboutFragment = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.about);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.about);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        //mAfTitleBar.setVisibility(View.GONE);
        //设置AfTitleBar在最上
        this.setTitleBarOverlay(true);
        application = (MyApplication)mAfApplication;
        mAfTitleBar.getLogoView().setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mAboutFragment = new AboutFragment();
		// 主视图的Fragment添加
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.about_frame, mAboutFragment).commit();

       
    }
    
}


