/*
 * Copyright (C) 2015 www.lyyj.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.af.activity;

import java.lang.reflect.Field;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.af.global.AfActivityManager;
import com.af.view.ioc.AfIocEventListener;
import com.af.view.ioc.AfIocSelect;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfBottomBar;
import com.af.view.titlebar.AfTitleBar;

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：AfActivity.java 
 * 描述：继承这个Activity使你的Activity拥有一个程序框架.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public abstract class AfActivity extends FragmentActivity {

	/** 全局的LayoutInflater对象，已经完成初始化. */
	public LayoutInflater mInflater;
	
	/** 全局的Application对象，已经完成初始化. */
	public Application mAfApplication = null;
	
	/** 总布局. */
	public RelativeLayout mAfBaseLayout = null;
	
	/** 标题栏布局. */
	private AfTitleBar mAfTitleBar = null;
	
	/** 副标题栏布局. */
	private AfBottomBar mAfBottomBar = null;
	
	/** 主内容布局. */
	protected RelativeLayout mContentLayout = null;
	
	/**
	 * 描述：创建.
	 *
	 * @param savedInstanceState the saved instance state
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mInflater = LayoutInflater.from(this);
		
		//主标题栏
		mAfTitleBar = new AfTitleBar(this);
		
		//最外层布局
		mAfBaseLayout = new RelativeLayout(this);
		mAfBaseLayout.setBackgroundColor(Color.rgb(255, 255, 255));
		
		//内容布局
		mContentLayout = new RelativeLayout(this);
		mContentLayout.setPadding(0, 0, 0, 0);
		
		//副标题栏
		mAfBottomBar = new AfBottomBar(this);
		
        //填入View
		mAfBaseLayout.addView(mAfTitleBar,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		mAfTitleBar.setVisibility(View.GONE);
		
		RelativeLayout.LayoutParams layoutParamsBottomBar = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParamsBottomBar.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		mAfBaseLayout.addView(mAfBottomBar, layoutParamsBottomBar);
		
		RelativeLayout.LayoutParams layoutParamsContent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParamsContent.addRule(RelativeLayout.BELOW, mAfTitleBar.getId());
		layoutParamsContent.addRule(RelativeLayout.ABOVE, mAfBottomBar.getId());
		mAfBaseLayout.addView(mContentLayout, layoutParamsContent);
		
		//Application初始化
		mAfApplication = getApplication();
		
		//设置ContentView
        setContentView(mAfBaseLayout,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        AfActivityManager.getInstance().addActivity(this);
	}
	
	/**
	 * 描述：用指定的View填充主界面.
	 * @param contentView  指定的View
	 */
	public void setAfContentView(View contentView) {
		mContentLayout.removeAllViews();
		mContentLayout.addView(contentView,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		initIocView();
	}
	
	/**
	 * 描述：用指定资源ID表示的View填充主界面.
	 * @param resId  指定的View的资源ID
	 */
	public void setAfContentView(int resId) {
		setAfContentView(mInflater.inflate(resId, null));
	}
	
	/**
	 * 获取主标题栏布局.
	 * @return the title layout
	 */
	public AfTitleBar getTitleBar() {
		mAfTitleBar.setVisibility(View.VISIBLE);
		return mAfTitleBar;
	}
	
	/**
	 * 获取副标题栏布局.
	 * @return the bottom layout
	 */
	public AfBottomBar getBottomBar() {
		return mAfBottomBar;
	}
	
	/**
	 * 设置主标题栏高度.
	 * @param height LayoutParams属性  和具体的大小px
	 */
	public void setTitleBarHeight(int height) {
		ViewGroup.LayoutParams params = mAfTitleBar.getLayoutParams();
		params.height = height;
	    mAfTitleBar.setLayoutParams(params);
	}


	/**
	 * 描述：设置绝对定位的主标题栏覆盖到内容的上边.
	 *
	 * @param overlay the new title bar overlay
	 */
	public void setTitleBarOverlay(boolean overlay) {
		mAfBaseLayout.removeAllViews();
		if(overlay){
			RelativeLayout.LayoutParams layoutParamsFW1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParamsFW1.addRule(RelativeLayout.ABOVE, mAfBottomBar.getId());
			mAfBaseLayout.addView(mContentLayout,layoutParamsFW1);
			RelativeLayout.LayoutParams layoutParamsFW2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParamsFW2.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
			mAfBaseLayout.addView(mAfTitleBar,layoutParamsFW2);
			
			RelativeLayout.LayoutParams layoutParamsFW3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParamsFW3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			mAfBaseLayout.addView(mAfBottomBar, layoutParamsFW3);
			
		}else{
			mAfBaseLayout.addView(mAfTitleBar,new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			RelativeLayout.LayoutParams layoutParamsFW2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParamsFW2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
			mAfBaseLayout.addView(mAfBottomBar, layoutParamsFW2);
			
			RelativeLayout.LayoutParams layoutParamsFW1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParamsFW1.addRule(RelativeLayout.BELOW, mAfTitleBar.getId());
			layoutParamsFW1.addRule(RelativeLayout.ABOVE, mAfBottomBar.getId());
			mAfBaseLayout.addView(mContentLayout, layoutParamsFW1);
		}
	}
	
	/**
	 * 描述：设置界面显示（忽略标题栏）.
	 *
	 * @param layoutResID the new content view
	 * @see android.app.Activity#setContentView(int)
	 */
	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initIocView();
	}

	/**
	 * 描述：设置界面显示（忽略标题栏）.
	 *
	 * @param view the view
	 * @param params the params
	 * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
	 */
	@Override
	public void setContentView(View view,
			android.view.ViewGroup.LayoutParams params) {
		super.setContentView(view, params);
	}

	/**
	 * 描述：设置界面显示（忽略标题栏）.
	 *
	 * @param view the new content view
	 * @see android.app.Activity#setContentView(android.view.View)
	 */
	public void setContentView(View view) {
		super.setContentView(view);
	}
	
	/**
	 * 初始化为IOC控制的View.
	 */
	private void initIocView(){
		Field[] fields = getClass().getDeclaredFields();
		if(fields!=null && fields.length>0){
			for(Field field : fields){
				try {
					field.setAccessible(true);
					
					if(field.get(this)!= null )
						continue;
				
					AfIocView viewInject = field.getAnnotation(AfIocView.class);
					if(viewInject!=null){
						
						int viewId = viewInject.id();
					    field.set(this,findViewById(viewId));
					
						setListener(field,viewInject.click(),AfIocEventListener.CLICK);
						setListener(field,viewInject.longClick(),AfIocEventListener.LONGCLICK);
						setListener(field,viewInject.itemClick(),AfIocEventListener.ITEMCLICK);
						setListener(field,viewInject.itemLongClick(),AfIocEventListener.ITEMLONGCLICK);
						
						AfIocSelect select = viewInject.select();
						if(!TextUtils.isEmpty(select.selected())){
							setViewSelectListener(field,select.selected(),select.noSelected());
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 设置view的监听器.
	 *
	 * @param field the field
	 * @param select the select
	 * @param noSelect the no select
	 * @throws Exception the exception
	 */
	private void setViewSelectListener(Field field,String select,String noSelect)throws Exception{
		Object obj = field.get(this);
		if(obj instanceof View){
			((AbsListView)obj).setOnItemSelectedListener(new AfIocEventListener(this).select(select).noSelect(noSelect));
		}
	}
	
	/**
	 * 设置view的监听器.
	 *
	 * @param field the field
	 * @param methodName the method name
	 * @param method the method
	 * @throws Exception the exception
	 */
	private void setListener(Field field,String methodName,int method)throws Exception{
		if(methodName == null || methodName.trim().length() == 0)
			return;
		
		Object obj = field.get(this);
		
		switch (method) {
			case AfIocEventListener.CLICK:
				if(obj instanceof View){
					((View)obj).setOnClickListener(new AfIocEventListener(this).click(methodName));
				}
				break;
			case AfIocEventListener.ITEMCLICK:
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemClickListener(new AfIocEventListener(this).itemClick(methodName));
				}
				break;
			case AfIocEventListener.LONGCLICK:
				if(obj instanceof View){
					((View)obj).setOnLongClickListener(new AfIocEventListener(this).longClick(methodName));
				}
				break;
			case AfIocEventListener.ITEMLONGCLICK:
				if(obj instanceof AbsListView){
					((AbsListView)obj).setOnItemLongClickListener(new AfIocEventListener(this).itemLongClick(methodName));
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * 标题上的返回按钮
	 * @param view
	 */
	public void back(View view){
		finish();
	}
	
	/**
	 * 描述：Activity结束.
	 *
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		super.finish();
	}
	
}
