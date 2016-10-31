package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.af.activity.AfActivity;
import com.af.view.app.AfNumberClock;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class NumberClockActivity extends AfActivity {
	
	private MyApplication application;
	private AfTitleBar mAfTitleBar = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.number_clock);
        application = (MyApplication)mAfApplication;
        
        mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.number_clock_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
        initTitleRightLayout();
        
        Drawable timeBg = this.getResources().getDrawable(R.drawable.timer_bg);
        Drawable timeColon = this.getResources().getDrawable(R.drawable.timer_colon);
        
		List<Drawable> dTimeArray = new ArrayList<Drawable>();
		List<Drawable> dApmArray = new ArrayList<Drawable>();
		
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time0));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time1));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time2));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time3));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time4));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time5));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time6));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time7));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time8));
		dTimeArray.add(this.getResources().getDrawable(R.drawable.time9));
		
		//AM 和PM的图标
		//dApmArray.add(this.getResources().getDrawable(R.drawable.time0));
		//dApmArray.add(this.getResources().getDrawable(R.drawable.time1));
		
		
		AfNumberClock view = new AfNumberClock(this, timeBg, timeColon, dTimeArray,dApmArray);
		LinearLayout contentLayout = (LinearLayout)this.findViewById(R.id.contentLayout);
		contentLayout.addView(view);
        
    }
    
    
    private void initTitleRightLayout(){
    	mAfTitleBar.clearRightView();
    }

	@Override
	protected void onResume() {
		super.onResume();
		initTitleRightLayout();
	}
	
	public void onPause() {
		super.onPause();
	}
   
}


