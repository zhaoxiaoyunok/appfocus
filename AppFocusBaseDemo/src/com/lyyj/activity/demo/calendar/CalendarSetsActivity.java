package com.lyyj.activity.demo.calendar;

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
import com.lyyj.activity.demo.anim.listview3deffect.ListViewTransition3dActivity;
import com.lyyj.activity.demo.anim.radiationview.AnimRadiationViewActivity;
import com.lyyj.activity.demo.calendar.dateslider.DateSliderDemo;
import com.lyyj.activity.demo.calendar.dateslider.DateSliderMinimalDemo;
import com.lyyj.activity.demo.calendar.datetimepicker.sample.DateTimePickerMainActivity;
import com.lyyj.activity.demo.calendar.iosweekview.IOSWeekViewActivity;
import com.lyyj.activity.demo.calendar.lunar.LunarActivity;
import com.lyyj.activity.demo.calendar.scrollercalendar.demo.iosScrollerCalendarMainActivity;
import com.lyyj.activity.demo.calendar.sdcalendar.sample.SDCalendarActivity;
import com.lyyj.activity.demo.calendar.timessquare.sample.SampleTimesSquareActivity;
import com.lyyj.activity.demo.edittext.validator.sample.EditTextFormExampleActivity;
import com.lyyj.activity.demo.menu.animation.MenuAnimationActivity;
import com.lyyj.activity.demo.menu.arc.MenuArcActivity;
import com.lyyj.activity.demo.menu.circle.MenuCircleLayoutActivity;
import com.lyyj.activity.demo.menu.menudrawer.SamplesActivity;
import com.lyyj.activity.demo.menu.radial.RadialMenuMainMenuActivity;
import com.lyyj.activity.demo.menu.satellitewheel.MenuSatelliteWheelActivity;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class CalendarSetsActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.lyyj_et_button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.lyyj_et_button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.lyyj_et_button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.lyyj_et_button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.lyyj_et_button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.lyyj_et_button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.lyyj_et_button7,click="btnClick")Button button7;
    @AfIocView(id = R.id.lyyj_et_button8,click="btnClick")Button button8; 
    
    private int[] btn_ids = new int[]{
    		R.id.lyyj_et_button1,
    		R.id.lyyj_et_button2,
    		R.id.lyyj_et_button3,
    		R.id.lyyj_et_button4,
    		R.id.lyyj_et_button5,
    		R.id.lyyj_et_button6,
    		R.id.lyyj_et_button7,
    		R.id.lyyj_et_button8,
    };
 
	List<Class<?>> clss = new ArrayList<Class<?>>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_calendar_sets);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("calendar full");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(IOSWeekViewActivity.class);
     	clss.add(SampleTimesSquareActivity.class);
     	clss.add(LunarActivity.class);
    	clss.add(DateSliderDemo.class);
     	clss.add(DateSliderMinimalDemo.class);
    	clss.add(SDCalendarActivity.class);
     	clss.add(iosScrollerCalendarMainActivity.class);
     	clss.add(DateTimePickerMainActivity.class);
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
