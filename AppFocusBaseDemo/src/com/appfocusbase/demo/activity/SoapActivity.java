package com.appfocusbase.demo.activity;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.af.activity.AfActivity;
import com.af.fragment.AfAlertDialogFragment.AfDialogOnClickListener;
import com.af.soap.AfSoapListener;
import com.af.soap.AfSoapParams;
import com.af.soap.AfSoapUtil;
import com.af.util.AfDialogUtil;
import com.af.util.AfToastUtil;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * 名称：SoapActivity
 * 描述：soap框架
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class SoapActivity extends AfActivity {
	
	private MyApplication application;
	
	private AfSoapUtil mAbSoapUtil = null;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.soap_main);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.soap_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        Button callBtn  = (Button)this.findViewById(R.id.callBtn);
        
        //获取Http工具类
        mAbSoapUtil = AfSoapUtil.getInstance(this);
        mAbSoapUtil.setTimeout(10000);
        //get请求
        callBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// 一个url地址
				String urlString = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
                String nameSpace = "http://WebXml.com.cn/";
                String methodName = "getMobileCodeInfo";
                 AfSoapParams params = new AfSoapParams();
                params.put("mobileCode", "13480665739");
                params.put("userID", "");
				
				mAbSoapUtil.call(urlString,nameSpace,methodName,params, new AfSoapListener() {
					
					//获取数据成功会调用这里
		        	@Override
					public void onSuccess(int statusCode, SoapObject object) {
		        		
		        		AfDialogUtil.showAlertDialog(SoapActivity.this,"返回结果",object.toString(),new AfDialogOnClickListener(){

							@Override
							public void onNegativeClick() {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onPositiveClick() {
								// TODO Auto-generated method stub
								
							}
							
		            		
		            	});
		        	}
		        	
		        	// 失败，调用
		            @Override
					public void onFailure(int statusCode, String content,
							Throwable error) {
		            	
		            	AfToastUtil.showToast(SoapActivity.this,error.getMessage());
					}
		            
		            // 失败，调用
		            @Override
					public void onFailure(int statusCode, SoapFault fault) {
		            	AfToastUtil.showToast(SoapActivity.this,fault.faultstring);
					}

					// 开始执行前
		            @Override
					public void onStart() {
		            	//显示进度框
		            	AfDialogUtil.showProgressDialog(SoapActivity.this,0,"正在查询...");
					}


					// 完成后调用，失败，成功
		            @Override
		            public void onFinish() { 
		            	//移除进度框
		            	AfDialogUtil.removeDialog(SoapActivity.this);
		            };
		            
		        });
				
			}
		});
    }
    
}
