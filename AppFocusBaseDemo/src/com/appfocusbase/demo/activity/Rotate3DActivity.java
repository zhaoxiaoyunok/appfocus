package com.appfocusbase.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 名称：Rotate3DActivity
 * 描述：3D翻转示例
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class Rotate3DActivity extends AfActivity {
	
	/** The Constant TAG. */
    private static final String TAG = "Rotate3DActivity";
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.rotate3d_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.rotate3d_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button btn1  = (Button)this.findViewById(R.id.btn1);
        Button btn2  = (Button)this.findViewById(R.id.btn2);
        
        btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Rotate3DActivity.this,Rotate3DActivity1.class); 
 				startActivity(intent);
			}
		});
        
        btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Rotate3DActivity.this,Rotate3DActivity2.class); 
 				startActivity(intent);
			}
		});
      } 

    
}
