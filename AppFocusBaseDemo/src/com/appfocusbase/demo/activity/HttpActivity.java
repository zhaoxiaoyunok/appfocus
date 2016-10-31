package com.appfocusbase.demo.activity;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.http.protocol.HTTP;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.af.activity.AfActivity;
import com.af.fragment.AfAlertDialogFragment.AfDialogOnClickListener;
import com.af.util.AfDialogUtil;
import com.af.util.AfFileUtil;
import com.af.util.AfImageUtil;
import com.af.util.AfToastUtil;
import com.af.view.ioc.AfIocView;
import com.af.view.progress.AfHorizontalProgressBar;
import com.appfocusbase.R;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.lyyj.activity.demo.progressbar.circlerainbow.CircularProgress;


/**
 * 名称：HttpActivity
 * 描述：Http框架
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class HttpActivity extends AfActivity {
	
	/** The Constant TAG. */
    private static final String TAG = "HttpActivity";
	

	
	// ProgressBar进度控制
	private AfHorizontalProgressBar mAfProgressBar;
	// 最大100
	private int max = 100;	
	private int progress = 0;
    Future<File> downloading;	
    Future<File> uploading;		
	
	private TextView numberText, maxText;
	private DialogFragment  mAlertDialog  = null;
    private MyHandler  mHandler;
	@AfIocView(id = R.id.getBtn)Button getBtn;
	@AfIocView(id = R.id.postBtn)Button postBtn;
	@AfIocView(id = R.id.byteBtn)Button byteBtn;
	@AfIocView(id = R.id.fileBtn)Button fileDownBtn;
	@AfIocView(id = R.id.fileUploadBtn)Button fileUploadBtn;
	@AfIocView(id = R.id.http_progress)CircularProgress  progressBar;	
	@AfIocView(id = R.id.iv_http_byte)ImageView ivByte;	
	
	
	static final int MSG_SHOW_PROGRESS = 1;
	static final int MSG_HIDE_PROGRESS = 2;	
    static final int MSG_BYTE_DATA = 3 ;	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.http_main);
        
        mHandler = new MyHandler();
        progressBar.setVisibility(View.GONE);

        //get请求
        getBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progressBar.setVisibility(View.VISIBLE);
				// 一个url地址
				String urlString = "https://raw.githubusercontent.com/koush/AndroidAsync/master/AndroidAsync/test/assets/test.json"; 
				FutureCallback<String> callback =new FutureCallback<String> (){

					@Override
					public void onCompleted(Exception e, String result) {
						 Message msg = new Message(); 
						 msg.what =  MSG_HIDE_PROGRESS;
						 mHandler.sendMessage(msg);
						if((e !=null)||(result==null)){
							AfToastUtil.showToast(HttpActivity.this,"网络访问错误");
							return;
						}
						AfDialogUtil.showAlertDialog(HttpActivity.this,"返回结果",result.trim(),new AfDialogOnClickListener(){

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
					
				};
				Ion.with(HttpActivity.this)
				.load(urlString)
				.setHandler(null)
				.asString()
				.setCallback(callback);
				
			}
		});
        
        //post请求
        postBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				progressBar.setVisibility(View.VISIBLE);
				String urlString = "https://httpbin.org/post"; 
				FutureCallback<String> callback =new FutureCallback<String> (){
				@Override
				public void onCompleted(Exception e, String result) {
					 Message msg = new Message(); 
					 msg.what =  MSG_HIDE_PROGRESS;
					 mHandler.sendMessage(msg);
					if((e !=null)||(result==null)){
						AfToastUtil.showToast(HttpActivity.this,"网络访问错误");
						return;
					}
					AfDialogUtil.showAlertDialog(HttpActivity.this,"返回结果",result.trim(),new AfDialogOnClickListener(){

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
				};
				
				Ion.with(HttpActivity.this)
				.load(urlString)
				.setHandler(null)
				.setBodyParameter("custemail","zxy@lyyj.com")
				.setBodyParameter("custname","zxy") 
				.setBodyParameter("custtel","10000")
				.setBodyParameter("size","small")
				.setBodyParameter("topping","onion")
				.asString()
				.setCallback(callback);
			};
		});
        
        //字节数组下载
        byteBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ivByte.setVisibility(View.VISIBLE);
				String urlString = "http://img4.duitang.com/uploads/item/201405/28/20140528203047_y5Swe.thumb.700_0.jpeg";
				progressBar.setVisibility(View.VISIBLE);
				// 一个url地址
				//String urlString = "https://raw.githubusercontent.com/koush/AndroidAsync/master/AndroidAsync/test/assets/test.json"; 
				FutureCallback<byte[]> callback =new FutureCallback<byte[]> (){

					@Override
					public void onCompleted(Exception e, byte[] result) {
						 Message msg = new Message(); 
						 msg.what =  MSG_HIDE_PROGRESS;
						 mHandler.sendMessage(msg);
						if((e !=null)||(result==null)){
							AfToastUtil.showToast(HttpActivity.this,"网络访问错误");
							return;
						}
						 Message msg1 = new Message(); 
						 msg1.what =  MSG_BYTE_DATA;
						 Bundle bundle = new Bundle();
						 bundle.putByteArray("bytebitmap",result);
						 msg1.setData(bundle);
						 mHandler.sendMessage(msg1);
						
					}
					
				};
				Ion.with(HttpActivity.this)
				.load(urlString)
				.setHandler(null)
				.asByteArray()
				.setCallback(callback);
				
			}
		});
 

        //文件下载
        fileDownBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				

                if (downloading != null && !downloading.isCancelled()) {
                    downloading.cancel();
                    downloading = null;
                    return;
                }

                // this is a 180MB zip file to test with
            	View vpb = LayoutInflater.from(HttpActivity.this).inflate(R.layout.progress_bar_horizontal, null, false);
            	mAfProgressBar = (AfHorizontalProgressBar) vpb.findViewById(R.id.horizontalProgressBar);
            	numberText = (TextView) vpb.findViewById(R.id.numberText);
        		maxText = (TextView) vpb.findViewById(R.id.maxText);
        		 
        		maxText.setText(progress+"/"+String.valueOf(max));
        		mAfProgressBar.setMax(max);
        		mAfProgressBar.setProgress(progress);
            	
        	    mAlertDialog = AfDialogUtil.showAlertDialog("正在下载",vpb);
                downloading = Ion.with(HttpActivity.this)
                .load("http://img4.duitang.com/uploads/item/201405/28/20140528203047_y5Swe.thumb.700_0.jpeg")
                    .progressHandler(new ProgressCallback() {
                        @Override
                        public void onProgress(long downloaded, long total) {
    						maxText.setText(downloaded/(total/max)+"/"+max);
    						mAfProgressBar.setProgress((int)(downloaded/(total/max)));
                        }
                    })
                    // write to a file
                    .write(getFileStreamPath("20140528203047_y5Swe.thumb.700_0.jpeg"))
                    // run a callback on completion
                    .setCallback(new FutureCallback<File>() {
                        @Override
                        public void onCompleted(Exception e, File result) {
                            downloading.cancel();
                            downloading = null;
//    		            	//下载完成取消进度框
    		            	if(mAlertDialog!=null){
    		            		mAlertDialog.dismiss();
    			            	mAlertDialog  = null;
    		            	}
                            if (e != null) {
                            	AfToastUtil.showToast(HttpActivity.this, "Error downloading file"+e.getMessage());                             
                                return;
                            }
   						 Message msg1 = new Message(); 
   						 msg1.what =  MSG_BYTE_DATA;
   						 Bundle bundle = new Bundle();
   						 bundle.putByteArray("bytebitmap",AfFileUtil.getByteArrayFromSD(result.getPath()));
   						 msg1.setData(bundle);
   						 mHandler.sendMessage(msg1);
                        }
                    });
            }
		});
        
        //文件上传
        
        fileUploadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (uploading != null && !uploading.isCancelled()) {
                    uploading.cancel();
                    uploading = null;
                    return;
                }
                // this is a 180MB zip file to test with
              	View vpb = LayoutInflater.from(HttpActivity.this).inflate(R.layout.progress_bar_horizontal, null, false);
              	mAfProgressBar = (AfHorizontalProgressBar) vpb.findViewById(R.id.horizontalProgressBar);
              	numberText = (TextView) vpb.findViewById(R.id.numberText);
          		maxText = (TextView) vpb.findViewById(R.id.maxText);
          		 
          		maxText.setText(progress+"/"+String.valueOf(max));
          		mAfProgressBar.setMax(max);
          		mAfProgressBar.setProgress(progress);
              	
          	    mAlertDialog = AfDialogUtil.showAlertDialog("正在上传",vpb);
                
                //File f = getFileStreamPath("largefile");
				File pathRoot = Environment.getExternalStorageDirectory();
				String path = pathRoot.getAbsolutePath();
				File file1 = new File(path+"/baoruan_temp1.apk");
				File file2 = new File(path+"/zhy_ormlite.rar");
