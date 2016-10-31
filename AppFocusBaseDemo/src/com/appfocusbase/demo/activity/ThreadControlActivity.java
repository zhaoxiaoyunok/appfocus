package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListener;
import com.af.task.AfTaskObjectListener;
import com.af.task.thread.AfTaskPool;
import com.af.task.thread.AfTaskQueue;
import com.af.util.AfDialogUtil;
import com.af.util.AfToastUtil;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 异步的使用参照
 * http://www.amsoft.cn/post-133.html
 * @author kaka
 *
 */
public class ThreadControlActivity extends AfActivity {
	
	private MyApplication application;
	private AfTitleBar mAfTitleBar = null;
	private AfTaskQueue mAbTaskQueue;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.thread_main);
        application = (MyApplication)mAfApplication;
        
        mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.thread_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
        initTitleRightLayout();
        
        Button threadBtn  = (Button)this.findViewById(R.id.threadBtn);
        Button queueBtn  = (Button)this.findViewById(R.id.queueBtn);
        Button poolBtn  = (Button)this.findViewById(R.id.poolBtn);
        Button taskBtn1  = (Button)this.findViewById(R.id.taskBtn1);
        Button taskBtn2  = (Button)this.findViewById(R.id.taskBtn2);
        
        //单个线程
        threadBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//显示进度框
				AfDialogUtil.showProgressDialog(ThreadControlActivity.this,R.drawable.progress_circular,"正在查询...");
				AfTask mAbTask = AfTask.newInstance();
				//定义异步执行的对象
		    	final AfTaskItem item = new AfTaskItem();
				item.setListener(new AfTaskListener() {

					@Override
					public void update() {
						AfDialogUtil.removeDialog(ThreadControlActivity.this);
						AfToastUtil.showToast(ThreadControlActivity.this,"执行完成");
					}

					@Override
					public void get() {
			   		    try {
			   		    	AfToastUtil.showToastInThread(ThreadControlActivity.this,"开始执行");
			   		    	Thread.sleep(3000);
			   		    	//下面写要执行的代码，如下载数据
			   		    } catch (Exception e) {
			   		    }
				  };
				});
				//开始执行
				mAbTask.execute(item);
			}
        	
        });
        
        //线程队列
        mAbTaskQueue = AfTaskQueue.newInstance();
        queueBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//显示进度框
				AfDialogUtil.showProgressDialog(ThreadControlActivity.this,R.drawable.progress_circular,"正在查询...");
				//获取队列
				//定义异步执行的对象
		    	AfTaskItem item1 = new AfTaskItem();
				item1.setListener(new AfTaskObjectListener() {

					
					@SuppressWarnings("unchecked")
					@Override
					public String getObject() {
						String msg1 = "amsoft";
						AfToastUtil.showToastInThread(ThreadControlActivity.this,"开始执行1,"+msg1);
		   		    	try {
							Thread.sleep(2000);
						} catch (Exception e) {
						}
		   		    	//下面写要执行的代码，如下载数据
						return msg1;
					}

					@Override
					public <T> void update(T obj) {
						AfToastUtil.showToast(ThreadControlActivity.this,"执行完成1,"+(String)obj);
					}

				});
				
				AfTaskItem item2 = new AfTaskItem();
				item2.setListener(new AfTaskListener() {

					@Override
					public void update() {
						AfToastUtil.showToast(ThreadControlActivity.this,"执行完成2");
						AfDialogUtil.removeDialog(ThreadControlActivity.this);
					}

					@Override
					public void get() {
			   		    try {
			   		    	String msg1 = "amsoft";
			   		    	Thread.sleep(2000);
			   		    	AfToastUtil.showToastInThread(ThreadControlActivity.this,"开始执行2");
			   		    	//下面写要执行的代码，如下载数据
			   		    } catch (Exception e) {
			   		    }
				  };
				});
				
				//开始执行
				mAbTaskQueue.execute(item1);
				mAbTaskQueue.execute(item2);
				
				//强制停止
				//mAbTaskQueue.cancel(true);
				
				//强制停止前面的请求
				//mAbTaskQueue.execute(item2,true);
			}
        	
        });
        
        
        //线程池
        poolBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//显示进度框
				AfDialogUtil.showProgressDialog(ThreadControlActivity.this,R.drawable.progress_circular,"正在查询...");
				AfTaskPool mAbTaskPool = AfTaskPool.getInstance();
				//定义异步执行的对象
		    	final AfTaskItem item = new AfTaskItem();
				item.setListener(new AfTaskListener() {

					@Override
					public void update() {
						AfDialogUtil.removeDialog(ThreadControlActivity.this);
						AfToastUtil.showToast(ThreadControlActivity.this,"执行完成");
					}

					@Override
					public void get() {
			   		    try {
			   		    	AfToastUtil.showToastInThread(ThreadControlActivity.this,"开始执行");
			   		    	Thread.sleep(1000);
			   		    	//下面写要执行的代码，如下载数据
			   		    } catch (Exception e) {
			   		    }
				  };
				});
				//开始执行
				mAbTaskPool.execute(item);
			}
        	
        });
        
        //异步任务(void)
        taskBtn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AfDialogUtil.showProgressDialog(ThreadControlActivity.this,R.drawable.progress_circular,"正在查询...");
				AfTask task = AfTask.newInstance();
				//定义异步执行的对象
		    	final AfTaskItem item = new AfTaskItem();
				item.setListener(new AfTaskListener() {

					@Override
					public void update() {
						AfDialogUtil.removeDialog(ThreadControlActivity.this);
						AfToastUtil.showToast(ThreadControlActivity.this,"执行完成");
					}

					@Override
					public void get() {
			   		    try {
			   		    	AfToastUtil.showToastInThread(ThreadControlActivity.this,"开始执行");
			   		    	Thread.sleep(3000);
			   		    	//下面写要执行的代码，如下载数据
			   		    } catch (Exception e) {
			   		    }
				  };
				});
		        task.execute(item);
			}
        	
        });
        
        //异步任务(对象)
        taskBtn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AfDialogUtil.showProgressDialog(ThreadControlActivity.this,R.drawable.progress_circular,"正在查询...");
			    //执行任务
			    loadObjectDataTask();
			}
        	
        });
        
    }
    
    
    private void initTitleRightLayout(){
    	mAfTitleBar.clearRightView();
    }

	@Override
	protected void onResume() {
		super.onResume();
		initTitleRightLayout();
	}
	
	public void onPause() {
		super.onPause();
	}
	
	public void loadObjectDataTask(){
        AfTask task = new AfTask();
        final AfTaskItem item = new AfTaskItem();
        item.setListener(new AfTaskObjectListener(){

            @Override
            public <T> void update(T entity) {
                AfDialogUtil.removeDialog(ThreadControlActivity.this);
                AfToastUtil.showToast(ThreadControlActivity.this,(String)entity);
                Log.d("TAG", "执行完成:"+(String)entity);
            }

            @SuppressWarnings("unchecked")
            @Override
            public String getObject() {
                String result = null;
                try {
                    Thread.sleep(3000);
                    result = "hello andbase";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
            
        });
        
        task.execute(item);
        
        //task.cancel(mayInterruptIfRunning)
    }


	@Override
	public void finish() {
		if(mAbTaskQueue!=null){
			mAbTaskQueue.cancel(true);
		}
		super.finish();
	}
	
	
   
}


