package com.lyyj.activity.demo.popup.uc;
 

import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Popupwindow弹窗
 * 
 * @author zihao
 * 
 */
public class PopupUCDemoActivity extends Activity {
	private PopMenu popMenu;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_popup_uc_activity_main);
		initView();
	}

	private void initView() {
		popMenu = new PopMenu(this, R.drawable.lyyj_popup_uc_tips_bg);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				popMenu.show(v, PopupUCDemoActivity.this);
			}
		});
	}

}