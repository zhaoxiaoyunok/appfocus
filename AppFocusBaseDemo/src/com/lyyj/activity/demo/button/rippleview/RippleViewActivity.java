package com.lyyj.activity.demo.button.rippleview;

import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;



public class RippleViewActivity extends Activity {

	RippleView mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_btn_rippleview_activity_ripple_view);
        mButton = (RippleView) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Ripples Yo! :D", Toast.LENGTH_LONG).show();
			}
		});
    }

}
