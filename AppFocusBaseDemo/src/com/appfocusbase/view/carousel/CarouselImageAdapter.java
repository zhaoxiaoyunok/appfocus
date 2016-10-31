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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：CarouselImageAdapter.java 
 * 描述：只有图片适配的Carousel
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class CarouselImageAdapter extends BaseAdapter {

	/** The m context. */
	private Context mContext;
	
	/** The m drawables. */
	private List<Drawable> mDrawables;
	
	/** The m reflected. */
	private boolean mReflected = true;
	
	/** The m carousel image views. */
	private CarouselItemImage[]  mCarouselImageViews = null;

	/**
	 * Instantiates a new carousel image adapter.
	 *
	 * @param c the c
	 * @param drawables the drawables
	 * @param reflected 反射镜面效果
	 */
	public CarouselImageAdapter(Context c,List<Drawable> drawables,boolean reflected) {
		mContext = c;
		mDrawables = drawables;
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
		if (mDrawables == null){
			return 0;
		}else{
			return mDrawables.size();
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
		
		return mCarouselImageViews[position];
	}
	
	/**
	 * Sets the images.
	 */
	public void setImages(){
		mCarouselImageViews = new CarouselItemImage[mDrawables.size()];
		for(int i = 0; i< mDrawables.size(); i++){
			Drawable drawable = mDrawables.get(i);
			Bitmap originalImage = ((BitmapDrawable)drawable).getBitmap();
			
			if(mReflected){
				originalImage = AfImageUtil.toReflectionBitmap(originalImage);
			}
			
			CarouselItemImage imageView = new CarouselItemImage(mContext);
			imageView.setImageBitmap(originalImage);
			imageView.setIndex(i);
			
			mCarouselImageViews[i] = imageView;
		}
		
		
	}

}
