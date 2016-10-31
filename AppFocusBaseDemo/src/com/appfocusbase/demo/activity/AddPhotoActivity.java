package com.appfocusbase.demo.activity;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.af.activity.AfActivity;
import com.af.util.AfDialogUtil;
import com.af.util.AfFileUtil;
import com.af.util.AfLogUtil;
import com.af.util.AfStrUtil;
import com.af.util.AfToastUtil;
import com.af.view.progress.AfHorizontalProgressBar;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.ImageShowAdapter;
import com.appfocusbase.global.MyApplication;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.koushikdutta.ion.builder.Builders.Any.B;
import com.koushikdutta.ion.builder.Builders.Any.M;
import com.koushikdutta.ion.builder.LoadBuilder;
import com.koushikdutta.ion.builder.MultipartBodyBuilder;

public class AddPhotoActivity extends AfActivity {
	
	private MyApplication application;
	private GridView mGridView = null;
	private ImageShowAdapter mImagePathAdapter = null;
	private ArrayList<String> mPhotoList = null;
	private int selectIndex = 0;
	private int camIndex = 0;
	private View mAvatarView = null;
	
	/* 用来标识请求照相功能的activity */
	private static final int CAMERA_WITH_DATA = 3023;
	/* 用来标识请求gallery的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;
	/* 用来标识请求裁剪图片后的activity */
	private static final int CAMERA_CROP_DATA = 3022;
	
	/* 拍照的照片存储位置 */
	private  File PHOTO_DIR = null;
	// 照相机拍照得到的图片
	private File mCurrentPhotoFile;
	private String mFileName;
	
