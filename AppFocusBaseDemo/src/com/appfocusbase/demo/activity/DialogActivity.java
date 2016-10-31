package com.appfocusbase.demo.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.af.activity.AfActivity;
import com.af.fragment.AfAlertDialogFragment.AfDialogOnClickListener;
import com.af.fragment.AfDialogFragment;
import com.af.fragment.AfDialogFragment.AfDialogOnLoadListener;
import com.af.fragment.AfLoadDialogFragment;
import com.af.fragment.AfRefreshDialogFragment;
import com.af.util.AfDialogUtil;
import com.af.util.AfToastUtil;
import com.af.view.ioc.AfIocView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * 名称：DemoAbActivity 描述：AbActivity基本用法
 * 
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class DialogActivity extends AfActivity {

	private MyApplication application;

	
    @AfIocView(id = R.id.button2,click="btnClick")Button button2;
    @AfIocView(id = R.id.button3,click="btnClick")Button button3;
    @AfIocView(id = R.id.button4,click="btnClick")Button button4;
    @AfIocView(id = R.id.button5,click="btnClick")Button button5;
    @AfIocView(id = R.id.button6,click="btnClick")Button button6;
    @AfIocView(id = R.id.button7,click="btnClick")Button button7;
    @AfIocView(id = R.id.button8,click="btnClick")Button button8;
    @AfIocView(id = R.id.button9,click="btnClick")Button button9;
    @AfIocView(id = R.id.button10,click="btnClick")Button button10;
    @AfIocView(id = R.id.button11,click="btnClick")Button button11;
    @AfIocView(id = R.id.button12,click="btnClick")Button button12;
    @AfIocView(id = R.id.button13,click="btnClick")Button button13;
    @AfIocView(id = R.id.button14,click="btnClick")Button button14;
    @AfIocView(id = R.id.button15,click="btnClick")Button button15;
    @AfIocView(id = R.id.button16,click="btnClick")Button button16;
    @AfIocView(id = R.id.button17,click="btnClick")Button button17;
    @AfIocView(id = R.id.button18,click="btnClick")Button button18;
    @AfIocView(id = R.id.button19,click="btnClick")Button button19;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setAfContentView(R.layout.dialog_main);

		AfTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.dialog_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		application = (MyApplication) mAfApplication;
	}

	/**
	 * 下载数据
	 * 
	 * @param mDialogFragment
	 */
	public void downRss(final AfDialogFragment mDialogFragment) {
		
		FutureCallback<String> callback =new FutureCallback<String> (){

			@Override
			public void onCompleted(Exception e, String result) {

				if((e !=null)||(result==null)){
					AfToastUtil.showToast(DialogActivity.this,"网络访问错误");
					return;
				}
				mDialogFragment.loadFinish();
				AfDialogUtil.showAlertDialog(DialogActivity.this,
						R.drawable.ic_alert, "返回结果", result,
						new AfDialogOnClickListener() {

							@Override
							public void onPositiveClick() {
								AfToastUtil.showToast(DialogActivity.this,
										"点击了确认");

							}

							@Override
							public void onNegativeClick() {
								AfToastUtil.showToast(DialogActivity.this,
										"点击了取消");

							}

						});
			}			
		};
		Ion.with(DialogActivity.this)
		.load("http://www.amsoft.cn/rss.php")
		.setHandler(null)
		.asString()
		.setCallback(callback);		
			
	}


	/**
	 * 显示刷新弹出框有背景层
	 */
	public void showLoadDialog() {

		final AfLoadDialogFragment mDialogFragment = AfDialogUtil
				.showLoadDialog(this, R.drawable.ic_load, "正在查询,请稍候");
		mDialogFragment.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});
		// 取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AfToastUtil.showToast(DialogActivity.this, "Load框被取消");
			}

		});
	}

	/**
	 * 显示加载弹出框无背景层
	 */
	public void showLoadPanel() {

		final AfLoadDialogFragment mDialogFragment = AfDialogUtil
				.showLoadPanel(this, R.drawable.ic_load, "正在查询,请稍候");
		mDialogFragment.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});
		// 取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AfToastUtil.showToast(DialogActivity.this, "Load框被取消");
			}

		});
	}

	/**
	 * 显示刷新弹出框有背景层
	 */
	public void showRefreshDialog() {
		// 显示重新刷新的框
		final AfRefreshDialogFragment mDialogFragment = AfDialogUtil
				.showRefreshDialog(this, R.drawable.ic_refresh, "请求出错，请重试");
		mDialogFragment.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});
		// 取消的监听
		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AfToastUtil.showToast(DialogActivity.this, "refresh框被取消");
			}

		});
	}

	/**
	 * 显示刷新弹出框无背景层
	 */
	public void showRefreshPanel() {
		// 显示重新刷新的框
		final AfRefreshDialogFragment mDialogFragment = AfDialogUtil
				.showRefreshPanel(this, R.drawable.ic_refresh, "请求出错，请重试");
		mDialogFragment.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downRss(mDialogFragment);
			}

		});

		mDialogFragment.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				AfToastUtil.showToast(DialogActivity.this, "load框被取消");
			}

		});
	}
	
	
	public void btnClick(View v){
		View mView = null;
		switch (v.getId()) {
		case R.id.button2:
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showDialog(mView,
			new DialogInterface.OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					AfToastUtil.showToast(DialogActivity.this,
							"弹出框被取消");
				}
			});
			break;
		case R.id.button3:
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			// AbDialogUtil.showPanel(mView);
			AfDialogUtil.showPanel(mView,
				new DialogInterface.OnCancelListener() {

					@Override
					public void onCancel(DialogInterface dialog) {
						AfToastUtil.showToast(DialogActivity.this,
								"弹出框被取消");
					}
			});
			break;
		case R.id.button4:
			// 显示有背景层的加载的弹出框
			showLoadDialog();
			break;
		case R.id.button5:
			// 显示无背景层的加载的弹出框
			showLoadPanel();
			break;
		case R.id.button6:
			// 显示有背景层的刷新的弹出框
			showRefreshDialog();
			break;
		case R.id.button7:
			// 显示无背景层的刷新的弹出框
			showRefreshPanel();
			break;
		case R.id.button8:
			AfDialogUtil.showAlertDialog(DialogActivity.this,
				R.drawable.ic_alert, "这里是标题", "这里写一些描述",
				new AfDialogOnClickListener() {

					@Override
					public void onPositiveClick() {
						AfToastUtil.showToast(DialogActivity.this,
								"点击了确认");

					}

					@Override
					public void onNegativeClick() {
						AfToastUtil.showToast(DialogActivity.this,
								"点击了取消");

					}
			});
			break;
		case R.id.button9:
			AfDialogUtil.showAlertDialog(DialogActivity.this,
					R.drawable.ic_alert, "这里是标题", "这里写一些描述",
					null);
			break;
		case R.id.button10:
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showAlertDialog(mView);
			break;
		case R.id.button11:
			//无按钮＋动画
			mView = mInflater.inflate(R.layout.dialog_text,null);
			AfDialogUtil.showDialog(mView,R.animator.fragment_top_enter,R.animator.fragment_top_exit,R.animator.fragment_pop_top_enter,R.animator.fragment_pop_top_exit);
			break;
		case R.id.button12:
			//按钮＋列表
			mView = mInflater.inflate(R.layout.dialog_button_listview,null);
			AfDialogUtil.showDialog(mView,R.animator.fragment_top_enter,R.animator.fragment_top_exit,R.animator.fragment_pop_top_enter,R.animator.fragment_pop_top_exit);
			ListView listView = (ListView)mView.findViewById(R.id.list);
			String[] mStrings = {
		            "对话框选项item1", "对话框选项item2", "对话框选项item3", "对话框选项item4"};
			listView.setAdapter(new ArrayAdapter<String>(this,
		               R.layout.dialog_list_item_1, mStrings));
			Button leftBtn = (Button)mView.findViewById(R.id.left_btn);
			Button rightBtn = (Button)mView.findViewById(R.id.right_btn);
			leftBtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					AfDialogUtil.removeDialog(DialogActivity.this);
				}
				
			});
			
			rightBtn.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					AfDialogUtil.removeDialog(DialogActivity.this);
				}
				
			});
			
			break;
		case R.id.button13:
			//按钮＋文本
			//动画未生效
			mView = mInflater.inflate(R.layout.dialog_text_button,null);
			AfDialogUtil.showDialog(mView,R.animator.fragment_top_enter,R.animator.fragment_top_exit,R.animator.fragment_pop_top_enter,R.animator.fragment_pop_top_exit);
			Button leftBtn1 = (Button)mView.findViewById(R.id.left_btn);
			Button rightBtn1 = (Button)mView.findViewById(R.id.right_btn);
			leftBtn1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					AfDialogUtil.removeDialog(DialogActivity.this);
				}
				
			});
			
			rightBtn1.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					AfDialogUtil.removeDialog(DialogActivity.this);
				}
				
			});
			break;
			
		case R.id.button14:
			//上
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showDialog(mView,Gravity.TOP);
			break;
		case R.id.button15:
			//中
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showDialog(mView,Gravity.CENTER);
			break;
		case R.id.button16:
			//下
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showDialog(mView,Gravity.BOTTOM);
			break;
		case R.id.button17:
			//全屏
			mView = mInflater.inflate(R.layout.dialog_custom_view,null);
			AfDialogUtil.showFullScreenDialog(mView);
			break;
		case R.id.button18:
			AfDialogUtil.showProgressDialog(DialogActivity.this, 0,
					"查询中...");
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					AfDialogUtil.removeDialog(DialogActivity.this);
				}
			}, 2000);
			break;
		case R.id.button19:
			AfToastUtil.showToast(DialogActivity.this, "Toast提示框");
			break;
		default:
			break;
		}
	}

}
