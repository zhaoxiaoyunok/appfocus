package com.lyyj.activity.demo.textview;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
import com.lyyj.activity.demo.menu.animation.MenuAnimationActivity;
import com.lyyj.activity.demo.menu.arc.MenuArcActivity;
import com.lyyj.activity.demo.menu.circle.MenuCircleLayoutActivity;
import com.lyyj.activity.demo.menu.menudrawer.SamplesActivity;
import com.lyyj.activity.demo.menu.radial.RadialMenuMainMenuActivity;
import com.lyyj.activity.demo.menu.satellitewheel.MenuSatelliteWheelActivity;
import com.lyyj.activity.demo.textview.babushkatext.BabushkaTextActivity;
import com.lyyj.activity.demo.textview.demo.TextViewJumpingBeansActivity;
import com.lyyj.activity.demo.textview.demo.TextViewMatchViewActivity;
import com.lyyj.activity.demo.textview.expandabletextview.sample.ExpandableTextViewDemoActivity;
import com.lyyj.activity.demo.textview.flowtextview.sample.FlowTextViewActivity;
import com.lyyj.activity.demo.textview.fontcustom.FontCustomActivity;
import com.lyyj.activity.demo.textview.multipletextview.MultipleTextViewActivity;
import com.lyyj.activity.demo.textview.myannouncementview.AnnouncementViewMainActivity;
import com.lyyj.activity.demo.textview.shimmer.ShimmerMainActivity;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class TextViewSetsActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.button7,click="btnClick")Button button7;
    @AfIocView(id = R.id.button8,click="btnClick")Button button8;
    @AfIocView(id = R.id.button9,click="btnClick")Button button9;   
    private int[] btn_ids = new int[]{
    		R.id.button1,
    		R.id.button2,
    		R.id.button3,
    		R.id.button4,
    		R.id.button5,
    		R.id.button6,	
    		R.id.button7,
    		R.id.button8,   
    		R.id.button9     		
    };
 
	List<Class<?>> clss = new ArrayList<Class<?>>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_tv_sets);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("textview full");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(BabushkaTextActivity.class);
    	clss.add(ExpandableTextViewDemoActivity.class);
    	 clss.add(FlowTextViewActivity.class);
    	clss.add(FontCustomActivity.class);
    	clss.add(TextViewJumpingBeansActivity.class);
    	 clss.add(TextViewMatchViewActivity.class);
    	 clss.add(MultipleTextViewActivity.class);
    	 clss.add(AnnouncementViewMainActivity.class);
    	 clss.add(ShimmerMainActivity.class);
    } 
	
	public void btnClick(View v){

		Intent intent = null;
		int btn_index = -1;
		for(int i = 0 ; i <btn_ids.length;i++){
			if(v.getId() == btn_ids[i]){
				btn_index = i;
				break;
			}
		}
		if(btn_index > -1){
			intent = new Intent(this, clss.get(btn_index));
			startActivity(intent);
		}
	}
    
}
