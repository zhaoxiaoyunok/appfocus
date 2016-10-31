package com.lyyj.activity.demo.guide;

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
import com.lyyj.activity.demo.guide.guidefourways.GuideFourWaysMainActivity;
import com.lyyj.activity.demo.guide.linkedviewpager.LinkedViewPagerActivity;
import com.lyyj.activity.demo.guide.viewpagerdooropen.ViewPagerDoorOpenAppstart;
import com.lyyj.activity.demo.guide.xhsreglogin.XhsWelcomeActivity;
import com.lyyj.activity.demo.menu.animation.MenuAnimationActivity;
import com.lyyj.activity.demo.menu.arc.MenuArcActivity;
import com.lyyj.activity.demo.menu.circle.MenuCircleLayoutActivity;
import com.lyyj.activity.demo.menu.menudrawer.SamplesActivity;
import com.lyyj.activity.demo.menu.radial.RadialMenuMainMenuActivity;
import com.lyyj.activity.demo.menu.satellitewheel.MenuSatelliteWheelActivity;
import com.lyyj.activity.demo.progressbar.progressbarwheel.ProgressBarWheelActivity;
import com.lyyj.activity.demo.progressbar.smoothprogressbar.sample.SmoothProgressBarMainActivity;
import com.lyyj.activity.demo.specialview.bubblelayout.BubbleLayoutMainActivity;
import com.lyyj.activity.demo.specialview.cardview.CardViewMainActivity;
import com.lyyj.activity.demo.specialview.circularprogressdrawable.CircularProgressDrawableMainActivity;
import com.lyyj.activity.demo.specialview.fivehundredpxblurview.BlurView500pxMainActivity;
import com.lyyj.activity.demo.specialview.seismicwaveview.SeismicWaveMainActivity;
import com.lyyj.activity.demo.specialview.shapeloading.ShapeLoadingMainActivity;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class GuideSetsActivity extends AfActivity {
	
	private MyApplication application;
	
	@AfIocView(id = R.id.lyyj_et_button1,click="btnClick")Button button1;
    @AfIocView(id = R.id.lyyj_et_button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.lyyj_et_button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.lyyj_et_button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.lyyj_et_button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.lyyj_et_button6,click="btnClick")Button button6;
 
    
    private int[] btn_ids = new int[]{
    		R.id.lyyj_et_button1,
    		R.id.lyyj_et_button2,
    		R.id.lyyj_et_button3,
    		R.id.lyyj_et_button4,
    		R.id.lyyj_et_button5,
    		R.id.lyyj_et_button6	
    };
 
	List<Class<?>> clss = new ArrayList<Class<?>>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_guide_sets);
        
        AfTitleBar mAbTitleBar = this.getTitleBar();
        mAbTitleBar.setTitleText("special views full");
        mAbTitleBar.setLogo(R.drawable.button_selector_back);
        mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAbTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(XhsWelcomeActivity.class);
      	clss.add(LinkedViewPagerActivity.class);
    	clss.add(GuideFourWaysMainActivity.class);
    	clss.add(ViewPagerDoorOpenAppstart.class);
    // 	clss.add(CardViewMainActivity.class);
    //	clss.add(CircularProgressDrawableMainActivity.class);
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