//                try {
//                    RandomAccessFile rf = new RandomAccessFile(f, "rw");
//                    rf.setLength(1024 * 1024 * 2);
//                } catch (Exception e) {
//                    System.err.println(e);
//                }
              //  File echoedFile = getFileStreamPath("echo.apk");
				final File echoedFile =new File(path+"/echo.apk");
				
				  // this is a 180MB zip file to test with
                uploading = Ion.with(HttpActivity.this)
                .load("http://koush.clockworkmod.com/test/echo")
                // attach the percentage report to a progress bar.
                // can also attach to a ProgressDialog with progressDialog.
                //.uploadProgressBar(progressBar)
                // callbacks on progress can happen on the UI thread
                // via progressHandler. This is useful if you need to update a TextView.
                // Updates to TextViews MUST happen on the UI thread.
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
						maxText.setText(downloaded/(total/max)+"/"+max);
						mAfProgressBar.setProgress((int)(downloaded/(total/max)));
                    }
                })
                // write to a file
                .setMultipartFile("baoruan", file1)
                .write(echoedFile)
                // run a callback on completion
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File result) {
                    	 if (uploading != null && !uploading.isCancelled()) {
                             uploading.cancel();
                             uploading = null;
                         }
 		            	if(mAlertDialog!=null){
		            		mAlertDialog.dismiss();
			            	mAlertDialog  = null;
		            	}
                         if (e != null) {
                             Toast.makeText(HttpActivity.this, "Error uploading file", Toast.LENGTH_LONG).show();
                             return;
                         }
                         AfFileUtil.copyFile(result,echoedFile);
                         Toast.makeText(HttpActivity.this, "File upload complete", Toast.LENGTH_LONG).show();
                     }
                });				

            }
  
 
		});       
      } 

    
    /** 
     * 接受消息，处理消息 ，此Handler会与当前主线程一块运行 
     * */ 
  
     class MyHandler extends Handler { 
         public MyHandler() { 
         } 
  
         public MyHandler(Looper L) { 
             super(L); 
         } 
  
         // 子类必须重写此方法，接受数据 
         @Override 
         public void handleMessage(Message msg) { 

             super.handleMessage(msg); 
             switch(msg.what){
             case MSG_SHOW_PROGRESS:
            	 break;
             case MSG_HIDE_PROGRESS:
            	 progressBar.setVisibility(View.GONE);
            	 break;
             case MSG_BYTE_DATA:
            	 ivByte.setVisibility(View.VISIBLE);
            	 Bitmap bitmap = AfImageUtil.bytes2Bimap(msg.getData().getByteArray("bytebitmap"));
            	ivByte.setImageBitmap(bitmap);
            	 break;          	 
             }
 
  
         } 
     } 
 

     
}
