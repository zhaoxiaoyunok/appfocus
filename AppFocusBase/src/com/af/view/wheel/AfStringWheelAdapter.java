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

import java.util.List;

import com.af.util.AfStrUtil;

// TODO: Auto-generated Javadoc

/**
 * © 2015 lyyj.com
 * 名称：AfStringWheelAdapter.java 
 * 描述：轮子适配器（字符串）
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class AfStringWheelAdapter implements AfWheelAdapter {
	
	/** The default items length. */
	public static final int DEFAULT_LENGTH = -1;
	// items
	/** The items. */
	private List<String> items;
	// length
	/** The length. */
	private int length = -1;
	
	/**
	 * Constructor.
	 *
	 * @param items the items
	 * @param length the max items length
	 */
	public AfStringWheelAdapter(List<String> items, int length) {
		this.items = items;
		this.length = length;
	}
	
	/**
	 * Constructor.
	 *
	 * @param items the items
	 */
	public AfStringWheelAdapter(List<String> items) {
		this(items, DEFAULT_LENGTH);
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
		if (index >= 0 && index < items.size()) {
			return items.get(index);
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
		return items.size();
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
		if(length!=-1){
			return length;
		}
		int maxLength = 0;
		for(int i=0;i<items.size();i++){
			String cur = items.get(i);
			int l = AfStrUtil.strLength(cur);
			if(maxLength<l){
				maxLength = l;
			}
		}
		return maxLength;
	}

}
