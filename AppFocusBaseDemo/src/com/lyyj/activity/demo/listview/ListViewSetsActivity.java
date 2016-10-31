package com.lyyj.activity.demo.listview;

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
import com.lyyj.activity.demo.expandlistview.gridview.ExpandListViewGridViewActivity;
import com.lyyj.activity.demo.listview.arclistview.ArcListViewMainActivity;
import com.lyyj.activity.demo.listview.fragmentgoodscategory.ListViewFragmentGoodsCategoryMainActivity;
import com.lyyj.activity.demo.listview.indexablelistview.IndexableListViewActivity;
import com.lyyj.activity.demo.listview.letter.LetterListViewMainActivity;
import com.lyyj.activity.demo.listview.multichoice.ListViewMultiChoiceMainActivity;
import com.lyyj.activity.demo.listview.multichoicewithiconandpreviewgrid.MultiChoiceWithIconAndPreviewGridMainActivity;
import com.lyyj.activity.demo.listview.parallax.ParallaxListViewActivity;
import com.lyyj.activity.demo.listview.pinnedsectionlistview.examples.PinnedSectionListActivity;
import com.lyyj.activity.demo.listview.stickylistheaders.sample.StickyListHeadersActivity;
import com.lyyj.activity.demo.listview.swipelistview.sample.activities.SwipeListViewExampleActivity;
import com.lyyj.activity.demo.listview.swipemenulistview.sample.SwipeMenuListViewMainActivity;
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

public class ListViewSetsActivity extends AfActivity {
	
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
    @AfIocView(id = R.id.lyyj_et_button10,click="btnClick")Button button10;
    @AfIocView(id = R.id.lyyj_et_button11,click="btnClick")Button button11;
    @AfIocView(id = R.id.lyyj_et_button12,click="btnClick")Button button12;    
    @AfIocView(id = R.id.lyyj_et_button13,click="btnClick")Button button13;
    @AfIocView(id = R.id.lyyj_et_button14,click="btnClick")Button button14;     
    private int[] btn_ids = new int[]{
    		R.id.lyyj_et_button1,
    		R.id.lyyj_et_button2,
    		R.id.lyyj_et_button3,
    		R.id.lyyj_et_button4,
    		R.id.lyyj_et_button5,
    		R.id.lyyj_et_button6,
    		R.id.lyyj_et_button7,    		
    		R.id.lyyj_et_button8,
    		R.id.lyyj_et_button9,
    		R.id.lyyj_et_button10,
    		R.id.lyyj_et_button11,
    		R.id.lyyj_et_button12,    
    		R.id.lyyj_et_button13,
    		R.id.lyyj_et_button14,     		
    };
 
	List<Class<?>> clss = new ArrayList<Class<?>>();

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.lyyj_lv_sets);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("listview full");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
        
    	clss.add(IndexableListViewActivity.class);
      	clss.add(PinnedSectionListActivity.class);
    	clss.add(StickyListHeadersActivity.class);
    	clss.add(SwipeListViewExampleActivity.class);
    	clss.add(ArcListViewMainActivity.class);
     	clss.add(ListViewFragmentGoodsCategoryMainActivity.class);
     	clss.add(LetterListViewMainActivity.class);
     	clss.add(ListViewMultiChoiceMainActivity.class);
     	clss.add(ParallaxListViewActivity.class);
     	clss.add(SwipeMenuListViewMainActivity.class);
     	clss.add(MultiChoiceWithIconAndPreviewGridMainActivity.class);
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
