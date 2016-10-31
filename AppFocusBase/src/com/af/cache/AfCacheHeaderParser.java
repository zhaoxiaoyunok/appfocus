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
package com.af.cache;



import java.util.Map;

import org.apache.http.impl.cookie.DateUtils;

// TODO: Auto-generated Javadoc
/**
 * 
 * © 2015 lyyj.com
 * 名称：AfCacheHeaderParser.java 
 * 描述：缓存解析
 * @author kaka
 * @date 2015年4月3日 上午10:01:54
 * @version v1.0
 */
public class AfCacheHeaderParser {
    
    /**
     * 将响应解析成缓存实体.
     *
     * @param response the response
     * @return the ab cache. entry
     */
    public static AfDiskCache.Entry parseCacheHeaders(AfCacheResponse response) {

           Map<String, String> headers = response.headers;

           long serverTimeMillis = 0;
           long expiredTimeMillis = 0;
           long maxAge = 0;
           boolean hasCacheControl = false;

           String serverEtag = null;
           String headerValue;

           //获取响应的内容的时间
           headerValue = headers.get("Date");
           if (headerValue != null) {
        	   try {
				   serverTimeMillis = DateUtils.parseDate(headerValue).getTime();
			   } catch (Exception e) {
				   e.printStackTrace();
				   serverTimeMillis = System.currentTimeMillis();
			   }
           }
           //Cache-Control有值才使用缓存超时的设置
           headerValue = headers.get("Cache-Control");
           if (headerValue != null) {
               hasCacheControl = true;
               String[] tokens = headerValue.split(",");
               for (int i = 0; i < tokens.length; i++) {
                   String token = tokens[i].trim();
                   if (token.equals("no-cache") || token.equals("no-store")) {
                       return null;
                   } else if (token.startsWith("max-age=")) {
                       try {
                           maxAge = Long.parseLong(token.substring(8));
                       } catch (Exception e) {
                       }
                   } else if (token.equals("must-revalidate") || token.equals("proxy-revalidate")) {
                       maxAge = 0;
                   }
               }
           }

           serverEtag = headers.get("ETag");

           if (hasCacheControl) {
        	   expiredTimeMillis = serverTimeMillis + maxAge;
           }

           AfDiskCache.Entry entry = new AfDiskCache.Entry();
           entry.data = response.data;
           entry.etag = serverEtag;
           entry.serverTimeMillis = serverTimeMillis;
           entry.expiredTimeMillis = expiredTimeMillis;
           entry.responseHeaders = headers;

           return entry;
    }
   
}
