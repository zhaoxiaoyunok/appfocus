package com.lyyj.activity.demo.imageprocess.facecropper.sample;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.appfocusbase.R;
import com.koushikdutta.ion.Ion;
import com.lyyj.activity.demo.imageprocess.facecropper.FaceCropper;
 
 

public class FaceCropperActivity extends ActionBarActivity {

 
    private FaceCropper mFaceCropper;
    private ViewPager mViewPager;
    private Ion mIon ;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_imageprocess_facecropper_activity_main);

        mFaceCropper = new FaceCropper(1f);
        mFaceCropper.setFaceMinSize(0);
        mFaceCropper.setDebug(true);

        final ImageAdapter adapter = new ImageAdapter();
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                adapter.updateView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mFaceCropper.setEyeDistanceFactorMargin((float) i / 10);
                adapter.updateView(mViewPager.getCurrentItem());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        seekBar.setProgress(10);
    }

    class ImageAdapter extends PagerAdapter {

        private int[] urls = new int[] {
                R.drawable.lyyj_imageprocess_facecropper_lluis1,
                R.drawable.lyyj_imageprocess_facecropper_vueling,
                R.drawable.lyyj_imageprocess_facecropper_arol1,
                R.drawable.lyyj_imageprocess_facecropper_git1,
                R.drawable.lyyj_imageprocess_facecropper_git2
        };

        @Override
        public int getCount() {
            return (urls == null) ? 0 : urls.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = getLayoutInflater().inflate(R.layout.lyyj_imageprocess_facecropper_pager_item, null, false);

            setupView(v, position);

            v.setTag(position);
            container.addView(v);
            return v;
        }

        public void setupView(View v, int position) {
            if (v == null) return;
            ImageView image = (ImageView) v.findViewById(R.id.imageView);
            ImageView imageCropped = (ImageView) v.findViewById(R.id.imageViewCropped);

 //           Ion.with(image).placeholder(urls[position]).transform(mDebugCropTransformation).into(image);

//            mPicasso.load(urls[position])
//                    .config(Bitmap.Config.RGB_565)
//                    .transform(mCropTransformation)
//                    .into(imageCropped);
            image.setImageResource(urls[position]);
           
            imageCropped.setImageBitmap( mFaceCropper.getCroppedImage(FaceCropperActivity.this, urls[position]));
        }

        public void updateView(int position) {
            setupView(mViewPager.findViewWithTag(position), position);
        }
    }
}
