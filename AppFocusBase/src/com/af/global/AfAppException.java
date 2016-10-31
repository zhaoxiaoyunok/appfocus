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
package com.af.global;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

import com.af.util.AfStrUtil;

// TODO: Auto-generated Javadoc

/**
 * © 2015 lyyj.com
 * 名称：AfAppException.java 
 * 描述：公共异常类.
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfAppException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1;

	
	/** 异常消息. */
	private String msg = null;

	/**
	 * 构造异常类.
	 *
	 * @param e 异常
	 */
	public AfAppException(Exception e) {
		super();

		try {
			if( e instanceof HttpHostConnectException) {  
				msg = AfAppConfig.UNKNOWN_HOST_EXCEPTION;
			}else if (e instanceof ConnectException) {
				msg = AfAppConfig.CONNECT_EXCEPTION;
			}else if (e instanceof ConnectTimeoutException) {
				msg = AfAppConfig.CONNECT_EXCEPTION;
			}else if (e instanceof UnknownHostException) {
				msg = AfAppConfig.UNKNOWN_HOST_EXCEPTION;
			}else if (e instanceof SocketException) {
				msg = AfAppConfig.SOCKET_EXCEPTION;
			}else if (e instanceof SocketTimeoutException) {
				msg = AfAppConfig.SOCKET_TIMEOUT_EXCEPTION;
			}else if( e instanceof NullPointerException) {  
				msg = AfAppConfig.NULL_POINTER_EXCEPTION;
			}else if( e instanceof ClientProtocolException) {  
				msg = AfAppConfig.CLIENT_PROTOCOL_EXCEPTION;
			}else {
				if (e == null || AfStrUtil.isEmpty(e.getMessage())) {
					msg = AfAppConfig.NULL_MESSAGE_EXCEPTION;
				}else{
				    msg = e.getMessage();
				}
			}
		} catch (Exception e1) {
		}
		
	}

	/**
	 * 用一个消息构造异常类.
	 *
	 * @param message 异常的消息
	 */
	public AfAppException(String message) {
		super(message);
		msg = message;
	}

	/**
	 * 描述：获取异常信息.
	 *
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return msg;
	}

}
