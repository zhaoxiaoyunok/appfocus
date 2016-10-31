package com.lyyj.activity.demo.anim.curtaineffect;
 

import com.appfocusbase.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class CurtainEffectActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_anim_curtain_main);
		initView();
	}

	private void initView() {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}

}
