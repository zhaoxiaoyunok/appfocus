package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.af.activity.AfActivity;
import com.af.util.AfToastUtil;
import com.appfocusbase.R;
import com.appfocusbase.view.carousel.CarouselAdapter;
import com.appfocusbase.view.carousel.CarouselImageAdapter;
import com.appfocusbase.view.carousel.CarouselImageView;
import com.appfocusbase.view.carousel.CarouselAdapter.OnItemClickListener;
import com.appfocusbase.view.carousel.CarouselAdapter.OnItemSelectedListener;
/**
 * 
 * © 2015 lyyj.com
 * 名称：CarouselImageActivity.java 
 * 描述：图片适配的旋转木马
 * @author kaka
 * 
 * @version v1.0
 */
public class CarouselImageActivity extends AfActivity {
	
	private CarouselImageView carousel = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.carousel_image);
		
		carousel = (CarouselImageView) findViewById(R.id.carousel);
		
		List<Drawable> mDrawables = new ArrayList<Drawable>();
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon1));
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon2));
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon3));
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon4));
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon5));
		mDrawables.add(this.getResources().getDrawable(R.drawable.icon6));
		
		//不支持的动态添加dapter.notifyDataSetChanged();增强滑动的流畅
		
		CarouselImageAdapter adapter = new CarouselImageAdapter(this,mDrawables,true);
		carousel.setAdapter(adapter);
		
		carousel.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(CarouselAdapter<?> parent, View view,
					int position, long id) {
				AfToastUtil.showToast(CarouselImageActivity.this,"Click Position=" + position);
				
			}

		});
		
		carousel.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(CarouselAdapter<?> parent, View view,
					int position, long id) {
				AfToastUtil.showToast(CarouselImageActivity.this,"Selected Position=" + position);
			}

			@Override
			public void onNothingSelected(CarouselAdapter<?> parent) {
			}
			
		});
		
		
	}

}
