/*
 * Copyright (C) 2015 500px Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyyj.activity.demo.specialview.fivehundredpxblurview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
 


import java.util.Random;

import com.appfocusbase.R;

/**
 * Demonstrates the use of the blurring view.
 */
public class BlurView500pxMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_specialview_blurview500px_activity_main);
        mBlurringView = (BlurringView) findViewById(R.id.blurring_view);
        View blurredView = findViewById(R.id.blurred_view);

        // Give the blurring view a reference to the blurred view.
        mBlurringView.setBlurredView(blurredView);

        mImageViews[0] = (ImageView) findViewById(R.id.image0);
        mImageViews[1] = (ImageView) findViewById(R.id.image1);
        mImageViews[2] = (ImageView) findViewById(R.id.image2);
        mImageViews[3] = (ImageView) findViewById(R.id.image3);
        mImageViews[4] = (ImageView) findViewById(R.id.image4);
        mImageViews[5] = (ImageView) findViewById(R.id.image5);
        mImageViews[6] = (ImageView) findViewById(R.id.image6);
        mImageViews[7] = (ImageView) findViewById(R.id.image7);
        mImageViews[8] = (ImageView) findViewById(R.id.image8);
    }

    public void shuffle(View view) {

        // Randomly pick a different start in the array of available images.
        int newStartIndex;
        do {
            newStartIndex = mImageIds[mRandom.nextInt(mImageIds.length)];
        } while (newStartIndex == mStartIndex);
        mStartIndex = newStartIndex;

        // Update the images for the image views contained in the blurred view.
        for (int i = 0; i < mImageViews.length; i++) {
            int drawableId = mImageIds[(mStartIndex + i) % mImageIds.length];
            mImageViews[i].setImageDrawable(getResources().getDrawable(drawableId));
        }

        // Invalidates the blurring view when the content of the blurred view changes.
        mBlurringView.invalidate();
    }

    private ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mBlurringView.invalidate();
        }
    };

    @SuppressLint("NewApi")
	public void shift(View view) {
        if (!mShifted) {
            for (ImageView imageView : mImageViews) {
                ObjectAnimator tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, (mRandom.nextFloat() - 0.5f) * 500);
                tx.addUpdateListener(listener);
                ObjectAnimator ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, (mRandom.nextFloat() - 0.5f) * 500);
                ty.addUpdateListener(listener);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(tx, ty);
                set.setDuration(3000);
                set.setInterpolator(new OvershootInterpolator());
                set.addListener(new AnimationEndListener(imageView));
                set.start();
            }
            mShifted = true;
        } else {
            for (ImageView imageView : mImageViews) {
                ObjectAnimator tx = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, 0);
                tx.addUpdateListener(listener);
                ObjectAnimator ty = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 0);
                ty.addUpdateListener(listener);
                AnimatorSet set = new AnimatorSet();
                set.playTogether(tx, ty);
                set.setDuration(3000);
                set.setInterpolator(new OvershootInterpolator());
                set.addListener(new AnimationEndListener(imageView));
                set.start();
            }
            mShifted = false;
        }
    }

    private BlurringView mBlurringView;

    private int[] mImageIds = {
            R.drawable.lyyj_specialview_blurview500px_p0, R.drawable.lyyj_specialview_blurview500px_p1, R.drawable.lyyj_specialview_blurview500px_p2, R.drawable.lyyj_specialview_blurview500px_p3, R.drawable.lyyj_specialview_blurview500px_p4,
            R.drawable.lyyj_specialview_blurview500px_p5, R.drawable.lyyj_specialview_blurview500px_p6, R.drawable.lyyj_specialview_blurview500px_p7, R.drawable.lyyj_specialview_blurview500px_p8, R.drawable.lyyj_specialview_blurview500px_p9
    };

    private ImageView[] mImageViews = new ImageView[9];
    private int mStartIndex;

    private Random mRandom = new Random();

    private boolean mShifted;

    @SuppressLint("NewApi")
	private static class AnimationEndListener implements Animator.AnimatorListener {

        View mView;

        public AnimationEndListener(View v) {
            mView = v;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            mView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
