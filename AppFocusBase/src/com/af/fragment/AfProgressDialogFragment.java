package com.af.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
//import android.app.DialogFragment;
//import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
/**
 * © 2015 lyyj.com
 * 名称：AfLoadDialogFragment.java 
 * 描述：弹出的进度框
 *
 * @author kaka
 * @version v1.0
 * 
 */
public class AfProgressDialogFragment extends DialogFragment {
	
	int mIndeterminateDrawable;
	String mMessage;
	static View mContentView;
	
	/**
	 * Create a new instance of AbProgressDialogFragment.
	 */
	public static AfProgressDialogFragment newInstance(int indeterminateDrawable,String message) {
		AfProgressDialogFragment f = new AfProgressDialogFragment();
		Bundle args = new Bundle();
		args.putInt("indeterminateDrawable", indeterminateDrawable);
		args.putString("message", message);
		f.setArguments(args);

		return f;
	}
	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIndeterminateDrawable = getArguments().getInt("indeterminateDrawable");
		mMessage = getArguments().getString("message");
		
		ProgressDialog mProgressDialog = new ProgressDialog(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
		if(mIndeterminateDrawable > 0){
			mProgressDialog.setIndeterminateDrawable(getActivity().getResources().getDrawable(mIndeterminateDrawable));
		}
		
		if(mMessage != null){
			mProgressDialog.setMessage(mMessage);
		}
		
	    return mProgressDialog;
	}
	
}
