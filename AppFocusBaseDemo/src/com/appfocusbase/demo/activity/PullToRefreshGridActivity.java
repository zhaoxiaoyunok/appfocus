package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.af.activity.AfActivity;
import com.af.fragment.AfDialogFragment.AfDialogOnLoadListener;
import com.af.fragment.AfLoadDialogFragment;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListener;
import com.af.util.AfDialogUtil;
import com.af.util.AfToastUtil;
import com.af.util.AfViewUtil;
import com.af.view.pullview.AfPullToRefreshView;
import com.af.view.pullview.AfPullToRefreshView.OnFooterLoadListener;
import com.af.view.pullview.AfPullToRefreshView.OnHeaderRefreshListener;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.ImageGridAdapter;
import com.appfocusbase.global.Constant;
import com.appfocusbase.global.MyApplication;
import com.appfocusbase.model.User;

public class PullToRefreshGridActivity extends AfActivity implements OnHeaderRefreshListener,OnFooterLoadListener{
	
	private int currentPage = 1;
	private MyApplication application;
	private ArrayList<User> mUserList = null;
	private ArrayList<User> mNewUserList = null;
	private AfPullToRefreshView mAfPullToRefreshView;
	private GridView mGridView = null;
	private ImageGridAdapter myGridViewAdapter = null;
	private ArrayList<String> mPhotoList = new ArrayList<String>();
	private int total = 250;
	private int pageSize = 28;
	private AfLoadDialogFragment  mDialogFragment = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setAfContentView(R.layout.pull_to_refresh_grid);
	    
	    AfTitleBar mAfTitleBar = this.getTitleBar();
	    mAfTitleBar.setTitleText(R.string.pull_list_name);
	    mAfTitleBar.setLogo(R.drawable.button_selector_back);
	    mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
	    mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
	    mAfTitleBar.setLogoLine(R.drawable.line);
        
	    
	    for (int i = 0; i < 23; i++) {
        	mPhotoList.add(Constant.BASEURL+"content/templates/amsoft/images/rand/"+i+".jpg");
		}
	    
		application = (MyApplication) this.getApplication();
		//获取ListView对象
        mAfPullToRefreshView = (AfPullToRefreshView)this.findViewById(R.id.mPullRefreshView);
        mGridView = (GridView)this.findViewById(R.id.mGridView);
        
        //设置监听器
        mAfPullToRefreshView.setOnHeaderRefreshListener(this);
        mAfPullToRefreshView.setOnFooterLoadListener(this);
        
        //设置进度条的样式
        mAfPullToRefreshView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
        mAfPullToRefreshView.getFooterView().setFooterProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular));
        //mAbPullListView.getHeaderView().setHeaderProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular2));
        //mAbPullListView.getFooterView().setFooterProgressBarDrawable(this.getResources().getDrawable(R.drawable.progress_circular2));
		
		mGridView.setColumnWidth(AfViewUtil.scaleValue(this, 200));
		mGridView.setGravity(Gravity.CENTER);
		mGridView.setHorizontalSpacing(5);
		
		//Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
	    //得到一个LayoutAnimationController对象;
	    //LayoutAnimationController lac = new LayoutAnimationController(animation);
		//mGridView.setLayoutAnimation(lac);
		/*AlphaAnimation animationAlpha = new AlphaAnimation(0.0f,1.0f);  
	    //得到一个LayoutAnimationController对象;
	    LayoutAnimationController lac = new LayoutAnimationController(animationAlpha);
	    //设置控件显示的顺序;
	    lac.setOrder(LayoutAnimationController.ORDER_RANDOM);
	    //设置控件显示间隔时间;
	    lac.setDelay(0.5f);
	    //为View设置LayoutAnimationController属性;
		mGridView.setLayoutAnimation(lac);*/

		mGridView.setNumColumns(GridView.AUTO_FIT);
		mGridView.setPadding(0, 0, 0, 0);
		mGridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		mGridView.setVerticalSpacing(20);
		// ListView数据
		mUserList = new ArrayList<User>();
		// 使用自定义的Adapter
		myGridViewAdapter = new ImageGridAdapter(this, mUserList,
				R.layout.item_grid, new String[] { "itemsIcon" },
				new int[] { R.id.itemsIcon });
		mGridView.setAdapter(myGridViewAdapter);
		
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
		
		mGridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				AfToastUtil.showToast(PullToRefreshGridActivity.this,""+position);
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
    
    public void refreshTask(){
    	AfTask mAbTask = AfTask.newInstance();
        //定义两种查询的事件
        final AfTaskItem item = new AfTaskItem();
        item.setListener(new AfTaskListener() {

            @Override
            public void update() {
            	AfDialogUtil.removeDialog(PullToRefreshGridActivity.this);
                mUserList.clear();
                if(mNewUserList!=null && mNewUserList.size()>0){
                    mUserList.addAll(mNewUserList);
                    myGridViewAdapter.notifyDataSetChanged();
                    mNewUserList.clear();
                }
                mAfPullToRefreshView.onHeaderRefreshFinish();
            }

            @Override
            public void get() {
                try {
                    currentPage = 1;
                    Thread.sleep(1000);
                    mNewUserList =  new ArrayList<User>() ;
                    
                    for (int i = 0; i < pageSize; i++) {
                        final User mUser = new User();
                        if(i>=mPhotoList.size()){
                            mUser.setHeadUrl(mPhotoList.get(mPhotoList.size()-1));
                        }else{
                            mUser.setHeadUrl(mPhotoList.get(i));
                        }
                        
                        mNewUserList.add(mUser);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    AfToastUtil.showToastInThread(PullToRefreshGridActivity.this,e.getMessage());
                }
          };
        });
        
        mAbTask.execute(item);
    }
    
    public void loadMoreTask(){
    	AfTask mAfTask = AfTask.newInstance();
        final AfTaskItem item = new AfTaskItem();
        item.setListener(new AfTaskListener() {

            @Override
            public void update() {
                if(mNewUserList!=null && mNewUserList.size()>0){
                    mUserList.addAll(mNewUserList);
                    myGridViewAdapter.notifyDataSetChanged();
                    mNewUserList.clear();
                }
                mAfPullToRefreshView.onFooterLoadFinish();
            }

            @Override
            public void get() {
                try {
                    currentPage++;
                    Thread.sleep(1000);
                    mNewUserList =  new ArrayList<User>() ;
                    for (int i = 0; i < pageSize; i++) {
                        final User mUser = new User();
                        mUser.setHeadUrl(mPhotoList.get(new Random().nextInt(mPhotoList.size())));
                        if((mUserList.size()+mNewUserList.size()) < total){
                            mNewUserList.add(mUser);
                        }
                        
                    }
                } catch (Exception e) {
                    currentPage--;
                    mNewUserList.clear();
                    AfToastUtil.showToastInThread(PullToRefreshGridActivity.this,e.getMessage());
                }
               
          };
        });
        
        mAfTask.execute(item);
    }


}
