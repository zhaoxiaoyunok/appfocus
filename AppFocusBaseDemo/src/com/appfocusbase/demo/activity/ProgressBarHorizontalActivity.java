package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.af.activity.AfActivity;
import com.af.view.progress.AfHorizontalProgressBar;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.Constant;
import com.appfocusbase.global.MyApplication;
/**
 * 
 * © 2015 lyyj.com
 * 名称：ProgressBarHorizontalActivity.java 
 * 描述：水平进度条
 * @author kaka
 * 
 * @version v1.0
 */
public class ProgressBarHorizontalActivity extends AfActivity {

	private String TAG = "MainActivity";
	private static final boolean D = Constant.DEBUG;
	private MyApplication application;

	// ProgressBar进度控制
	private AfHorizontalProgressBar mAfProgressBar;
	// 最大1000
	private int max = 100;	
	private int progress = 0;
	private TextView numberText, maxText;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.progress_bar_horizontal);

		application = (MyApplication) mAfApplication;
		AfTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.progressbar_horizontal_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// ProgressBar进度控制
		mAfProgressBar = (AfHorizontalProgressBar) findViewById(R.id.horizontalProgressBar);
		
		numberText = (TextView) findViewById(R.id.numberText);
		maxText = (TextView) findViewById(R.id.maxText);
		
		maxText.setText("/"+String.valueOf(max));
		mAfProgressBar.setMax(max);
		mAfProgressBar.setProgress(progress);
		
		mAbTitleBar.getLogoView().setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		startAddProgress();
		
		mAfProgressBar.setAbOnProgressListener(new AfHorizontalProgressBar.AbOnProgressListener() {
			
			@Override
			public void onProgress(int progress) {
				
			}
			
			@Override
			public void onComplete() {
				progress = 0;
				mAfProgressBar.reset();
			}
		});
		
	}


	public void startAddProgress() {
		progress = progress+10;
		numberText.setText(String.valueOf(progress));
		mAfProgressBar.setProgress(progress);
		mUpdateHandler.sendEmptyMessageDelayed(1, 1000);
	}

	private Handler mUpdateHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				startAddProgress();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	private Runnable mUpdateRunnable = new Runnable() {
		public void run() {
			while (true) {
				Message message = new Message();
				message.what = 1;
				mUpdateHandler.sendMessage(message);
				try {
					// 更新间隔毫秒数
					Thread.sleep(200);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	};
	
	

}
