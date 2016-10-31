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
package com.af.view.level;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

// TODO: Auto-generated Javadoc

/**
 * © 2015 lyyj.com
 * 名称：AfLevelView.java 
 * 描述：等级条View.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class AfLevelView extends View {
	
	/** The m context. */
	private Context mContext;
	
	/** The m af level chart. */
	private AfLevelChart mAbLevelChart;
	
	/** The m paint. */
	private Paint mPaint ;
	
	/** The width. */
	private int width;
	
	/** The height. */
	private int height; 
	
	/** 屏幕宽度. */
	private int screenWidth = 0;
	
	/** 屏幕高度. */
	private int screenHeight = 0;


	/**
	 * Instantiates a new ab level view.
	 *
	 * @param context the context
	 * @param abstractChart the abstract chart
	 */
	public AfLevelView(Context context,AfLevelAbstractChart abstractChart) {
		super(context);
		this.mContext = context;
		this.mAbLevelChart = (AfLevelChart)abstractChart;
		mPaint = new Paint();
		DisplayMetrics displayMetrics = new DisplayMetrics(); 
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth = displayMetrics.widthPixels; 
		screenHeight = displayMetrics.heightPixels; 
	}
	

	/**
	 * Instantiates a new ab level view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public AfLevelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Instantiates a new ab level view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AfLevelView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Instantiates a new ab level view.
	 *
	 * @param context the context
	 */
	public AfLevelView(Context context) {
		super(context);
	}



	/**
	 * 描述：绘制.
	 *
	 * @param canvas the canvas
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.width = getMeasuredWidth();
		this.height = getMeasuredHeight();
		mAbLevelChart.draw(canvas, 0, 0, width,height,screenWidth,screenHeight, mPaint);
		
	}
	
}
