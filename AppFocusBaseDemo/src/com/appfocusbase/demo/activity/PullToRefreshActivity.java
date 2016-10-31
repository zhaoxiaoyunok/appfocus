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
 * 名称：PullToRefreshActivity 描述：下拉刷新分页
 * 
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class PullToRefreshActivity extends AfActivity {

	private MyApplication application;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setAfContentView(R.layout.pull_to_refresh_main);

		AfTitleBar mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.pull_list_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) mAfApplication;
		Button mListView = (Button) this.findViewById(R.id.mListView);
		Button mSampleView = (Button) this.findViewById(R.id.mSampleView);
		Button mGridView = (Button) this.findViewById(R.id.mGridView);
		Button mMultiListView = (Button) this.findViewById(R.id.mMultiListView);

		mSampleView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PullToRefreshActivity.this,
						PullToRefreshViewActivity.class);
				startActivity(intent);
			}
		});

		mListView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PullToRefreshActivity.this,
						PullToRefreshListActivity.class);
				startActivity(intent);
			}
		});

		mGridView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PullToRefreshActivity.this,
						PullToRefreshGridActivity.class);
				startActivity(intent);
			}
		});

		mMultiListView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PullToRefreshActivity.this,
						PullToRefreshMultiColumnListActivity.class);
				startActivity(intent);
			}
		});

	}

}
