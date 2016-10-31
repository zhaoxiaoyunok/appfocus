
package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.af.fragment.AfFragment;
import com.af.util.AfFileUtil;
import com.af.util.AfJsonUtil;
import com.af.util.AfToastUtil;
import com.af.view.pullview.AfPullToRefreshView;
import com.af.view.pullview.AfPullToRefreshView.OnFooterLoadListener;
import com.af.view.pullview.AfPullToRefreshView.OnHeaderRefreshListener;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.ArticleListAdapter;
import com.appfocusbase.demo.model.Article;
import com.appfocusbase.demo.model.ArticleListResult;
import com.appfocusbase.global.MyApplication;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;



public class FragmentRefresh extends AfFragment {
	
	private MyApplication application;
	private Activity mActivity = null;
	private List<Article> mList = null;
	private AfPullToRefreshView mAfPullToRefreshView = null;
	private ListView mListView = null;
	private int currentPage = 1;
	private ArticleListAdapter myListViewAdapter = null;
	private int total = 50;
	private int pageSize = 5;

	@Override
	public View onCreateContentView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) { 
		
		 mActivity = this.getActivity();
		 application = (MyApplication) mActivity.getApplication();
		
		 View view = inflater.inflate(R.layout.pull_to_refresh_list, null);
		 //获取ListView对象
         mAfPullToRefreshView = (AfPullToRefreshView)view.findViewById(R.id.mPullRefreshView);
         mListView = (ListView)view.findViewById(R.id.mListView);
        
         //设置监听器
         mAfPullToRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
			
			@Override
			public void onHeaderRefresh(AfPullToRefreshView view) {
				refreshTask();
			}
		});
         mAfPullToRefreshView.setOnFooterLoadListener(new OnFooterLoadListener() {
			
			@Override
			public void onFooterLoad(AfPullToRefreshView view) {
				loadMoreTask();
				
			}
		});
        
         //设置进度条的样式
         mAfPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
         mAfPullToRefreshView.getFooterView().setFooterProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
         
         //ListView数据
         mList = new ArrayList<Article>();
    	
    	 //使用自定义的Adapter
    	 myListViewAdapter = new ArticleListAdapter(mActivity, mList);
    	 mListView.setAdapter(myListViewAdapter);
    	 //item被点击事件
    	 mListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
    	 });
    	 
    	 //加载数据必须
    	 this.setAbFragmentOnLoadListener(new AbFragmentOnLoadListener(){

			@Override
			public void onLoad() {
				//第一次下载数据
				refreshTask();
			}
    		 
    	 });
    	 
    	 return view;
	} 
	
	@Override
	public void setResource() {
		//设置加载的资源
		this.setLoadDrawable(R.drawable.ic_load);
		this.setLoadMessage("正在查询,请稍候");
		 
		this.setRefreshDrawable(R.drawable.ic_refresh);
		this.setRefreshMessage("请求出错，请重试");
	}


	/**
	 * 下载数据
	 */
	public void refreshTask() {
		currentPage = 1;
		final String content = AfFileUtil.readAssetsByName(mActivity, "article_list.json","UTF-8");
		
		FutureCallback<String> callback =new FutureCallback< String> (){

			@Override
			public void onCompleted(Exception e, String result) {

				if((e !=null)||(result==null)){
					AfToastUtil.showToast(mActivity,"网络访问错误");
					return;
				}
				ArticleListResult mArticleListResult = (ArticleListResult)AfJsonUtil.fromJson(content,ArticleListResult.class);
				List<Article> articleList = mArticleListResult.getItems();

				mList.clear();
				if(articleList!=null && articleList.size()>0){
					mList.addAll((List<Article>)articleList);
	                myListViewAdapter.notifyDataSetChanged();
	                articleList.clear();
   		    	}
				mAfPullToRefreshView.onHeaderRefreshFinish();
				
				//模拟用，真是开发中需要直接调用run内的内容
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						//显示内容
						showContentView();
					}
					
				}, 3000);
		
			}			
		};
		Ion.with(mActivity)
		.load("http://www.amsoft.cn/rss.php")
		.setBodyParameter("cityCode", "11")
		.setBodyParameter("pageSize", String.valueOf(pageSize))
		.setBodyParameter("toPageNo",String.valueOf(currentPage))
		.asString()
		.setCallback(callback);			
}


    
    public void loadMoreTask(){
    	currentPage++;
		final String content = AfFileUtil.readAssetsByName(mActivity, "article_list.json","UTF-8");
		
		FutureCallback<String> callback =new FutureCallback< String> (){

			@Override
			public void onCompleted(Exception e, String result) {

				if((e !=null)||(result==null)){
					AfToastUtil.showToast(mActivity,"网络访问错误");
					return;
				}
				ArticleListResult mArticleListResult = (ArticleListResult)AfJsonUtil.fromJson(content,ArticleListResult.class);
				List<Article> articleList = mArticleListResult.getItems();

				mList.clear();
				if(articleList!=null && articleList.size()>0){
					mList.addAll((List<Article>)articleList);
	                myListViewAdapter.notifyDataSetChanged();
	                articleList.clear();
   		    	}
				mAfPullToRefreshView.onHeaderRefreshFinish();
				
				//模拟用，真是开发中需要直接调用run内的内容
				new Handler().postDelayed(new Runnable(){

					@Override
					public void run() {
						//显示内容
						showContentView();
					}
					
				}, 3000);
		
			}			
		};
		Ion.with(mActivity)
		.load("http://www.amsoft.cn/rss.php")
		.setBodyParameter("cityCode", "11")
		.setBodyParameter("pageSize", String.valueOf(pageSize))
		.setBodyParameter("toPageNo",String.valueOf(currentPage))
		.asString()
		.setCallback(callback);		
    }
}

