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

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com 
 * 名称：AfCPUInfo.java 
 * 描述：CPU信息
 * 
 * @author kaka
 * @version v1.0
 * 
 */
public class AfCPUInfo {

	public String User;

	public String System;

	public String IOW;

	public String IRQ;

	public AfCPUInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AfCPUInfo(String user, String system, String iOW, String iRQ) {
		super();
		User = user;
		System = system;
		IOW = iOW;
		IRQ = iRQ;
	}

}
