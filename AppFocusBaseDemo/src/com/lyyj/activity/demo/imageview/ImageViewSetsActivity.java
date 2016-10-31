package com.lyyj.activity.demo.imageview;

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
import com.lyyj.activity.demo.imageview.customshape.meg7.samples.ImageViewCustomShapeSamplesActivity;
import com.lyyj.activity.demo.imageview.flipimageview.FlipImageViewSampleActivity;
import com.lyyj.activity.demo.imageview.loadingcircle.LoadingCircleMainActivity;
import com.lyyj.activity.demo.imageview.roundrunning.RoundRunningMainActivity;
import com.lyyj.activity.demo.imageview.autoresize.MasaccioAutoResizeDemoActivity;
/**
 * © 2015 lyyj.com
 * 名称：UIElementActivity.java 
 * 描述：常用控件汇总.
 *
 * @author kaka
 * @version v1.0
 * 
 */

public class ImageViewSetsActivity extends AfActivity {
	
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
        
        setAfContentView(R.layout.lyyj_iv_sets);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("imageprocess_sets");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(ImageViewCustomShapeSamplesActivity.class);
   	clss.add(FlipImageViewSampleActivity.class);
    	clss.add(MasaccioAutoResizeDemoActivity.class);
    	clss.add(RoundRunningMainActivity.class);
    	clss.add(LoadingCircleMainActivity.class);
//    	clss.add(MenuSatelliteWheelActivity.class);
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
