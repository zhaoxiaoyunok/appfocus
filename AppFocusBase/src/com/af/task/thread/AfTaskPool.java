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
package com.af.task.thread;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

import com.af.task.AfTaskItem;
import com.af.task.AfTaskListListener;
import com.af.task.AfTaskObjectListener;

import android.os.Handler;
import android.os.Message;
// TODO: Auto-generated Javadoc
/**
 * © 2015 lyyj.com
 * 名称：AfTaskPool.java 
 * 描述：用andbase线程池
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class AfTaskPool{
	
	/** 单例对象 The http pool. */
	private static AfTaskPool abTaskPool = null; 
	
	/** 线程执行器. */
	public static Executor mExecutorService = null;
	
	/**  存放返回的任务结果. */
    private static HashMap<String,Object> result;
	
	/** 下载完成后的消息句柄. */
    private static Handler handler = new Handler() { 
        @Override 
        public void handleMessage(Message msg) { 
        	AfTaskItem item = (AfTaskItem)msg.obj; 
        	if(item.getListener() instanceof AfTaskListListener){
        		((AfTaskListListener)item.getListener()).update((List<?>)result.get(item.toString())); 
        	}else if(item.getListener() instanceof AfTaskObjectListener){
        		((AfTaskObjectListener)item.getListener()).update(result.get(item.toString())); 
        	}else{
        		item.getListener().update(); 
        	}
        	result.remove(item.toString());
        } 
    }; 
    
	
	/**
	 * 构造线程池.
	 */
    private AfTaskPool() {
        result = new HashMap<String,Object>();
        mExecutorService = AfThreadFactory.getExecutorService();
    } 
	
	/**
	 * 单例构造图片下载器.
	 *
	 * @return single instance of AbHttpPool
	 */
    public static AfTaskPool getInstance() { 
    	if (abTaskPool == null) { 
    		abTaskPool = new AfTaskPool(); 
        } 
        return abTaskPool;
    } 
    
    /**
     * 执行任务.
     * @param item the item
     */
    public void execute(final AfTaskItem item) {   
    	mExecutorService.execute(new Runnable() { 
    		public void run() {
    			try {
    				//定义了回调
                    if (item.getListener() != null) { 
                        if(item.getListener() instanceof AfTaskListListener){
                            result.put(item.toString(), ((AfTaskListListener)item.getListener()).getList());
                        }else if(item.getListener() instanceof AfTaskObjectListener){
                            result.put(item.toString(), ((AfTaskObjectListener)item.getListener()).getObject());
                        }else{
                        	item.getListener().get();
                            result.put(item.toString(), null);
                        }
                        
                    	//交由UI线程处理 
                        Message msg = handler.obtainMessage(); 
                        msg.obj = item; 
                        handler.sendMessage(msg); 
                    }                              
    			} catch (Exception e) { 
    				e.printStackTrace();
    			}                         
    		}                 
    	});                 
    	
    }
	
}