	/* ProgressBar进度控制 */
	private AfHorizontalProgressBar mAbProgressBar;
	/* 最大100 */
	private int max = 100;	
	private int progress = 0;
	private TextView numberText, maxText;
	private DialogFragment  mAlertDialog  = null;
    Future<File> uploading;		
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.add_photo);
		application = (MyApplication) mAfApplication;
		   
		AfTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.photo_add_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		
		initTitleRightLayout();
		
		mPhotoList = new ArrayList<String>();
		
		
        //默认
		mPhotoList.add(String.valueOf(R.drawable.cam_photo));
		
		mGridView = (GridView)findViewById(R.id.myGrid);
		mImagePathAdapter = new ImageShowAdapter(this, mPhotoList,116,116);
		mGridView.setAdapter(mImagePathAdapter);
	   
		//初始化图片保存路径
	    String photo_dir = AfFileUtil.getImageDownloadDir(this);
	    if(AfStrUtil.isEmpty(photo_dir)){
	    	AfToastUtil.showToast(AddPhotoActivity.this,"存储卡不存在");
	    }else{
	    	PHOTO_DIR = new File(photo_dir);
	    }
		
		
		Button addBtn = (Button)findViewById(R.id.addBtn);
		
		mGridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectIndex = position;
				if(selectIndex == camIndex){
					mAvatarView = mInflater.inflate(R.layout.choose_avatar, null); 
					Button albumButton = (Button)mAvatarView.findViewById(R.id.choose_album);
					Button camButton = (Button)mAvatarView.findViewById(R.id.choose_cam);
					Button cancelButton = (Button)mAvatarView.findViewById(R.id.choose_cancel);
					albumButton.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							AfDialogUtil.removeDialog(AddPhotoActivity.this);
							// 从相册中去获取
							try {
								Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
								intent.setType("image/*");
								startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
							} catch (ActivityNotFoundException e) {
								AfToastUtil.showToast(AddPhotoActivity.this,"没有找到照片");
							}
						}
						
					});
					
					camButton.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							AfDialogUtil.removeDialog(AddPhotoActivity.this);
							doPickPhotoAction();
						}
						
					});
					
					cancelButton.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							AfDialogUtil.removeDialog(AddPhotoActivity.this);
						}
						
					});
					AfDialogUtil.showDialog(mAvatarView,Gravity.BOTTOM);
				}else{
					for(int i=0;i<mImagePathAdapter.getCount();i++){
						ImageShowAdapter.ViewHolder mViewHolder = (ImageShowAdapter.ViewHolder)mGridView.getChildAt(i).getTag();
						if(mViewHolder!=null){
							mViewHolder.mImageView2.setBackgroundDrawable(null);
						}
					}
	
					ImageShowAdapter.ViewHolder mViewHolder = (ImageShowAdapter.ViewHolder)view.getTag();
					mViewHolder.mImageView2.setBackgroundResource(R.drawable.photo_select);
				}
			}
			
		});
		
		addBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				uploadFile(mPhotoList);
			}
		});
		
	}
	
	private void initTitleRightLayout() {
		
	}
	
	/**
	 * 从照相机获取
	 */
	private void doPickPhotoAction() {
		String status = Environment.getExternalStorageState();
		//判断是否有SD卡,如果有sd卡存入sd卡在说，没有sd卡直接转换为图片
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			doTakePhoto();
		} else {
			AfToastUtil.showToast(AddPhotoActivity.this,"没有可用的存储卡");
		}
	}

	/**
	 * 拍照获取图片
	 */
	protected void doTakePhoto() {
		try {
			mFileName = System.currentTimeMillis() + ".jpg";
			mCurrentPhotoFile = new File(PHOTO_DIR, mFileName);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (Exception e) {
			AfToastUtil.showToast(AddPhotoActivity.this,"未找到系统相机程序");
		}
	}
	
	/**
	 * 描述：因为调用了Camera和Gally所以要判断他们各自的返回情况,
	 * 他们启动时是这样的startActivityForResult
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent mIntent) {
		if (resultCode != RESULT_OK){
			return;
		}
		switch (requestCode) {
			case PHOTO_PICKED_WITH_DATA:
				Uri uri = mIntent.getData();
				String currentFilePath = getPath(uri);
				if(!AfStrUtil.isEmpty(currentFilePath)){
					Intent intent1 = new Intent(this, CropImageActivity.class);
					intent1.putExtra("PATH", currentFilePath);
					startActivityForResult(intent1, CAMERA_CROP_DATA);
		        }else{
		        	AfToastUtil.showToast(AddPhotoActivity.this,"未在存储卡中找到这个文件");
		        }
				break;
			case CAMERA_WITH_DATA:
				AfLogUtil.d(AddPhotoActivity.class, "将要进行裁剪的图片的路径是 = " + mCurrentPhotoFile.getPath());
				String currentFilePath2 = mCurrentPhotoFile.getPath();
				Intent intent2 = new Intent(this, CropImageActivity.class);
				intent2.putExtra("PATH",currentFilePath2);
				startActivityForResult(intent2,CAMERA_CROP_DATA);
				break;
			case CAMERA_CROP_DATA:
				String path = mIntent.getStringExtra("PATH");
		    	AfLogUtil.d(AddPhotoActivity.class, "裁剪后得到的图片的路径是 = " + path);
		    	mImagePathAdapter.addItem(mImagePathAdapter.getCount()-1,path);
		     	camIndex++;
				break;
		}
	}

	/**
	 * 从相册得到的url转换为SD卡中图片路径
	 */
	public String getPath(Uri uri) {
		if(AfStrUtil.isEmpty(uri.getAuthority())){
			return null;
		}
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		String path = cursor.getString(column_index);
		return path;
	}
	
	public void uploadFile(List<String> list){
	
			//多文件上传添加多个即可
//			params.put("data1",URLEncoder.encode("如果包含中文的处理方式",HTTP.UTF_8));
//			params.put("data2","100");
			//文件参数，去掉后边那个按钮


            if (uploading != null && !uploading.isCancelled()) {
                uploading.cancel();
                uploading = null;
                return;
            }
            // this is a 180MB zip file to test with
          	View vpb = LayoutInflater.from(AddPhotoActivity.this).inflate(R.layout.progress_bar_horizontal, null, false);
          	mAbProgressBar = (AfHorizontalProgressBar) vpb.findViewById(R.id.horizontalProgressBar);
          	numberText = (TextView) vpb.findViewById(R.id.numberText);
      		maxText = (TextView) vpb.findViewById(R.id.maxText);
      		 
      		maxText.setText(progress+"/"+String.valueOf(max));
      		mAbProgressBar.setMax(max);
      		mAbProgressBar.setProgress(progress);
          	
      	    mAlertDialog = AfDialogUtil.showAlertDialog("正在上传",vpb);
            
            //File f = getFileStreamPath("largefile");
			File pathRoot = Environment.getExternalStorageDirectory();
			String path = pathRoot.getAbsolutePath();

//            try {
//                RandomAccessFile rf = new RandomAccessFile(f, "rw");
//                rf.setLength(1024 * 1024 * 2);
//            } catch (Exception e) {
//                System.err.println(e);
//            }
          //  File echoedFile = getFileStreamPath("echo.apk");
			final File echoedFile =new File(path+"/echo.apk");
	

			  // this is a 180MB zip file to test with
			LoadBuilder<B>  ion  = Ion.with(AddPhotoActivity.this);
			for(int i=0;i<list.size()-1;i++){
				String path1 = list.get(i);
				File file = new File(path1);
				((MultipartBodyBuilder<M>) ion).setMultipartFile(file.getName(),file);
			}
            uploading = ion
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
					mAbProgressBar.setProgress((int)(downloaded/(total/max)));
                }
            })
            // write to a file
            //.setMultipartFile("baoruan",pathRoot)
       
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
                         Toast.makeText(AddPhotoActivity.this, "Error uploading file", Toast.LENGTH_LONG).show();
                         return;
                     }
                     AfFileUtil.copyFile(result,echoedFile);
                     Toast.makeText(AddPhotoActivity.this, "File upload complete", Toast.LENGTH_LONG).show();
                 }
            });				
            

	}

}
