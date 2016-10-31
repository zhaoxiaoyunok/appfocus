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
public class TabActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.tab_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.tab_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button topTab  = (Button)this.findViewById(R.id.topTab);
        Button bottomTab  = (Button)this.findViewById(R.id.bottomTab);
        
        topTab.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TabActivity.this,TabTopActivity.class); 
 				startActivity(intent);
			}
		});
        
        
        bottomTab.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TabActivity.this,TabBottomActivity.class); 
 				startActivity(intent);
			}
		});
        
      } 
    
}
