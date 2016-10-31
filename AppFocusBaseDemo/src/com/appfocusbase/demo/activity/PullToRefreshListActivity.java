package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.af.activity.AfActivity;
import com.af.fragment.AfDialogFragment.AfDialogOnLoadListener;
import com.af.fragment.AfLoadDialogFragment;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListListener;
import com.af.util.AfDialogUtil;
import com.af.util.AfLogUtil;
import com.af.util.AfToastUtil;
import com.af.view.pullview.AfPullToRefreshView;
import com.af.view.pullview.AfPullToRefreshView.OnFooterLoadListener;
import com.af.view.pullview.AfPullToRefreshView.OnHeaderRefreshListener;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.ImageListAdapter;
import com.appfocusbase.global.Constant;
import com.appfocusbase.global.MyApplication;

public class PullToRefreshListActivity extends AfActivity implements
		OnHeaderRefreshListener, OnFooterLoadListener {

	private MyApplication application;
	private List<Map<String, Object>> list = null;
	private AfPullToRefreshView mAfPullToRefreshView = null;
	private ListView mListView = null;
	private int currentPage = 1;
	private ArrayList<String> mPhotoList = new ArrayList<String>();
	private AfTitleBar mAfTitleBar = null;
	private ImageListAdapter myListViewAdapter = null;
	private int total = 50;
	private int pageSize = 15;
	private AfLoadDialogFragment  mDialogFragment = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.pull_to_refresh_list);
		application = (MyApplication) mAfApplication;

		mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.pull_list_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);

		for (int i = 0; i < 23; i++) {
			mPhotoList.add(Constant.BASEURL
					+ "content/templates/amsoft/images/rand/" + i + ".jpg");
		}

		// 获取ListView对象
		mAfPullToRefreshView = (AfPullToRefreshView) this
				.findViewById(R.id.mPullRefreshView);
		mListView = (ListView) this.findViewById(R.id.mListView);

		// 设置监听器
		mAfPullToRefreshView.setOnHeaderRefreshListener(this);
		mAfPullToRefreshView.setOnFooterLoadListener(this);

		// 设置进度条的样式
		mAfPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(
				this.getResources().getDrawable(R.drawable.progress_circular));
		mAfPullToRefreshView.getFooterView().setFooterProgressBarDrawable(
				this.getResources().getDrawable(R.drawable.progress_circular));

		// ListView数据
		list = new ArrayList<Map<String, Object>>();

		// 使用自定义的Adapter
		myListViewAdapter = new ImageListAdapter(this, list,
				R.layout.item_list, new String[] { "itemsIcon", "itemsTitle",
						"itemsText" }, new int[] { R.id.itemsIcon,
						R.id.itemsTitle, R.id.itemsText });
		mListView.setAdapter(myListViewAdapter);

		// item被点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});

		//显示进度框
		mDialogFragment = AfDialogUtil.showLoadDialog(this, R.drawable.ic_load, "查询中,请等一小会");
		mDialogFragment
		.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				refreshTask();
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
	
	public void refreshTask() {
		AfLogUtil.prepareLog(PullToRefreshListActivity.class);
		AfTask mAfTask = AfTask.newInstance();
		final AfTaskItem item = new AfTaskItem();
		item.setListener(new AfTaskListListener() {
			@Override
			public List<?> getList() {
				List<Map<String, Object>> newList = null;
				try {
					Thread.sleep(1000);
					currentPage = 1;
					newList = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = null;

					for (int i = 0; i < pageSize; i++) {
						map = new HashMap<String, Object>();
						map.put("itemsIcon", mPhotoList.get(i));
						map.put("itemsTitle", "item" + (i + 1));
						map.put("itemsText", "item..." + (i + 1));
						newList.add(map);

					}
				} catch (Exception e) {
				}
				return newList;
			}

			@Override
			public void update(List<?> paramList) {
				
				//通知Dialog
				mDialogFragment.loadFinish();
				AfLogUtil.d(PullToRefreshListActivity.class, "返回", true);
				List<Map<String, Object>> newList = (List<Map<String, Object>>) paramList;
				list.clear();
				if (newList != null && newList.size() > 0) {
					list.addAll(newList);
					myListViewAdapter.notifyDataSetChanged();
					newList.clear();
				}
				mAfPullToRefreshView.onHeaderRefreshFinish();
			}

		});

		mAfTask.execute(item);
	}

	public void loadMoreTask() {
		AfTask mAfTask = AfTask.newInstance();
		final AfTaskItem item = new AfTaskItem();
		item.setListener(new AfTaskListListener() {

			@Override
			public void update(List<?> paramList) {
				List<Map<String, Object>> newList = (List<Map<String, Object>>) paramList;
				if (newList != null && newList.size() > 0) {
					list.addAll(newList);
					myListViewAdapter.notifyDataSetChanged();
					newList.clear();
				}
				mAfPullToRefreshView.onFooterLoadFinish();

			}

			@Override
			public List<?> getList() {
				List<Map<String, Object>> newList = null;
				try {
					currentPage++;
					Thread.sleep(1000);
					newList = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = null;

					for (int i = 0; i < pageSize; i++) {
						map = new HashMap<String, Object>();
						map.put("itemsIcon", mPhotoList.get(i));
						map.put("itemsTitle", "item上拉"
								+ ((currentPage - 1) * pageSize + (i + 1)));
						map.put("itemsText", "item上拉..."
								+ ((currentPage - 1) * pageSize + (i + 1)));
						if ((list.size() + newList.size()) < total) {
							newList.add(map);
						}
					}

				} catch (Exception e) {
					currentPage--;
					newList.clear();
					AfToastUtil.showToastInThread(
							PullToRefreshListActivity.this, e.getMessage());
				}
				return newList;
			};
		});

		mAfTask.execute(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}


}
