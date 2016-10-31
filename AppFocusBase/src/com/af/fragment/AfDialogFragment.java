package com.af.fragment;

//import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.animation.Animation;

import com.af.util.AfAnimationUtil;
import com.af.util.AfDialogUtil;
/**
 * © 2015 lyyj.com
 * 名称：AfDialogFragment.java 
 * 描述：弹出框的父类
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfDialogFragment extends DialogFragment {

	private View mIndeterminateView = null;
	public String mMessage;
	private DialogInterface.OnCancelListener mOnCancelListener = null;
	private DialogInterface.OnDismissListener mOnDismissListener = null;
	private AfDialogOnLoadListener mAfDialogOnLoadListener = null;

	public AfDialogFragment() {
		super();
	}
	

	@Override
	public void onCancel(DialogInterface dialog) {
		// 用户中断
		if (mOnCancelListener != null) {
			mOnCancelListener.onCancel(dialog);
		}

		super.onCancel(dialog);
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		// 用户隐藏
		if (mOnDismissListener != null) {
			mOnDismissListener.onDismiss(dialog);
		}
		super.onDismiss(dialog);
	}

	public DialogInterface.OnCancelListener getOnCancelListener() {
		return mOnCancelListener;
	}

	public void setOnCancelListener(
			DialogInterface.OnCancelListener onCancelListener) {
		this.mOnCancelListener = onCancelListener;
	}

	public DialogInterface.OnDismissListener getOnDismissListener() {
		return mOnDismissListener;
	}

	public void setOnDismissListener(
			DialogInterface.OnDismissListener onDismissListener) {
		this.mOnDismissListener = onDismissListener;
	}

	
	/**
	 * 加载调用
	 */
	public void load(View v){
		if(mAfDialogOnLoadListener!=null){
			mAfDialogOnLoadListener.onLoad();
		}
		mIndeterminateView = v;
		AfAnimationUtil.playRotateAnimation(mIndeterminateView, 300, Animation.INFINITE,
				Animation.RESTART);
	}

	/**
	 * 加载成功调用
	 */
	public void loadFinish(){
		//停止动画
		loadStop();
		AfDialogUtil.removeDialog(this.getActivity());
	}
	
	/**
	 * 加载结束
	 */
	public void loadStop(){
		//停止动画
		mIndeterminateView.postDelayed(new Runnable(){

			@Override
			public void run() {
				mIndeterminateView.clearAnimation();
			}
			
		}, 200);
		
	}
	
	public AfDialogOnLoadListener getAfDialogOnLoadListener() {
		return mAfDialogOnLoadListener;
	}

	public void setAfDialogOnLoadListener(
			AfDialogOnLoadListener afDialogOnLoadListener) {
		this.mAfDialogOnLoadListener = afDialogOnLoadListener;
	}
	
	public String getMessage() {
		return mMessage;
	}


	public void setMessage(String mMessage) {
		this.mMessage = mMessage;
	}


	/**
	 * 加载事件的接口.
	 */
	public interface AfDialogOnLoadListener {

		/**
		 * 加载
		 */
		public void onLoad();
		
	}

}
