package com.appfocusbase.main;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

 














import com.af.cache.image.AfImageBaseCache;
import com.af.fragment.AfAlertDialogFragment.AfDialogOnClickListener;
import com.af.model.AfMenuItem;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListener;
import com.af.util.AfAnimationUtil;
import com.af.util.AfDialogUtil;
import com.af.util.AfFileUtil;
import com.af.util.AfToastUtil;
import com.appfocusbase.R;
import com.appfocusbase.demo.activity.DemoMainActivity;
import com.appfocusbase.global.MyApplication;
import com.appfocusbase.login.AboutActivity;
import com.appfocusbase.model.User;
import com.koushikdutta.ion.Ion;

public class MainMenuFragment extends Fragment {

	private MyApplication application;
	private MainActivity mActivity = null;
	private ExpandableListView mMenuListView;
	private ArrayList<String> mGroupName = null;
	private ArrayList<ArrayList<AfMenuItem>> mChilds = null;
	private ArrayList<AfMenuItem> mChild1 = null;
	private ArrayList<AfMenuItem> mChild2 = null;
	private LeftMenuAdapter mAdapter;
	private OnChangeViewListener mOnChangeViewListener;
	private TextView mNameText;
	private TextView mUserPoint;
	private ImageView mUserPhoto;
	private ImageView sunshineView;

	private RelativeLayout loginLayout = null;
	private User mUser = null;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mActivity = (MainActivity) this.getActivity();
		application = (MyApplication) mActivity.getApplication();

		View view = inflater.inflate(R.layout.main_menu, null);
		mMenuListView = (ExpandableListView) view.findViewById(R.id.menu_list);

		mNameText = (TextView) view.findViewById(R.id.user_name);
		mUserPhoto = (ImageView) view.findViewById(R.id.user_photo);
		mUserPoint = (TextView) view.findViewById(R.id.user_point);
		sunshineView = (ImageView) view.findViewById(R.id.sunshineView);
		loginLayout = (RelativeLayout) view.findViewById(R.id.login_layout);
		Button cacheClearBtn = (Button) view.findViewById(R.id.cacheClearBtn);

		cacheClearBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AfDialogUtil.showProgressDialog(mActivity,0, "正在清空缓存...");
				AfTask task = AfTask.newInstance();
				// 定义异步执行的对象
				final AfTaskItem item = new AfTaskItem();
				item.setListener(new AfTaskListener() {

					@Override
					public void update() {
						AfDialogUtil.removeDialog(mActivity);
						AfToastUtil.showToast(mActivity, "缓存已清空完成");
					}

					@Override
					public void get() {
						try {
							AfFileUtil.clearDownloadFile();
							AfImageBaseCache.getInstance().clearBitmap();
						} catch (Exception e) {
							AfToastUtil.showToastInThread(mActivity,
									e.getMessage());
						}
					};
				});
				task.execute(item);

			}
		});

		mGroupName = new ArrayList<String>();
		mChild1 = new ArrayList<AfMenuItem>();
		mChild2 = new ArrayList<AfMenuItem>();

		ArrayList<ArrayList<AfMenuItem>> mChilds = new ArrayList<ArrayList<AfMenuItem>>();
		mChilds.add(mChild1);
		mChilds.add(mChild2);

		mAdapter = new LeftMenuAdapter(mActivity, mGroupName, mChilds);
		mMenuListView.setAdapter(mAdapter);
		for (int i = 0; i < mGroupName.size(); i++) {
			mMenuListView.expandGroup(i);
		}

		mMenuListView.setOnGroupClickListener(new OnGroupClickListener() {

			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});

		mMenuListView.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				if (mOnChangeViewListener != null) {
					mOnChangeViewListener.onChangeView(groupPosition,
							childPosition);
				}
				return true;
			}
		});

		initMenu();

		AfAnimationUtil.playRotateAnimation(sunshineView, 2000, 5,
				Animation.RESTART);

		return view;
	}

	public interface OnChangeViewListener {
		public abstract void onChangeView(int groupPosition, int childPosition);
	}

	public void setOnChangeViewListener(
			OnChangeViewListener onChangeViewListener) {
		mOnChangeViewListener = onChangeViewListener;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	public void initMenu() {
		mGroupName.clear();
		mChild1.clear();
		mChild2.clear();

		mGroupName.add("常用");
		mGroupName.add("操作");

		AfMenuItem m0 = new AfMenuItem();
		m0.setIconId(R.drawable.square);
		m0.setText("联系人");
		mChild1.add(m0);

		AfMenuItem m1 = new AfMenuItem();
		m1.setIconId(R.drawable.square);
		m1.setText("我的消息");
		mChild1.add(m1);

		AfMenuItem m3 = new AfMenuItem();
		m3.setIconId(R.drawable.share);
		m3.setText("程序案例");
		mChild1.add(m3);

		AfMenuItem m4 = new AfMenuItem();
		m4.setIconId(R.drawable.app);
		m4.setText("应用游戏");
		mChild1.add(m4);

		AfMenuItem m5 = new AfMenuItem();
		m5.setIconId(R.drawable.set);
		m5.setText("支持我");
		mChild2.add(m5);

		AfMenuItem m6 = new AfMenuItem();
		m6.setIconId(R.drawable.recommend);
		m6.setText("推荐给好友");
		mChild2.add(m6);

		mUser = application.mUser;
		if (mUser != null) {
			AfMenuItem m7 = new AfMenuItem();
			m7.setIconId(R.drawable.quit);
			m7.setText("注销");
			mChild2.add(m7);
		}

		AfMenuItem m8 = new AfMenuItem();
		m8.setIconId(R.drawable.about);
		m8.setText("关于");
		mChild2.add(m8);
		mAdapter.notifyDataSetChanged();
		for (int i = 0; i < mGroupName.size(); i++) {
			mMenuListView.expandGroup(i);
		}

		if (mUser == null) {
			setNameText("登录");
			setUserPhoto(R.drawable.photo01);
			setUserPoint("0");
			mNameText.setCompoundDrawables(null, null, null, null);
			loginLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (mUser == null) {
						mActivity.toLogin(mActivity.LOGIN_CODE);
					}
				}
			});
		} else {
			setNameText(mUser.getName());
			downSetPhoto(mUser.getHeadUrl());
			if ("MAN".equals(mUser.getSex())) {
				Drawable d = mActivity.getResources().getDrawable(
						R.drawable.user_info_male);
				d.setBounds(0, 0, 26, 26);
				mNameText.setCompoundDrawables(null, null, d, null);
			} else if ("WOMAN".equals(mUser.getSex())) {
				Drawable d = mActivity.getResources().getDrawable(
						R.drawable.user_info_female);
				d.setBounds(0, 0, 26, 26);
				mNameText.setCompoundDrawables(null, null, d, null);
			} else {
				mNameText.setCompoundDrawables(null, null, null, null);
			}

			setUserPoint(String.valueOf(mUser.getPoint()));
			loginLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

				}
			});
		}
		final String shareStr = this.getResources().getString(
				R.string.share_desc);
		setOnChangeViewListener(new OnChangeViewListener() {

			@Override
			public void onChangeView(int groupPosition, int childPosition) {
				if (groupPosition == 0) {
					if (childPosition == 0) {
						// 联系人
						if (application.mUser == null) {
							mActivity.toLogin(mActivity.FRIEND_CODE);
						} else {
//							Intent intent = new Intent(mActivity,
//									ContacterActivity.class);
//							mActivity.startActivity(intent);
						}
					} else if (childPosition == 1) {
						// 我的消息
//						Intent intent = new Intent(mActivity,
//								MessageActivity.class);
//						startActivity(intent);
					} else if (childPosition == 2) {
						// 程序案例
						Intent intent = new Intent(mActivity,
								DemoMainActivity.class);
						startActivity(intent);
					} else if (childPosition == 3) {
						// 应用游戏
						mActivity.showApp();
					}
				} else if (groupPosition == 1) {
					if (childPosition == 0) {
						// 选项、赞助作者
						mActivity.showApp();
					} else if (childPosition == 1) {
						// 推荐

					} else if (childPosition == 2) {
						if (mUser != null) {
							AfDialogUtil.showAlertDialog(mActivity, "注销",
									"确定要注销该用户吗?",
									new AfDialogOnClickListener() {

										@Override
										public void onPositiveClick() {
											// 注销
											application.clearLoginParams();
											initMenu();
											mActivity.stopIMService();
										}

										@Override
										public void onNegativeClick() {
											// TODO Auto-generated method stub

										}

									});

						} else {
							// 关于
							Intent intent = new Intent(mActivity,
									AboutActivity.class);
							startActivity(intent);
						}
					} else if (childPosition == 3) {
						if (application.mUser != null) {
							// 关于
							Intent intent = new Intent(mActivity,
									AboutActivity.class);
							startActivity(intent);
						} else {
							// 无
						}
					}
				}
			}

		});

	}

	/**
	 * 描述：用户名的设置
	 * 
	 * @param mNameText
	 */
	public void setNameText(String mNameText) {
		this.mNameText.setText(mNameText);
	}

	/**
	 * 描述：设置用户阳光
	 * 
	 * @param mPoint
	 */
	public void setUserPoint(String mPoint) {
		this.mUserPoint.setText(mPoint);
		AfAnimationUtil.playRotateAnimation(sunshineView, 2000, 5,
				Animation.RESTART);
	}

	public void downSetPhoto(String mPhotoUrl) {
		// 缩放图片的下载
		//mAbImageLoader.display(mUserPhoto, mPhotoUrl,150,150);
		Ion.with(mUserPhoto).placeholder(R.drawable.progress_loading2).resize(150,150).load(mPhotoUrl); 
	}

	/**
	 * 描述：设置头像
	 * 
	 * @param drawable
	 */
	public void setUserPhoto(int resId) {
		this.mUserPhoto.setImageResource(resId);
	}

}
