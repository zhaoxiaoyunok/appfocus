package com.appfocusbase.demo.activity;

import java.util.Calendar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.af.activity.AfActivity;
import com.af.util.AfDialogUtil;
import com.af.view.titlebar.AfTitleBar;
import com.af.view.wheel.AfNumericWheelAdapter;
import com.af.view.wheel.AfWheelUtil;
import com.af.view.wheel.AfWheelView;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class WheelActivity extends AfActivity {
	private MyApplication application;
	private View mTimeView1 = null;
	private View mTimeView2 = null;
	private View mTimeView3 = null;
	private View mDataView1 = null;
	
	private TextView timeTextView1 = null;
	private TextView timeTextView2 = null;
	private TextView timeTextView3 = null;
	private TextView mDataTextView1= null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.wheel);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.wheel_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        timeTextView1 = (TextView)findViewById(R.id.measureTimeText1);
        timeTextView2 = (TextView)findViewById(R.id.measureTimeText2);
        timeTextView3 = (TextView)findViewById(R.id.measureTimeText3);
        mDataTextView1 = (TextView)findViewById(R.id.dataTextView1);
        
        timeTextView1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mTimeView1 = mInflater.inflate(R.layout.choose_three, null);
				initWheelDate(mTimeView1,timeTextView1);
				AfDialogUtil.showDialog(mTimeView1,Gravity.BOTTOM);
			}
			
		});
        
        timeTextView2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mTimeView2 = mInflater.inflate(R.layout.choose_three, null);
				initWheelTime(mTimeView2,timeTextView2);
				AfDialogUtil.showDialog(mTimeView2,Gravity.BOTTOM);
			}
			
		});
        
        timeTextView3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mTimeView3 = mInflater.inflate(R.layout.choose_two, null);
				initWheelTime2(mTimeView3,timeTextView3);
				AfDialogUtil.showDialog(mTimeView3,Gravity.BOTTOM);
			}
			
		});
        
        mDataTextView1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mDataView1 = mInflater.inflate(R.layout.choose_one, null);
				initWheelData1(mDataView1);
				AfDialogUtil.showDialog(mDataView1,Gravity.BOTTOM);
			}
			
		});
        
    }
    
    
    public void initWheelData1(View mDataView1){
    	final AfWheelView mWheelView1 = (AfWheelView)mDataView1.findViewById(R.id.wheelView1);
		mWheelView1.setAdapter(new AfNumericWheelAdapter(40, 190));
		// 可循环滚动
		mWheelView1.setCyclic(true);
		// 添加文字
		mWheelView1.setLabel(getResources().getString(R.string.data1_unit));
		// 初始化时显示的数据
		mWheelView1.setCurrentItem(40);
		mWheelView1.setValueTextSize(35);
		mWheelView1.setLabelTextSize(35);
		mWheelView1.setLabelTextColor(0x80000000);
		mWheelView1.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		Button okBtn = (Button)mDataView1.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mDataView1.findViewById(R.id.cancelBtn);
		okBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AfDialogUtil.removeDialog(v.getContext());
				int index = mWheelView1.getCurrentItem();
				String val = mWheelView1.getAdapter().getItem(index);
				mDataTextView1.setText(val);
			}
			
		});
		
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AfDialogUtil.removeDialog(v.getContext());
			}
			
		});
    }
    
    
    public void initWheelTime(View mTimeView,TextView mText){
    	final AfWheelView mWheelViewMD = (AfWheelView)mTimeView.findViewById(R.id.wheelView1);
		final AfWheelView mWheelViewMM = (AfWheelView)mTimeView.findViewById(R.id.wheelView2);
		final AfWheelView mWheelViewHH = (AfWheelView)mTimeView.findViewById(R.id.wheelView3);
		Button okBtn = (Button)mTimeView.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mTimeView.findViewById(R.id.cancelBtn);
		mWheelViewMD.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		mWheelViewMM.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		mWheelViewHH.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		AfWheelUtil.initWheelTimePicker(this, mText, mWheelViewMD, mWheelViewMM, mWheelViewHH,okBtn,cancelBtn,2013,1,1,10,0,true);
    }
    
    public void initWheelTime2(View mTimeView,TextView mText){
		final AfWheelView mWheelViewMM = (AfWheelView)mTimeView.findViewById(R.id.wheelView1);
		final AfWheelView mWheelViewHH = (AfWheelView)mTimeView.findViewById(R.id.wheelView2);
		Button okBtn = (Button)mTimeView.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mTimeView.findViewById(R.id.cancelBtn);
		mWheelViewMM.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		mWheelViewHH.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
        AfWheelUtil.initWheelTimePicker2(this, mText,mWheelViewMM, mWheelViewHH,okBtn,cancelBtn,1,1,true);
        //AbWheelUtil.initWheelTimePicker2(this, mText,mWheelViewMM, mWheelViewHH,okBtn,cancelBtn,16,23,false);
		
    }
    
    public void initWheelDate(View mDateView,TextView mText){
    	//年月日时间选择器
        Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		final AfWheelView mWheelViewY = (AfWheelView)mDateView.findViewById(R.id.wheelView1);
		final AfWheelView mWheelViewM = (AfWheelView)mDateView.findViewById(R.id.wheelView2);
		final AfWheelView mWheelViewD = (AfWheelView)mDateView.findViewById(R.id.wheelView3);
		Button okBtn = (Button)mDateView.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mDateView.findViewById(R.id.cancelBtn);
		mWheelViewY.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		mWheelViewM.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		mWheelViewD.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		AfWheelUtil.initWheelDatePicker(this, mText, mWheelViewY, mWheelViewM, mWheelViewD,okBtn,cancelBtn, year,month,day, year, 120, false);
    }
    
    
}


