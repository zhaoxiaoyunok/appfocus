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

package com.af.view.wheel;

// TODO: Auto-generated Javadoc

/**
 * © 2015 lyyj.com
 * 名称：AfNumericWheelAdapter.java 
 * 描述：轮子适配器（数字）
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class AfNumericWheelAdapter implements AfWheelAdapter {

	/** The default min value. */
	public static final int DEFAULT_MAX_VALUE = 9;

	/** The default max value. */
	private static final int DEFAULT_MIN_VALUE = 0;

	// Values
	/** The min value. */
	private int minValue;

	/** The max value. */
	private int maxValue;

	// format
	/** The format. */
	private String format;

	/**
	 * Default constructor.
	 */
	public AfNumericWheelAdapter() {
		this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	/**
	 * Constructor.
	 *
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 */
	public AfNumericWheelAdapter(int minValue, int maxValue) {
		this(minValue, maxValue, null);
	}

	/**
	 * Constructor.
	 *
	 * @param minValue the wheel min value
	 * @param maxValue the wheel max value
	 * @param format the format string
	 */
	public AfNumericWheelAdapter(int minValue, int maxValue, String format) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.format = format;
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @param index the index
	 * @return the item
	 * @see com.af.view.wheel.AfWheelAdapter#getItem(int)
	 * @author: amsoft.cn
	 * 
	 */
	@Override
	public String getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			int value = minValue + index;
			return format != null ? String.format(format, value) : Integer.toString(value);
		}
		return null;
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @return the items count
	 * @see com.af.view.wheel.AfWheelAdapter#getItemsCount()
	 * @author: amsoft.cn
	 * 
	 */
	@Override
	public int getItemsCount() {
		return maxValue - minValue + 1;
	}

	/**
	 * 描述：TODO.
	 *
	 * @version v1.0
	 * @return the maximum length
	 * @see com.af.view.wheel.AfWheelAdapter#getMaximumLength()
	 * @author: amsoft.cn
	 * 
	 */
	@Override
	public int getMaximumLength() {
		int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
		int maxLen = Integer.toString(max).length();
		if (minValue < 0) {
			maxLen++;
		}
		return maxLen;
	}
}
