package com.lyyj.activity.demo.guide.guidefourways.activity.splash;

import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.guidefourways.SuccessLaunchActivity;
import com.lyyj.activity.demo.guide.guidefourways.utils.AnimationUtil;
 


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class SplashActivity extends Activity {

	private static final long DELAY_TIME = 3000L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_guide_fourways_activity_splash);
		redirectByTime();
	}

	private void redirectByTime() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this,SuccessLaunchActivity.class));
				AnimationUtil.finishActivityAnimation(SplashActivity.this);
			}
		}, DELAY_TIME);
	}
}
