package com.lyyj.activity.demo.guide.guidefourways.activity.viewPage;

import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.guidefourways.SuccessLaunchActivity;
import com.lyyj.activity.demo.guide.guidefourways.utils.AnimationUtil;
 


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class Fragment4 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.lyyj_guide_fourways_fragment_4, container, false);
		view.findViewById(R.id.tvInNew).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						// TODO Auto-generated method stub
						startActivity(new Intent(getActivity(),
								SuccessLaunchActivity.class));
						AnimationUtil.finishActivityAnimation(getActivity());
					}
				});
		return view;
	}

}
