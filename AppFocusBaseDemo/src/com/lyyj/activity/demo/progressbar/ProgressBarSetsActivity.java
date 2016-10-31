package com.lyyj.activity.demo.progressbar;

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
import com.lyyj.activity.demo.button.segmentradio.SegmentedRadioActivity;
import com.lyyj.activity.demo.edittext.validator.sample.EditTextFormExampleActivity;
import com.lyyj.activity.demo.menu.animation.MenuAnimationActivity;
import com.lyyj.activity.demo.menu.arc.MenuArcActivity;
import com.lyyj.activity.demo.menu.circle.MenuCircleLayoutActivity;
import com.lyyj.activity.demo.menu.menudrawer.SamplesActivity;
import com.lyyj.activity.demo.menu.radial.RadialMenuMainMenuActivity;
import com.lyyj.activity.demo.menu.satellitewheel.MenuSatelliteWheelActivity;
import com.lyyj.activity.demo.progressbar.circlepedometer.ProgressBarCirclePedometerMainActivity;
import com.lyyj.activity.demo.progressbar.circlerainbow.RainbowProgressBarMainActivity;
import com.lyyj.activity.demo.progressbar.circleround.ProgressBarCircleRoundMainActivity;
import com.lyyj.activity.demo.progressbar.customloading.ProgressBarCustomLoadingMainActivity;
import com.lyyj.activity.demo.progressbar.holocircular.ProgressBarHoloCircularProgressMainActivity;
import com.lyyj.activity.demo.progressbar.number.ProgressBarNumber;
import com.lyyj.activity.demo.progressbar.progressbarwheel.ProgressBarWheelActivity;
import com.lyyj.activity.demo.progressbar.roundcorner.ProgressBarRoundCornerMainActivity;
import com.lyyj.activity.demo.progressbar.smoothprogressbar.sample.SmoothProgressBarMainActivity;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class ProgressBarSetsActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.lyyj_et_button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.lyyj_et_button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.lyyj_et_button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.lyyj_et_button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.lyyj_et_button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.lyyj_et_button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.lyyj_et_button7,click="btnClick")Button button7;
    @AfIocView(id = R.id.lyyj_et_button8,click="btnClick")Button button8;
    @AfIocView(id = R.id.lyyj_et_button9,click="btnClick")Button button9;  
    private int[] btn_ids = new int[]{
    		R.id.lyyj_et_button1,
    		R.id.lyyj_et_button2,
    		R.id.lyyj_et_button3,
    		R.id.lyyj_et_button4,
    		R.id.lyyj_et_button5,
    		R.id.lyyj_et_button6,	
    		R.id.lyyj_et_button7,
    		R.id.lyyj_et_button8,
    		R.id.lyyj_et_button9
    };
 
	List<Class<?>> clss = new ArrayList<Class<?>>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_pb_sets);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("progressbar full");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(SmoothProgressBarMainActivity.class);
      	clss.add(ProgressBarWheelActivity.class);
     	clss.add(ProgressBarCustomLoadingMainActivity.class);
     	clss.add(ProgressBarHoloCircularProgressMainActivity.class);
     	clss.add(ProgressBarNumber.class);
     	clss.add(ProgressBarRoundCornerMainActivity.class);
     	clss.add(ProgressBarCircleRoundMainActivity.class);
     	clss.add(ProgressBarCirclePedometerMainActivity.class);
     	clss.add(RainbowProgressBarMainActivity.class);
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
