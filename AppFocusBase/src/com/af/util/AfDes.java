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
package com.af.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com 
 * 名称：AfDes.java 
 * 描述：DES工具类.
 * 
 * @author kaka
 * @version v1.0
 * 
 */
public class AfDes {

	private byte[] iv;

	public AfDes(byte[] iv) {
		super();
		this.iv = iv;
	}

	public static AfDes newInstance(byte[] iv) {
		AfDes des = new AfDes(iv);
		return des;
	}

	public String encrypt(byte[] encryptByte, String encryptKey) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] encryptedData = cipher.doFinal(encryptByte);
			return AfBase64.encode(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] decrypt(String encryptString, String encryptKey) {
		try {
			byte[] encryptByte = AfBase64.decode(encryptString);
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			return cipher.doFinal(encryptByte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	AfDes des = AfDes.newInstance("yxs!1sdf".getBytes());
    	String a = des.encrypt("ssssss".getBytes(),"bywhjgpt");
    	System.out.println(a);
		System.out.println(des.decrypt(a,"bywhjgpt"));
	}
}