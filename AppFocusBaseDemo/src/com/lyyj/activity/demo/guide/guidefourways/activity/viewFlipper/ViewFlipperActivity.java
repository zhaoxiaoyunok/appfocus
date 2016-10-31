package com.lyyj.activity.demo.guide.guidefourways.activity.viewFlipper;

import com.af.view.ioc.AfIocView;
import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.guidefourways.SuccessLaunchActivity;
import com.lyyj.activity.demo.guide.guidefourways.utils.AnimationUtil;
 




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ViewFlipperActivity extends Activity implements OnGestureListener {
	@AfIocView(id = R.id.vf_activity)
	private ViewFlipper mVFActivity;
	private GestureDetector mGestureDetector;
	@AfIocView(id = R.id.tvInNew)
	private TextView tvInNew;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_guide_fourways_activity_viewflipper);
		 
		initView();
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		mGestureDetector = new GestureDetector(this);
		tvInNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ViewFlipperActivity.this,SuccessLaunchActivity.class));
				AnimationUtil.finishActivityAnimation(ViewFlipperActivity.this);
			}
		});
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() > e2.getX()) {
			mVFActivity.showNext();
		} else if (e1.getX() < e2.getX()) {
			mVFActivity.showPrevious();
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

}
