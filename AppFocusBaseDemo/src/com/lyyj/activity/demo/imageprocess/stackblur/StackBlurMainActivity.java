package com.lyyj.activity.demo.imageprocess.stackblur;

import java.io.IOException;
import java.io.InputStream;

import com.af.activity.AfActivity;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.ToggleButton;
 
 

public class StackBlurMainActivity extends AfActivity {

	@AfIocView(id = R.id.imageView)    ImageView    _imageView;
	@AfIocView(id = R.id.seekBar)      SeekBar      _seekBar  ;
	@AfIocView(id = R.id.toggleButton) ToggleButton _toggleButton;
	
	private StackBlurManager _stackBlurManager;
	
	private String IMAGE_TO_ANALYZE = "lyyj_imageprocess_stackblur_android_platform_256.png";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.lyyj_imageprocess_stackblur_activity_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("imageprocess_sets");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        
		_stackBlurManager = new StackBlurManager(getBitmapFromAsset(this, IMAGE_TO_ANALYZE));
		
		_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				_stackBlurManager.process(progress*5);
				_imageView.setImageBitmap(_stackBlurManager.returnBlurredImage() );
			}
		});
		
		_toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        if (isChecked) {
		        	IMAGE_TO_ANALYZE = "lyyj_imageprocess_stackblur_image_transparency.png";
		        	_stackBlurManager = new StackBlurManager(getBitmapFromAsset(getApplicationContext(), IMAGE_TO_ANALYZE));
		        	_imageView.setImageDrawable(getResources().getDrawable(R.drawable.lyyj_imageprocess_stackblur_image_transparency));
		        } else {
		        	IMAGE_TO_ANALYZE = "lyyj_imageprocess_stackblur_android_platform_256.png";
		        	_stackBlurManager = new StackBlurManager(getBitmapFromAsset(getApplicationContext(), IMAGE_TO_ANALYZE));
		        	_imageView.setImageDrawable(getResources().getDrawable(R.drawable.lyyj_imageprocess_stackblur_android_platform_256));
		        }
		    }
		});
	}

    private Bitmap getBitmapFromAsset(Context context, String strName) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(strName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            return null;
        }
        return bitmap;
    }

}
