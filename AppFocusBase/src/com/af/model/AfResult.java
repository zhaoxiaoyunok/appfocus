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
package com.af.model;

import com.af.util.AfJsonUtil;

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：AfResult.java 
 * 描述：结果
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfResult {
	
	/** 返回码：成功. */
    public static final int RESULT_OK = 0;
    
    /** 返回码：失败. */
    public static final int RESULT_ERROR = -1;

	/** 结果code. */
	private int resultCode;
	
	/** 结果 message. */
	private String resultMessage;
	
    /**
     * 构造
     */
	public AfResult() {
		super();
	}

	/**
	 * 构造
	 * @param resultCode
	 * @param resultMessage
	 */
	public AfResult(int resultCode, String resultMessage) {
		super();
		this.resultCode = resultCode;
		this.resultMessage = resultMessage;
	}
	
	/**
	 * 用json构造自己
	 * @param json
	 */
	public AfResult(String json) {
		super();
		AfResult result = (AfResult)AfJsonUtil.fromJson(json, this.getClass());
		this.resultCode = result.getResultCode();
		this.resultMessage = result.getResultMessage();
	}

	/**
	 * 获取返回码.
	 *
	 * @return the result code
	 */
	public int getResultCode() {
		return resultCode;
	}

	/**
	 * 设置返回码.
	 *
	 * @param resultCode the new result code
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	/**
	 * 获取返回信息.
	 *
	 * @return the result message
	 */
	public String getResultMessage() {
		return resultMessage;
	}

	/**
	 * 设置返回信息.
	 *
	 * @param resultMessage the new result message
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
	/**
	 * 
	 * 描述：转换成json.
	 * @return
	 */
	public String toJson(){
		return AfJsonUtil.toJson(this);
	}
	

}
