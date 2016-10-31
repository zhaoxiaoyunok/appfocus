package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.af.activity.AfActivity;
import com.af.view.slidingmenu.SlidingMenu;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;

public class SlidingMenuLeftActivity extends AfActivity {

	private SlidingMenu menu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.sliding_menu_content);
		
		AfTitleBar mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.sliding_menu_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_menu);
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
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		//menu视图的Fragment添加
		menu.setMenu(R.layout.sliding_menu_menu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new FragmentLoad())
		.commit();
		
		mAfTitleBar.getLogoView().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (menu.isMenuShowing()) {
 					menu.showContent();
 				} else {
 					menu.showMenu();
 				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

}
