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

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AfLevelSeriesDataset.
 */
public class AfLevelSeriesDataset implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The m series. */
	private AfLevelSeries mSeries = null;

	/**
	 * Gets the m series.
	 *
	 * @return the m series
	 */
	public AfLevelSeries getmSeries() {
		return mSeries;
	}

	/**
	 * Sets the m series.
	 *
	 * @param mSeries the new m series
	 */
	public void setmSeries(AfLevelSeries mSeries) {
		this.mSeries = mSeries;
	}

}
