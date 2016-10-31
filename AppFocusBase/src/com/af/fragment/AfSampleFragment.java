package com.af.fragment;

//import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * © 2015 lyyj.com 
 * 名称：AfSampleFragment.java 
 * 描述：全屏的Fragment
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfSampleFragment extends DialogFragment {

	private View mContentView;

	public AfSampleFragment() {
		super();
	}
	
	/**
	 * Create a new instance of AbSampleFragment.
	 */
	public static AfSampleFragment newInstance() {
		AfSampleFragment f = new AfSampleFragment();
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return mContentView;
	}

	public View getContentView() {
		return mContentView;
	}

	public void setContentView(View mContentView) {
		this.mContentView = mContentView;
	}

}
