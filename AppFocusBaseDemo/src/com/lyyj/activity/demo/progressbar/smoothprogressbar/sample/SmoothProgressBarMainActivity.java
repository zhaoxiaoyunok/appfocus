package com.lyyj.activity.demo.progressbar.smoothprogressbar.sample;

import com.appfocusbase.R;
import com.lyyj.activity.demo.progressbar.smoothprogressbar.SmoothProgressBar;
import com.lyyj.activity.demo.progressbar.smoothprogressbar.SmoothProgressBarUtils;
import com.lyyj.activity.demo.progressbar.smoothprogressbar.SmoothProgressDrawable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;


public class SmoothProgressBarMainActivity extends Activity {

  private ProgressBar       mProgressBar1;
  private SmoothProgressBar mGoogleNow;
  private SmoothProgressBar mPocketBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.lyyj_pb_spb_activity_main);

    mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar2);
    mPocketBar = (SmoothProgressBar) findViewById(R.id.pocket);

    mProgressBar1.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(this).interpolator(new AccelerateInterpolator()).build());

    mGoogleNow = (SmoothProgressBar) findViewById(R.id.google_now);
    mPocketBar.setSmoothProgressDrawableBackgroundDrawable(
        SmoothProgressBarUtils.generateDrawableWithColors(
            getResources().getIntArray(R.array.pocket_background_colors),
            ((SmoothProgressDrawable) mPocketBar.getIndeterminateDrawable()).getStrokeWidth()));

    findViewById(R.id.button_make).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(SmoothProgressBarMainActivity.this, MakeCustomActivity.class);
        startActivity(intent);
      }
    });

    findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPocketBar.progressiveStart();
      }
    });

    findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPocketBar.progressiveStop();
      }
    });
  }
}
