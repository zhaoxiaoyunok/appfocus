/**
 * SuccessLaunchActivity.java [V 1..0.0]
 * classes : com.zhangyx.MyLauncherGuide.SuccessLaunchActivity
 * zhangyx Create at 2015-1-21 下午1:58:49
 */
package com.lyyj.activity.demo.guide.guidefourways;
 
import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.guidefourways.utils.AnimationUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 引导成功界面
 *com.zhangyx.MyLauncherGuide.SuccessLaunchActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-21 下午1:58:49
 */
public class SuccessLaunchActivity extends AfActivity{

	@AfIocView(id = R.id.btnBack)
	private Button btnBack;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.lyyj_guide_fourways_activity_successlaunch);
		
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(SuccessLaunchActivity.this,GuideFourWaysMainActivity.class));
				AnimationUtil.finishActivityAnimation(SuccessLaunchActivity.this);
			}
		});
	}

}
