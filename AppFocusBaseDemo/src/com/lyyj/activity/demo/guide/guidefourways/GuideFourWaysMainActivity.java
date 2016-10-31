package com.lyyj.activity.demo.guide.guidefourways;
 
import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.guidefourways.activity.scrollView.ScollerViewActivity;
import com.lyyj.activity.demo.guide.guidefourways.activity.splash.SplashActivity;
import com.lyyj.activity.demo.guide.guidefourways.activity.viewFlipper.ViewFlipperActivity;
import com.lyyj.activity.demo.guide.guidefourways.activity.viewPage.ViewPagerActivity;
import com.lyyj.activity.demo.guide.guidefourways.utils.AnimationUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * APP 引导方式分类
 *com.zhangyx.MyLauncherGuide.MainActivity
 * @author Admin-zhangyx
 *
 * create at 2015-1-21 下午2:04:27
 */
public class GuideFourWaysMainActivity extends Activity implements View.OnClickListener{

	//@AbIocView(id = R.id.btnSplash,click="onClick")
	private Button btnSplash;
	//@AbIocView(id = R.id.btnViewPage,click="onClick")
	private Button btnViewPage;
	//@AbIocView(id = R.id.btnViewFlipper,click="onClick")
	private Button btnViewFlipper;
	//@AbIocView(id = R.id.btnScrollView,click="onClick")
	private Button btnScrollView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_guide_fourways_activity_main);
//  
        btnSplash = (Button) findViewById(R.id.btnSplash);   
        btnViewPage = (Button) findViewById(R.id.btnViewPage);   
        btnViewFlipper = (Button) findViewById(R.id.btnViewFlipper);   
        btnScrollView = (Button) findViewById(R.id.btnScrollView);   
        
        btnSplash.setOnClickListener(this);
       btnViewPage.setOnClickListener(this);
       btnViewFlipper.setOnClickListener(this);
        btnScrollView.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnSplash){
			startActivity(new Intent(this,SplashActivity.class));
		}else if(v==btnViewPage){
			startActivity(new Intent(this,ViewPagerActivity.class));
		}else if(v==btnViewFlipper){
			startActivity(new Intent(this,ViewFlipperActivity.class));
		}else if(v==btnScrollView){
			startActivity(new Intent(this,ScollerViewActivity.class));
		}
		AnimationUtil.activityZoomAnimation(this);
	}
    
}
