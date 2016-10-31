package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.slidingmenu.SlidingMenu;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;

public class SlidingMenuRightActivity extends AfActivity {

	private SlidingMenu menu;
	private AfTitleBar mAfTitleBar = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.sliding_menu_content);
		
		mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.sliding_menu_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);
        
        //主视图的Fragment添加
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, new FragmentLoad())
		.commit();

		//SlidingMenu的配置
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.RIGHT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow_right);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		//menu视图的Fragment添加
		menu.setMenu(R.layout.sliding_menu_menu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new FragmentLoad())
		.commit();
		
	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}
	
	private void initTitleRightLayout(){
		mAfTitleBar.clearRightView();
    	View rightViewMenu = mInflater.inflate(R.layout.menu_btn, null);
    	mAfTitleBar.addRightView(rightViewMenu);
    	Button menuBtn = (Button)rightViewMenu.findViewById(R.id.menuBtn);
    	
    	menuBtn.setOnClickListener(new View.OnClickListener(){

 			@Override
 			public void onClick(View v) {
 				if (menu.isMenuShowing()) {
 					menu.showContent();
 				} else {
 					menu.showMenu();
 				}
 			}
         });
    	
    }

	@Override
	protected void onResume() {
		super.onResume();
		initTitleRightLayout();
	}

}
