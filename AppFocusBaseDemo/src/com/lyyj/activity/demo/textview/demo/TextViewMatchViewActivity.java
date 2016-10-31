package com.lyyj.activity.demo.textview.demo;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import com.appfocusbase.R;
import com.lyyj.activity.demo.textview.library.MatchTextView;
 


public class TextViewMatchViewActivity extends  Activity {

    private SeekBar mSeekBar;
    private MatchTextView mMatchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_tv_matchview_activity_main);

        mMatchTextView = (MatchTextView) findViewById(R.id.mMatchTextView);
        mSeekBar = (SeekBar) findViewById(R.id.mSeekBar);
        mSeekBar.setProgress(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mMatchTextView.setProgress(progress * 1f / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.mButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchDialog matchDialog = new MatchDialog();
                getFragmentManager().beginTransaction().add(matchDialog, "matchDialog").commit();
            }
        });
    }

}
