package com.appfocusbase.demo.activity;

import java.util.Random;

import android.os.Bundle;
import android.widget.TextView;

 












import com.af.activity.AfActivity;
import com.af.fragment.AfDialogFragment.AfDialogOnLoadListener;
import com.af.fragment.AfLoadDialogFragment;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListener;
import com.af.util.AfDialogUtil;
import com.af.view.pullview.AfPullToRefreshView;
import com.af.view.pullview.AfPullToRefreshView.OnFooterLoadListener;
import com.af.view.pullview.AfPullToRefreshView.OnHeaderRefreshListener;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class PullToRefreshViewActivity extends AfActivity implements OnHeaderRefreshListener,OnFooterLoadListener{
	
	private MyApplication application;
	private AfPullToRefreshView mAfPullToRefreshView = null;
	private TextView mTextView = null;
	private AfLoadDialogFragment  mDialogFragment = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.pull_to_refresh_view);
        application = (MyApplication)mAfApplication;
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.pull_list_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
	    //获取ListView对象
        mAfPullToRefreshView = (AfPullToRefreshView)this.findViewById(R.id.mPullRefreshView);
        mTextView = (TextView)this.findViewById(R.id.mTextView);
        
        //设置监听器
        mAfPullToRefreshView.setOnHeaderRefreshListener(this);
        mAfPullToRefreshView.setOnFooterLoadListener(this);
        
        //设置进度条的样式
        mAfPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
        mAfPullToRefreshView.getFooterView().setFooterProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
        
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
	protected void onResume() {
		super.onResume();
	}
	
	public void onPause() {
		super.onPause();
	}
	
	@Override
    public void onFooterLoad(AfPullToRefreshView view) {
	    loadMoreTask();
    }
	
    @Override
    public void onHeaderRefresh(AfPullToRefreshView view) {
        refreshTask();
    }
	
	
	public void refreshTask(){
		AfTask mAbTask = AfTask.newInstance();
        final AfTaskItem item = new AfTaskItem();
        item.setListener(new AfTaskListener() {

            @Override
            public void update() {
            	AfDialogUtil.removeDialog(PullToRefreshViewActivity.this);
                mTextView.setText("This is "+new Random().nextInt(100));
                mAfPullToRefreshView.onHeaderRefreshFinish();
            }

            @Override
            public void get() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
          };
        });
        
        mAbTask.execute(item);
    }
	
	public void loadMoreTask(){
		AfTask mAbTask = AfTask.newInstance();
        final AfTaskItem item = new AfTaskItem();
        item.setListener(new AfTaskListener() {

            @Override
            public void update() {
            	AfDialogUtil.removeDialog(PullToRefreshViewActivity.this);
                mTextView.append("+"+new Random().nextInt(100));
                mAfPullToRefreshView.onFooterLoadFinish();
            }

            @Override
            public void get() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
          };
        });
        
        mAbTask.execute(item);
    }
   
}


