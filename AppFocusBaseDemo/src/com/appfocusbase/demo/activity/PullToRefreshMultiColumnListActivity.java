package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;

import com.af.activity.AfActivity;
import com.af.util.AfToastUtil;
import com.af.view.pullview.AfMultiColumnListAdapter;
import com.af.view.pullview.AfMultiColumnListView;
import com.af.view.pullview.AfMultiColumnListView.OnScrollListener;
import com.af.view.pullview.AfPullToRefreshView;
import com.af.view.pullview.AfPullToRefreshView.OnFooterLoadListener;
import com.af.view.pullview.AfPullToRefreshView.OnHeaderRefreshListener;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.MultiColumnImageListAdapter;
import com.appfocusbase.demo.model.Article;
import com.appfocusbase.demo.model.ArticleListResult;
import com.appfocusbase.demo.model.ImageInfo;
import com.appfocusbase.global.MyApplication;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class PullToRefreshMultiColumnListActivity extends AfActivity implements
		OnHeaderRefreshListener, OnFooterLoadListener {

	private MyApplication application;
	private List<ImageInfo> mImageList = null;
	private AfPullToRefreshView mAfPullToRefreshView = null;
	private AfMultiColumnListView mListView = null;
	private AfMultiColumnListAdapter myListViewAdapter = null;
	private int currentPage = 1;
	private AfTitleBar mAfTitleBar = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.pull_to_refresh_multi_list);
		application = (MyApplication) mAfApplication;

		mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.multi_column_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);
		
		

		// 获取ListView对象
		mAfPullToRefreshView = (AfPullToRefreshView) this
				.findViewById(R.id.mPullRefreshView);
		mListView = (AfMultiColumnListView) this.findViewById(R.id.mListView);

		// 设置监听器
		mAfPullToRefreshView.setOnHeaderRefreshListener(this);
		mAfPullToRefreshView.setOnFooterLoadListener(this);

		// 设置进度条的样式
		mAfPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(
				this.getResources().getDrawable(R.drawable.progress_circular));
		mAfPullToRefreshView.getFooterView().setFooterProgressBarDrawable(
				this.getResources().getDrawable(R.drawable.progress_circular));

		// 获取ListView对象
		mListView = (AfMultiColumnListView) this.findViewById(R.id.mListView);

		// ListView数据
		mImageList = new ArrayList<ImageInfo>();

		// 使用自定义的Adapter
		myListViewAdapter = new MultiColumnImageListAdapter(this, mImageList);
		mListView.setAdapter(myListViewAdapter);
		
		//如果里面有图片是动态加载的，请在这配置!!!,系统要处理释放
		mListView.setReleaseImageResIds(new int[]{R.id.itemsIcon});
		
		refreshTask();
		
		mListView.setOnScrollListener(new OnScrollListener(){

			@Override
			public void onScrollChanged(int x, int y, int oldx, int oldy) {
				// TODO Auto-generated method stub
			}
		});

	}
	
	@Override
	public void onFooterLoad(AfPullToRefreshView view) {
		loadMoreTask();
	}

	@Override
	public void onHeaderRefresh(AfPullToRefreshView view) {
		refreshTask();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}

	public void refreshTask() {
		currentPage = 1;
		String url = "http://www.duitang.com/album/1733789/masn/p/"
				+ currentPage + "/24/";
		
		FutureCallback<String> callback =new FutureCallback< String> (){

			@Override
			public void onCompleted(Exception e, String result) {

				if((e !=null)||(result==null)){
					AfToastUtil.showToast(PullToRefreshMultiColumnListActivity.this,"网络访问错误");
					return;
				}
				List<ImageInfo> mNewImageList = parseJSON(result);
				mImageList.clear();
				if (mNewImageList != null && mNewImageList.size() > 0) {
					mImageList.addAll(mNewImageList);
					myListViewAdapter.notifyDataSetChanged();
				}
				mAfPullToRefreshView.onHeaderRefreshFinish();
		
			}			
		};
		Ion.with(PullToRefreshMultiColumnListActivity.this)
		.load(url)
		.asString()
		.setCallback(callback);		

	}

	public void loadMoreTask() {
		currentPage++;
		String url = "http://www.duitang.com/album/1733789/masn/p/"
				+ currentPage + "/24/";
		FutureCallback<String> callback =new FutureCallback< String> (){

			@Override
			public void onCompleted(Exception e, String result) {

				if((e !=null)||(result==null)){
					AfToastUtil.showToast(PullToRefreshMultiColumnListActivity.this,"网络访问错误");
					return;
				}
				List<ImageInfo> mNewImageList = parseJSON(result);
				mImageList.clear();
				if (mNewImageList != null && mNewImageList.size() > 0) {
					mImageList.addAll(mNewImageList);
					myListViewAdapter.notifyDataSetChanged();
				}
				mAfPullToRefreshView.onHeaderRefreshFinish();
		
			}			
		};
		Ion.with(PullToRefreshMultiColumnListActivity.this)
		.load(url)
		.asString()
		.setCallback(callback);		

	}
	

	/**
	 * 
	 * 描述：数据来源
	 * 
	 * @param json
	 * @return
	 * @throws
	 */
	public List<ImageInfo> parseJSON(String json) {
		List<ImageInfo> mImageList = new ArrayList<ImageInfo>();
		try {
			if (null != json) {
				JSONObject newsObject = new JSONObject(json);
				JSONObject jsonObject = newsObject.getJSONObject("data");
				JSONArray blogsJson = jsonObject.getJSONArray("blogs");
				ImageInfo imageInfo = null;
				for (int i = 0; i < blogsJson.length(); i++) {
					imageInfo = new ImageInfo();
					JSONObject newsInfoLeftObject = blogsJson.getJSONObject(i);
					imageInfo.setUrl(newsInfoLeftObject.isNull("isrc") ? ""
							: newsInfoLeftObject.getString("isrc"));
					imageInfo.setWidth(newsInfoLeftObject.getInt("iwd"));
					imageInfo.setHeight(newsInfoLeftObject.getInt("iht"));
					mImageList.add(imageInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mImageList;
	}

}
