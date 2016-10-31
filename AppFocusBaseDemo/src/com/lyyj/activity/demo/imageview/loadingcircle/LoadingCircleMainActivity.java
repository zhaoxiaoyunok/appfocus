package com.lyyj.activity.demo.imageview.loadingcircle;
 import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
/**
 * 
 * @author LiangJunYing email:junying.hao@163.com
 *
 */
public class LoadingCircleMainActivity extends Activity {

	ListView listview;
	private LoadingImageView loadingImageView;
	private LoadingCircleView loadingCircleView;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			loadingImageView.setProgress(msg.what,true);
			loadingCircleView.setProgress(msg.what);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_iv_loadingcircle_activity_main);
		loadingImageView = (LoadingImageView) findViewById(R.id.loading_image_view);
		loadingImageView.setFgDrawableRes(R.drawable.lyyj_iv_loadingcircle_bg_click_load_img);
		loadingImageView.setBgDrawableRes(R.drawable.ic_launcher);
		loadingImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loading(); 
			}
		});
		loadingCircleView = (LoadingCircleView) findViewById(R.id.loading_cirle_view);
		loadingCircleView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loading();
			}
		});
//		listview = (ListView) findViewById(R.id.listview);
//		showImage();
	}

	private void loading(){
		Thread t = new Thread(){
			@Override
			public void run() {
				int i = 0;
				while(i<=100){
						try {
							i++;
							handler.sendEmptyMessage(i);
							this.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
				super.run();
			}
		};
		t.start();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
}
