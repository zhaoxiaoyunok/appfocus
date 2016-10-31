package com.af.global;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

/**
 * 
 * © 2015 lyyj.com
 * 名称：AfActivityManager.java 
 * 描述：用于处理退出程序时可以退出所有的activity，而编写的通用类
 * @author kaka
 * @date 2015年4月10日 下午6:10:28
 * @version v1.0
 */
public class AfActivityManager {

	private List<Activity> activityList = new LinkedList<Activity>();
	private static AfActivityManager instance;

	private AfActivityManager() {
	}

	/**
	 * 单例模式中获取唯一的AbActivityManager实例.
	 * @return
	 */
	public static AfActivityManager getInstance() {
		if (null == instance) {
			instance = new AfActivityManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到容器中.
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 遍历所有Activity并finish.
	 */
	public void clearAllActivity() {
		for (Activity activity : activityList) {
			if(activity!=null){
				activity.finish();
			}
		}
	}
}