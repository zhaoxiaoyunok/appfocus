package com.appfocusbase.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.fragment.AfAlertDialogFragment.AfDialogOnClickListener;
import com.af.util.AfDialogUtil;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 名称：NestScrollActivity
 * 描述：各种嵌套
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class NestScrollActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.nest_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.nest_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button listPager  = (Button)this.findViewById(R.id.listPager);
        Button slidingMenuPager  = (Button)this.findViewById(R.id.slidingMenuPager);
        Button slidingMenuTab  = (Button)this.findViewById(R.id.slidingMenuTab);
        Button scrollPager  = (Button)this.findViewById(R.id.scrollPager);
        
        
        listPager.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NestScrollActivity.this,ListNestViewPagerActivity.class); 
 				startActivity(intent);
			}
		});
        
        slidingMenuPager.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NestScrollActivity.this,SlidingMenuNestViewPagerActivity.class); 
 				startActivity(intent);
			}
		});
        
        slidingMenuTab.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NestScrollActivity.this,SlidingMenuNestTabActivity.class); 
 				startActivity(intent);
			}
		});
        
        scrollPager.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AfDialogUtil.showAlertDialog(NestScrollActivity.this,"提示", "把你的ScrollView换成AbOuterScrollView就可以了", new AfDialogOnClickListener() {

					@Override
					public void onNegativeClick() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onPositiveClick() {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});
        
      } 
    
}
