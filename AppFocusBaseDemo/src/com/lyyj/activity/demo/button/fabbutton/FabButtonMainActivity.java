package com.lyyj.activity.demo.button.fabbutton;
 

import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class FabButtonMainActivity extends  Activity {
    String TAG = FabButtonMainActivity.class.getSimpleName();
    FabButton determinate,indeterminate;
    int currentProgress = 0;
    Handler handle=new Handler();

    final Runnable updater = new Runnable() {
        @Override
        public void run() {
            currentProgress += 1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG,"["+currentProgress+"]");
                    determinate.setProgress(currentProgress);
                    if(currentProgress <= 100){
                        handle.postDelayed(updater,50);
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_btn_activity_main);
        determinate = (FabButton) findViewById(R.id.determinate);
        indeterminate = (FabButton) findViewById(R.id.indeterminate);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDeterminateProgress();
                startIndeterminateProgress();
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               determinate.resetIcon();
            }
        });



//        determinate.setEnabled(false);
        determinate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FabButtonMainActivity.this,"determinate",Toast.LENGTH_SHORT).show();
                startDeterminateProgress();
            }
        });


        indeterminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FabButtonMainActivity.this,"indeterminate",Toast.LENGTH_SHORT).show();
                startIndeterminateProgress();
            }
        });


    }

    private void startIndeterminateProgress() {
        indeterminate.showProgress(true);
    }


    private void startDeterminateProgress() {
        determinate.resetIcon();
        currentProgress = 0;
        determinate.showProgress(true);
        determinate.setProgress(currentProgress);
        updater.run();
    }

}
