package com.lyyj.activity.demo.dialog.frameanimation;
 

import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
/**
 * @Description: 奔跑小人的动画进度条对话框，可以用作加载数据界面
 * @author http://blog.csdn.net/finddreams
 */ 
public class RuningManActivity extends Activity {

	CustomProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_dialog_frameanim_ac_runing_man);
	}
	/**
	 * 显示美团进度对话框
	 * @param v
	 */
	public void showmeidialog(View v){
		//CustomProgressDialog dialog =new CustomProgressDialog(this, "正在加载中",R.anim.frame);
		//dialog.show();
		new CustomProgressDialog(this, "正在加载中",R.anim.lyyj_dialog_frameanim_frame_meituan).show();
	}
	/**
	 * 显示顺丰快递员进度对话框
	 * @param v
	 */
	public void showsfdialog(View v){
		new CustomProgressDialog(this, "正在加载中",R.anim.lyyj_dialog_frameanim_frame_shunfeng).show();
	}
}
