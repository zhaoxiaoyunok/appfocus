package com.lyyj.activity.demo.specialview.seismicwaveview;
 

import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SeismicWaveMainActivity extends Activity {

	private Button btn;
	private SeismicWaveView seismicWaveView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_specialview_seismicwave_activity_main);
		btn = (Button) findViewById(R.id.button);
		btn.setText("开始");
		seismicWaveView = (SeismicWaveView) findViewById(R.id.seismicwaveview);
		//控制地震波的按钮
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (seismicWaveView.isStarting()) {
					btn.setText("继续");
					seismicWaveView.stop();
				} else {
					btn.setText("停止");
					seismicWaveView.start();
				}
			}
		});
	}

}
