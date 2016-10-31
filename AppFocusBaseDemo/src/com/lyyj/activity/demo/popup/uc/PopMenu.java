package com.lyyj.activity.demo.popup.uc;

import java.util.ArrayList;
import java.util.HashMap;
 


import com.appfocusbase.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("CutPasteId")
public class PopMenu {

	// 存储每个按钮点击后对应生成的drawable
	private HashMap<View, Drawable> mapDrawable = new HashMap<View, Drawable>();

	private Context context;
	private PopupWindow popupWindow;
	private ViewPager viewPager;
	private ArrayList<View> listViews;
	private int screenwidth;// 屏幕的宽度

	private int currentView = 0;// 当前视图
	private int viewOffset;// 动画图片偏移量
	private int imgWidth;// 图片宽度
	private ImageView iv_cursor;// 动画图片
	private TextView tv_main;
	private TextView tv_utils;
	private TextView tv_set;

	private int popupWindowHeight = 0;// popupWindow的高度
	private Drawable popBg; // 背景图片
	private Drawable bg;// 生成的背景图片

	public PopMenu(Context context, int drawableID) {
		this.context = context;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		View view = inflater.inflate(R.layout.lyyj_popup_uc_popmenu_layout, null);

		tv_main = (TextView) view.findViewById(R.id.tv_main);
		tv_utils = (TextView) view.findViewById(R.id.tv_utils);
		tv_set = (TextView) view.findViewById(R.id.tv_set);
		this.tv_main.setOnClickListener(new myOnClick(0));
		this.tv_utils.setOnClickListener(new myOnClick(1));
		this.tv_set.setOnClickListener(new myOnClick(2));

		iv_cursor = (ImageView) view.findViewById(R.id.iv_cursor);
		setCursorWidth();

		viewPager = (ViewPager) view.findViewById(R.id.viewPagerw);
		viewPager.setFocusableInTouchMode(true);
		viewPager.setFocusable(true);

		listViews = new ArrayList<View>();
		listViews.add(inflater.inflate(R.layout.lyyj_popup_uc_grid_menu_layout, null));
		listViews.add(inflater.inflate(R.layout.lyyj_popup_uc_grid_menu_layout, null));
		listViews.add(inflater.inflate(R.layout.lyyj_popup_uc_grid_menu_layout, null));
		viewPager.setAdapter(new myPagerAdapter());
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, context
				.getResources().getDimensionPixelSize(R.dimen.popmenu_h));
		// 生成drawable
		this.popBg = context.getResources().getDrawable(drawableID);
	}

	public void setCursorWidth() {
		screenwidth = getScreenWidth();
		imgWidth = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.lyyj_popup_uc_img_cursor).getWidth();// 获取图片宽度
		viewOffset = (screenwidth / 3 - imgWidth) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(viewOffset, 0);
		iv_cursor.setImageMatrix(matrix);
		Log.e("TAG", screenwidth + "");

	}

	public int getScreenWidth() {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		int screenW = dm.widthPixels;
		return screenW;

	}

	public void show(View parent, Context context) {
		bg = mapDrawable.get(parent);// 为节省资源，map中会保存以前生成的背景，根据父控件来获得
		popupWindowHeight = popupWindow.getHeight();// 得到popupWindow的高度，在popupWindow构造完后才能获取
		if (bg == null)// 背景为空
		{
			createDrawable(context);// 重新生成背景图
			mapDrawable.put(parent, bg);// 保存到map中
		}

		popupWindow.setBackgroundDrawable(bg);// 给popupWindow设置背景
		popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0,
				parent.getHeight());// 距离底部的位置
		popupWindow.setAnimationStyle(R.style.popwin_anim_style);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
	}

	public void dismiss() {
		popupWindow.dismiss();
	}

	/**
	 * 以一个Bitmap为画布，画上一个Bitmap
	 * 
	 * @param canvasBitmap
	 *            作为画布的Bitmap
	 * @param drawBitmap
	 *            要被绘制的Bitmap
	 * @param top
	 *            从画布的距离顶部的top位置开始
	 * @param left
	 *            从画布的距离左边的left位置开始
	 */
	private void drawbitMap(Bitmap canvasBitmap, Bitmap drawBitmap, int top,
			int left) {
		Canvas localCanvas = new Canvas(canvasBitmap);// 以canvasBitmap生成画布
		localCanvas.drawBitmap(drawBitmap, left, top, null);// 在画布上移left和top左标开始绘制drawBitmap
		localCanvas.save(Canvas.ALL_SAVE_FLAG);// 保存
		localCanvas.restore();
		drawBitmap.recycle();// 释放掉drawBitmap，防止内存泄漏
	}

	/**
	 * 把Drawable生成对应的Bitmap
	 * 
	 * @param paramRect
	 *            生成的Bitmap大小等一些参数
	 * @param drawable
	 *            要绘制的drawable
	 * @param canvasBitmap
	 *            将drawable绘制到canvasBitmap中
	 */
	private void getBitMap(Rect paramRect, Drawable drawable,
			Bitmap canvasBitmap) {
		drawable.setBounds(0, 0, paramRect.right, paramRect.bottom);
		// 用canvasBitmap生成一个画布
		Canvas localCanvas = new Canvas(canvasBitmap);
		drawable.draw(localCanvas);// 使用drawable的draw方法画到画布上
		localCanvas.save(Canvas.ALL_SAVE_FLAG);// 保存
		localCanvas.restore();
	}

	private void createDrawable(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		@SuppressWarnings("deprecation")
		int width = wm.getDefaultDisplay().getWidth();
		// 图片的大小等一些参数
		Rect arrayOfRect = new Rect();
		arrayOfRect.top = 0;
		arrayOfRect.left = 0;
		arrayOfRect.right = width;
		arrayOfRect.bottom = this.popupWindowHeight;
		// 生成背景
		bg = getDrawable(context, arrayOfRect, popBg);
	}

	/**
	 * 生成背景图
	 */
	private Drawable getDrawable(Context context, Rect ArrayOfRect,
			Drawable ArrayOfDrawable) {
		Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
		// 先更具popupWindow的大小生成一个Bitmap
		Bitmap paramBitmap = Bitmap.createBitmap(screenwidth,
				popupWindowHeight, localConfig);
		Bitmap localBitmap = Bitmap.createBitmap(ArrayOfRect.right,
				ArrayOfRect.bottom, localConfig);
		getBitMap(ArrayOfRect, ArrayOfDrawable, localBitmap);// 得到相应的drawable的BitMap
		drawbitMap(paramBitmap, localBitmap, ArrayOfRect.top, ArrayOfRect.left);// 在paramBitmap中绘制localBitmap
		localBitmap.recycle();// 释放掉，要不多次运行有可能会内存泄漏
		return new BitmapDrawable(context.getResources(), paramBitmap);
	}

	public class myPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(listViews.get(arg1));
		}

		@Override
		public int getCount() {
			return listViews.size();
		}

		public Object instantiateItem(View arg0, int arg1) {

			if (arg1 < 3) {
				((ViewPager) arg0).addView(listViews.get(arg1 % 3), 0);
			}
			// 将来添加菜单的时候 新建一个gridviewadapter 然后new个gridview 添加到这里就可以
			GirdViewAdapter girdViewAdapter = new GirdViewAdapter(context);
			switch (arg1) {
			case 0:// 选项卡1
				GridView gridView = (GridView) arg0
						.findViewById(R.id.myGridView);
				gridView.setAdapter(girdViewAdapter);
				gridView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						switch (arg2) {
						default:
							Toast.makeText(context,
									"这个是GridView+ViewPager仿UC菜单栏" + arg2,
									Toast.LENGTH_LONG).show();
							break;
						}
					}
				});
				break;
			case 1:// 选项卡2
				GridView gridView2 = (GridView) arg0
						.findViewById(R.id.myGridView);
				gridView2.setAdapter(girdViewAdapter);
				gridView2.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						switch (arg2) {
						default:
							Toast.makeText(context,
									"这个是GridView+ViewPager仿UC菜单栏" + arg2,
									Toast.LENGTH_LONG).show();
							break;
						}

					}
				});
				break;
			case 2:// 选项卡3
				GridView gridView3 = (GridView) arg0
						.findViewById(R.id.myGridView);
				gridView3.setAdapter(girdViewAdapter);
				gridView3.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						switch (arg2) {
						default:
							Toast.makeText(context,
									"这个是GridView+ViewPager仿UC菜单栏" + arg2,
									Toast.LENGTH_LONG).show();
							break;
						}
					}
				});
				break;
			}
			return listViews.get(arg1);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		int one = viewOffset * 2 + imgWidth;// 页卡1 -> 页卡2 偏移量
		int two = one * 2;// 页卡1 -> 页卡3 偏移量

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currentView == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (currentView == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
				}
				break;
			case 1:
				if (currentView == 0) {
					animation = new TranslateAnimation(viewOffset, one, 0, 0);
				} else if (currentView == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currentView == 0) {
					animation = new TranslateAnimation(viewOffset, two, 0, 0);
				} else if (currentView == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
				}
				break;

			}

			currentView = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			iv_cursor.startAnimation(animation);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

	}

	/*
	 * 
	 * 对选项卡单击监听的实现方法
	 */
	public class myOnClick implements View.OnClickListener {
		int index = 0;

		public myOnClick(int currentIndex) {
			index = currentIndex;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(index);
		}
	}

}
