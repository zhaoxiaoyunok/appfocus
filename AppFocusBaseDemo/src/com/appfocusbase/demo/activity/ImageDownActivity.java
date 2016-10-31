package com.appfocusbase.demo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.Constant;
import com.appfocusbase.global.MyApplication;
import com.koushikdutta.ion.Ion;

public class ImageDownActivity extends AfActivity {
	
	private MyApplication application;
	private AfTitleBar mAfTitleBar = null;
	
	private ImageView scaleView = null;
	private ImageView scaleView2 = null;
	private ImageView scaleView3 = null;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.image_down);
        application = (MyApplication)mAfApplication;
        
        mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.down_image_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
        initTitleRightLayout();
        
        scaleView = (ImageView)this.findViewById(R.id.scale_img);
        scaleView2 = (ImageView)this.findViewById(R.id.scale_img2);
        scaleView3 = (ImageView)this.findViewById(R.id.scale_img3);
        
        String imageUrl = Constant.BASEURL+"content/templates/amsoft/images/rand/0.jpg";
        String imageUrl2 = Constant.BASEURL+"content/templates/amsoft/images/rand/1.jpg";
        String imageUrl3 = Constant.BASEURL+"content/templates/amsoft/images/rand/2.jpg";
        Ion.with(scaleView).load(imageUrl);
        Ion.with(scaleView2).resize(150, 150).load(imageUrl2);
        Ion.with(scaleView3).resize(180, 180).load(imageUrl3);
//        //图片的下载
//        mAbImageLoader = AbImageLoader.getInstance(this);
//        
//        //原图片的下载
//        mAbImageLoader.display(scaleView,imageUrl);
//        
//        //缩放图片的下载（保持宽高比，计算缩放比例，使一个方向缩放后，另一方向不小与显示的大小的最合适比例）
//        mAbImageLoader.display(scaleView2,imageUrl2,150,150);
//        
//        //放大图片的下载
//        mAbImageLoader.display(scaleView3,imageUrl3,180,180);
        
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


