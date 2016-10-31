package com.appfocusbase.demo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

 




import com.af.activity.AfActivity;
import com.af.view.app.AfAnalogClock;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class AnalogClockActivity extends AfActivity {
	
	private MyApplication application;
	private AfTitleBar mAbTitleBar = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.analog_clock);
        application = (MyApplication)mAfApplication;
        
        mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText(R.string.analog_clock_name);
        mAbTitleBar.setLogo(R.drawable.button_selector_back);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.drawable.line);
        
        initTitleRightLayout();
        
		Drawable dial = this.getResources().getDrawable(R.drawable.clock_dial);
		Drawable hourHand = this.getResources().getDrawable(R.drawable.clock_hour);
		Drawable minuteHand = this.getResources().getDrawable(R.drawable.clock_minute);
		Drawable secondHand = this.getResources().getDrawable(R.drawable.clock_second);
		AfAnalogClock view = new AfAnalogClock(this, dial, hourHand, minuteHand,secondHand);
        
		LinearLayout contentLayout = (LinearLayout)this.findViewById(R.id.contentLayout);
		contentLayout.addView(view);
    }
    
    
    private void initTitleRightLayout(){
    	mAbTitleBar.clearRightView();
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


