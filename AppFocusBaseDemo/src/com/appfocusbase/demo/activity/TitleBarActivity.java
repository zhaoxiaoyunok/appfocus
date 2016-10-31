package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.af.activity.AfActivity;
import com.af.model.AfMenuItem;
import com.af.util.AfToastUtil;
import com.af.view.titlebar.AfBottomBar;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.ListPopAdapter;
import com.appfocusbase.global.MyApplication;
import com.appfocusbase.login.AboutActivity;

public class TitleBarActivity extends AfActivity {
	
	private MyApplication application;
	
	//标题栏
	private AfTitleBar mAfTitleBar = null;
	private AfBottomBar mAfBottomBar = null;
	
	private PopupWindow popupWindow;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.titlebar_main);
        application = (MyApplication)mAfApplication;
        
        mAfTitleBar = this.getTitleBar();
        mAfBottomBar = this.getBottomBar(); 
        mAfTitleBar.setTitleText("多功能标题栏");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(20, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
        
        
        mAfTitleBar.setLogoOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AfToastUtil.showToast(TitleBarActivity.this,"点了返回哦");
				finish();
			}
		});
        
        Button btn1 = (Button) this.findViewById(R.id.btn1);
		Button btn2 = (Button) this.findViewById(R.id.btn2);
		Button btn3 = (Button) this.findViewById(R.id.btn3);
		Button btn4 = (Button) this.findViewById(R.id.btn4);
		Button btn5 = (Button) this.findViewById(R.id.btn5);
		Button btn6 = (Button) this.findViewById(R.id.btn6);
		Button btn7 = (Button) this.findViewById(R.id.btn7);
		Button btn8 = (Button) this.findViewById(R.id.btn8);
		Button btn9 = (Button) this.findViewById(R.id.btn9);
		Button btn10 = (Button) this.findViewById(R.id.btn10);
		
		//显示标题栏
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mAfTitleBar.setVisibility(View.VISIBLE);
			}
		});
		
		//隐藏标题栏
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mAfTitleBar.setVisibility(View.GONE);
			}
		});
		
		//显示右边布局
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mAfTitleBar.clearRightView();
		    	View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		    	View rightViewApp = mInflater.inflate(R.layout.app_btn, null);
		    	mAfTitleBar.addRightView(rightViewApp);
		    	mAfTitleBar.addRightView(rightViewMore);
		    	Button about = (Button)rightViewMore.findViewById(R.id.moreBtn);
		    	Button appBtn = (Button)rightViewApp.findViewById(R.id.appBtn);
		    	
		    	appBtn.setOnClickListener(new View.OnClickListener(){

		 			@Override
		 			public void onClick(View v) {
		 				AfToastUtil.showToast(TitleBarActivity.this,"别点了");
		 			}
		         });
		    	
		    	about.setOnClickListener(new View.OnClickListener(){

		 			@Override
		 			public void onClick(View v) {
		 				AfToastUtil.showToast(TitleBarActivity.this,"还点");
		 			}
		         	
		         });
			}
		});
		
		//删除右边布局
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mAfTitleBar.clearRightView();
			}
		});
		
		//操作栏变换
		btn5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mAfTitleBar.setLogo(R.drawable.button_selector_delete);
				mAfTitleBar.setLogo2(R.drawable.button_selector_app);
				mAfTitleBar.clearRightView();
				View rightViewOk = mInflater.inflate(R.layout.ok_btn, null);
		    	mAfTitleBar.addRightView(rightViewOk);
		    	mAfTitleBar.setTitleText("正在修改");
		    	mAfTitleBar.setTitleBarBackground(R.drawable.top_bg2);
		    	mAfTitleBar.setTitleBarGravity(Gravity.LEFT,Gravity.CENTER);
		    	mAfTitleBar.setLogoOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
						mAfTitleBar.setTitleText("多功能标题栏");
						mAfTitleBar.clearRightView();
						mAfTitleBar.setLogo(R.drawable.button_selector_back);
						mAfTitleBar.setTitleBarGravity(Gravity.LEFT,Gravity.RIGHT);
						mAfTitleBar.setTitleTextMargin(20, 0, 0, 0);
						mAfTitleBar.getLogoView2().setVisibility(View.GONE);
						mAfTitleBar.setLogoOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								finish();
							}
						});
					}
				});
			}
		});
		
		btn6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//关于界面
				Intent intent = new Intent(TitleBarActivity.this,AboutActivity.class); 
				startActivity(intent);
			}
		});
		
		
		btn7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 mAfTitleBar.setTitleTextBackgroundResource(R.drawable.drop_down_title_btn);
				 View popView = mInflater.inflate(R.layout.list_pop, null);
				 ListView popListView = (ListView) popView.findViewById(R.id.pop_list);
				 List<AfMenuItem> list = new ArrayList<AfMenuItem>();
				 list.add(new AfMenuItem("蔡文姬"));
				 list.add(new AfMenuItem("貂蝉"));
				 list.add(new AfMenuItem("紫罂粟"));
				 list.add(new AfMenuItem("孙尚香"));
				 ListPopAdapter mListPopAdapter = new ListPopAdapter(TitleBarActivity.this, list,R.layout.item_list_pop);
				 popListView.setAdapter(mListPopAdapter);
				 
				 mAfTitleBar.setTitleTextDropDown(popView);
			}
		});
		
		btn8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				 mAfTitleBar.setTitleTextBackgroundDrawable(null);
				 mAfTitleBar.setTitleTextOnClickListener(null);
			}
		});
		
		btn9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mAfBottomBar.setVisibility(View.VISIBLE);
				View view = mInflater.inflate(R.layout.bottom_bar, null);
				Button searchBtn = (Button)view.findViewById(R.id.tab_1);
				Button moreBtn = (Button)view.findViewById(R.id.tab_5);
				Button selectBtn = (Button)view.findViewById(R.id.tab_4);
				mAfBottomBar.setBottomView(view);
				View popView = mInflater.inflate(R.layout.list_pop, null);
				ListView popListView = (ListView) popView.findViewById(R.id.pop_list);
				List<AfMenuItem> list = new ArrayList<AfMenuItem>();
				list.add(new AfMenuItem("分享"));
				list.add(new AfMenuItem("收藏"));
				list.add(new AfMenuItem("好评"));
				list.add(new AfMenuItem("搜索"));
				ListPopAdapter mListPopAdapter = new ListPopAdapter(TitleBarActivity.this, list,R.layout.item2_list_pop);
				popListView.setAdapter(mListPopAdapter);
				mAfBottomBar.setDropDown(moreBtn,popView);
				mAfBottomBar.setDropDown(selectBtn,popView);
				mAfBottomBar.setDropDown(searchBtn,popView);
			}
		});
		
		btn10.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mAfBottomBar.setVisibility(View.GONE);
			}
		});
    }   
    
}


