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
package com.appfocusbase.view.carousel;

import java.util.List;

import com.af.util.AfImageUtil;
import com.af.util.AfViewUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;


// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：CarouselViewAdapter.java 
 * 描述：自定义View适配的Carousel
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class CarouselViewAdapter extends BaseAdapter {

	/** The m context. */
	private Context mContext;
	
	/** The m views. */
	private List<View> mViews;
	
	/** The m reflected. */
	private boolean mReflected = true;
	
	/** The m carousel image views. */
	private CarouselItemView[]  mCarouselItemViews = null;

	/**
	 * Instantiates a new carousel view adapter.
	 *
	 * @param c the c
	 * @param views the views
	 * @param reflected 反射镜面效果
	 */
	public CarouselViewAdapter(Context c,List<View> views,boolean reflected) {
		mContext = c;
		mViews = views;
		mReflected = reflected;
		setImages();
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @return the count
	 * @see android.widget.Adapter#getCount()
	 * @author: amsoft.cn
	 * 
	 */
	public int getCount() {
		if (mViews == null){
			return 0;
		}else{
			return mViews.size();
		}
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param position the position
	 * @return the item
	 * @see android.widget.Adapter#getItem(int)
	 * @author: amsoft.cn
	 * 
	 */
	public Object getItem(int position) {
		return position;
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param position the position
	 * @return the item id
	 * @see android.widget.Adapter#getItemId(int)
	 * @author: amsoft.cn
	 * 
	 */
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param position the position
	 * @param convertView the convert view
	 * @param parent the parent
	 * @return the view
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 * @author: amsoft.cn
	 * 
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mCarouselItemViews[position];
		return convertView;
	}
	
	/**
	 * Sets the images.
	 */
	public void setImages(){
		mCarouselItemViews = new CarouselItemView[mViews.size()];
		for(int i = 0; i< mViews.size(); i++){
			final int index = i;
			View view = mViews.get(i);
			
			CarouselItemView itemView = new CarouselItemView(mContext);
			itemView.setIndex(i);
			
			if(mReflected){
				Bitmap originalImage = AfImageUtil.view2Bitmap(view);
				ImageView imageView = new ImageView(mContext);
				LayoutParams  mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				mLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
				imageView.setScaleType(ScaleType.CENTER);
				imageView.setImageBitmap(AfImageUtil.toReflectionBitmap(originalImage));
				itemView.addView(imageView,mLayoutParams);
				imageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						//((AbActivity)mContext).showToast("点击了:"+index);
					}
				});
				
			}else{
				AfViewUtil.measureView(view);
				LayoutParams  mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				mLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
				itemView.addView(view,mLayoutParams);
				view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						//((AbActivity)mContext).showToast("点击了:"+index);
					}
				});
			}
			
			mCarouselItemViews[i] = itemView;
			
		}
		
	}
	
	

}
