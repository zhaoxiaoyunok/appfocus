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
package com.af.view.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：AfInnerListView.java 
 * 描述：这个ListView不会与父亲是个ScrollView与List的产生事件冲突
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfInnerListView extends ListView {

	/** The parent scroll view. */
	private ScrollView parentScrollView;
	
	/** The max height. */
	private int maxHeight;

	/**
	 * Gets the parent scroll view.
	 *
	 * @return the parent scroll view
	 */
	public ScrollView getParentScrollView() {
		return parentScrollView;
	}

	/**
	 * Sets the parent scroll view.
	 *
	 * @param parentScrollView the new parent scroll view
	 */
	public void setParentScrollView(ScrollView parentScrollView) {
		this.parentScrollView = parentScrollView;
	}

	/**
	 * Gets the max height.
	 *
	 * @return the max height
	 */
	public int getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Sets the max height.
	 *
	 * @param maxHeight the new max height
	 */
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	/**
	 * Instantiates a new ab inner list view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AfInnerListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param widthMeasureSpec the width measure spec
	 * @param heightMeasureSpec the height measure spec
	 * @see android.widget.ListView#onMeasure(int, int)
	 * @author: amsoft.cn
	 * 
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (maxHeight > -1) {
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight,
					MeasureSpec.AT_MOST);
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param ev the ev
	 * @return true, if successful
	 * @see android.widget.AbsListView#onInterceptTouchEvent(android.view.MotionEvent)
	 * @author: amsoft.cn
	 * 
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:

			setParentScrollAble(false);
		case MotionEvent.ACTION_MOVE:

			break;
		case MotionEvent.ACTION_UP:

		case MotionEvent.ACTION_CANCEL:
			setParentScrollAble(true);
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	/**
	 * Sets the parent scroll able.
	 *
	 * @param flag the new parent scroll able
	 */
	private void setParentScrollAble(boolean flag) {
		parentScrollView.requestDisallowInterceptTouchEvent(!flag);
	}

}
