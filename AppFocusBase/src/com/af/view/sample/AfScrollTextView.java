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
import android.widget.TextView;

// TODO: Auto-generated Javadoc

/**
 * © 2015 lyyj.com
 * 名称：AfScrollTextView.java 
 * 描述：跑马灯一直跑
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfScrollTextView extends TextView {

	/**
	 * Instantiates a new ab scroll text view.
	 *
	 * @param context the context
	 */
	public AfScrollTextView(Context context) {
		super(context);
	}

	/**
	 * Instantiates a new ab scroll text view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public AfScrollTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Instantiates a new ab scroll text view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 * @param defStyle the def style
	 */
	public AfScrollTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 描述：设置为焦点，能一直滚动.
	 */
	@Override
	public boolean isFocused() {
		return true;
	}

}
