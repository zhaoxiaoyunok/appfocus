package com.appfocusbase.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.af.activity.AfActivity;
import com.af.model.AfResult;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskObjectListener;
import com.af.task.thread.AfTaskPool;
import com.af.util.AfDialogUtil;
import com.af.util.AfStrUtil;
import com.af.util.AfToastUtil;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class FindPwdActivity extends AfActivity {
	
	private MyApplication application;
	
	private EditText userName = null;
	private EditText email = null;
	private ImageButton mClear1;
	private ImageButton mClear2;
	private String mStr_name = null;
	private String mStr_email = null;
	private AfTaskPool mAfTaskPool = null;
	private AfTitleBar mAfTitleBar = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.find_pwd);
		application = (MyApplication) mAfApplication;
		mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.register_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);
		//设置AbTitleBar在最上
		this.setTitleBarOverlay(true);
		mAfTaskPool = AfTaskPool.getInstance();
		userName = (EditText) this.findViewById(R.id.userName);
		email = (EditText) this.findViewById(R.id.email);
		
		mClear1 = (ImageButton) findViewById(R.id.clearName);
		mClear2 = (ImageButton) findViewById(R.id.clearEmail);
		
		userName.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String str = userName.getText().toString().trim();
				int length = str.length();
				if (length > 0) {
					mClear1.setVisibility(View.VISIBLE);
					if (!AfStrUtil.isNumberLetter(str)) {
						str = str.substring(0, length - 1);
						userName.setText(str);
						String str1 = userName.getText().toString().trim();
						userName.setSelection(str1.length());
						AfToastUtil.showToast(FindPwdActivity.this,R.string.error_name_expr);
					}
					
					mClear1.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							mClear1.setVisibility(View.INVISIBLE);
						}
						
					}, 5000);
					
				} else {
					mClear1.setVisibility(View.INVISIBLE);
				}
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		email.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = email.getText().toString().trim();
				int length = str.length();
				if (length > 0) {
					mClear2.setVisibility(View.VISIBLE);
					if(AfStrUtil.isContainChinese(str)){
						str = str.substring(0, length-1);
						email.setText(str);
						String str1 = email.getText().toString().trim();
						email.setSelection(str1.length());
						AfToastUtil.showToast(FindPwdActivity.this,R.string.error_email_expr2);
					}
					mClear2.postDelayed(new Runnable(){

						@Override
						public void run() {
							mClear2.setVisibility(View.INVISIBLE);
						}
						
					}, 5000);
				} else {
					mClear2.setVisibility(View.INVISIBLE);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			public void afterTextChanged(Editable s) {

			}
		});
		
		mClear1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				userName.setText("");
			}
		});
		
		mClear2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				email.setText("");
			}
		});
		
		Button findPwdBtn = (Button) findViewById(R.id.findPwdBtn);
		findPwdBtn.setOnClickListener(new FindPwdOnClickListener());
		
	}
	
	public class FindPwdOnClickListener implements View.OnClickListener {
		
		@Override
		public void onClick(View v) {
			mStr_name = userName.getText().toString().trim();
			mStr_email = email.getText().toString().trim();
			if (TextUtils.isEmpty(mStr_name)) {
				AfToastUtil.showToast(FindPwdActivity.this,R.string.error_name);
				userName.setFocusable(true);
				userName.requestFocus();
				return;
			}
			
			if (!AfStrUtil.isNumberLetter(mStr_name)) {
				AfToastUtil.showToast(FindPwdActivity.this,R.string.error_name_expr);
				userName.setFocusable(true);
				userName.requestFocus();
				return;
			}
			
			if (AfStrUtil.strLength(mStr_name) < 3) {
				AfToastUtil.showToast(FindPwdActivity.this,R.string.error_name_length1);
				userName.setFocusable(true);
				userName.requestFocus();
				return;
			}
			
			if (AfStrUtil.strLength(mStr_name) > 20) {
				AfToastUtil.showToast(FindPwdActivity.this,R.string.error_name_length2);
				userName.setFocusable(true);
				userName.requestFocus();
				return;
			}
			
			
			if (!TextUtils.isEmpty(mStr_email)) {
				if (!AfStrUtil.isEmail(mStr_email)) {
					AfToastUtil.showToast(FindPwdActivity.this,R.string.error_email_expr);
					email.setFocusable(true);
					email.requestFocus();
					return;
				}
			}else{
				AfToastUtil.showToast(FindPwdActivity.this,R.string.error_email);
				email.setFocusable(true);
				email.requestFocus();
			}
			
			
			AfDialogUtil.showProgressDialog(FindPwdActivity.this,R.drawable.progress_circular,"正在找回...");
			final AfTaskItem item = new AfTaskItem();
			item.setListener(new AfTaskObjectListener() {

				@Override
				public void update(Object obj) {
					AfDialogUtil.removeDialog(FindPwdActivity.this);
					AfResult mAbResult = (AfResult)obj;
					if(mAbResult != null){
						AfToastUtil.showToast(FindPwdActivity.this,mAbResult.getResultMessage());
						if(mAbResult.getResultCode()==AfResult.RESULT_OK){
							finish();
			        	}
			        }
				}

				@Override
				public Object getObject() {
				    AfResult mAbResult = null;
		   		    try {
		   		    	mAbResult = new AfResult();
		   		    	mAbResult.setResultMessage("ok");
		   		    	
		   		    } catch (Exception e){
		   		    	AfToastUtil.showToastInThread(FindPwdActivity.this,e.getMessage());
		   		    }
		   		    return mAbResult;
			  };
			});
			mAfTaskPool.execute(item);
		}
	}
	
}
