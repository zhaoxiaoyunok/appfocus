package com.appfocusbase.demo.activity;


import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

 



import com.af.activity.AfActivity;
import com.af.view.app.AfPopoverView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;

public class PopoverActivity extends AfActivity implements OnClickListener{
   
	RelativeLayout rootView = null;
	AfPopoverView popoverView = null;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.popover);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.popview_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
        rootView = (RelativeLayout)findViewById(R.id.rootLayout);
        popoverView = new AfPopoverView(this);
        popoverView.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.popover_bg));
        popoverView.setArrowLeftDrawable(this.getResources().getDrawable(R.drawable.popover_arrow_left));
        popoverView.setArrowRightDrawable(this.getResources().getDrawable(R.drawable.popover_arrow_right));
        popoverView.setArrowDownDrawable(this.getResources().getDrawable(R.drawable.popover_arrow_down));
        popoverView.setArrowUpDrawable(this.getResources().getDrawable(R.drawable.popover_arrow_up));
        
        popoverView.setContentSizeForViewInPopover(new Point(300, 300));
        popoverView.setPopoverViewListener(new AfPopoverView.PopoverViewListener() {
			
			@Override
			public void popoverViewWillShow(AfPopoverView view) {
			}
			
			@Override
			public void popoverViewWillDismiss(AfPopoverView view) {
			}
			
			@Override
			public void popoverViewDidShow(AfPopoverView view) {
			}
			
			@Override
			public void popoverViewDidDismiss(AfPopoverView view) {
			}
		});
        
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		popoverView.setPopoverContentView(mInflater.inflate(R.layout.popover_showed_view, null));
		popoverView.showPopoverFromRectInViewGroup(rootView, AfPopoverView.getFrameForView(v), AfPopoverView.PopoverArrowDirectionAny, true);
	}

	
}