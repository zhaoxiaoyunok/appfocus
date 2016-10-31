package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

 






import com.af.activity.AfActivity;
import com.af.util.AfToastUtil;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.MyListViewAdapter;
import com.appfocusbase.demo.db.activity.DBActivity;
import com.appfocusbase.global.Constant;
import com.appfocusbase.global.MyApplication;
import com.lyyj.activity.demo.anim.AnimationSetsActivity;
import com.lyyj.activity.demo.button.BtnSetsActivity;
import com.lyyj.activity.demo.calendar.CalendarSetsActivity;
import com.lyyj.activity.demo.dialog.DialogSetsActivity;
import com.lyyj.activity.demo.edittext.EditTextSetsActivity;
import com.lyyj.activity.demo.gallery.GallerySetsActivity;
import com.lyyj.activity.demo.gridview.GridViewSetsActivity;
import com.lyyj.activity.demo.guide.GuideSetsActivity;
import com.lyyj.activity.demo.expandlistview.ExpandListViewSetsActivity;
import com.lyyj.activity.demo.imageprocess.ImageProcessSetsActivity;
import com.lyyj.activity.demo.imageview.ImageViewSetsActivity;
import com.lyyj.activity.demo.listview.ListViewSetsActivity;
import com.lyyj.activity.demo.mainmenu.MainMenuSetsActivity;
import com.lyyj.activity.demo.menu.MenuSetsActivity;
import com.lyyj.activity.demo.popup.PopupSetsActivity;
import com.lyyj.activity.demo.progressbar.ProgressBarSetsActivity;
import com.lyyj.activity.demo.specialview.SpecialViewSetsActivity;
import com.lyyj.activity.demo.textview.TextViewSetsActivity;
import com.lyyj.activity.demo.toast.ToastSetsActivity;
/**
 * 
 * © 2015 lyyj.com
 * 名称：DemoMainActivity.java 
 * 描述：Demo主界面
 * @author kaka
 * 
 * @version v1.0
 */
public class DemoMainActivity extends AfActivity {
	
	private MyApplication application;
	private ListView mListView = null;
	private MyListViewAdapter myListViewAdapter = null;
	private List<Map<String, Object>> list = null;
	private ArrayList<String> mPhotoList = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.main);

		AfTitleBar mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.demo_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);
		// mAbTitleBar.setVisibility(View.GONE);
		// 设置AbTitleBar在最上
		this.setTitleBarOverlay(true);
		application = (MyApplication) mAfApplication;
		mAbTitleBar.getLogoView().setOnClickListener(
			new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
		});
		
		for (int i = 0; i < 75; i++) {
	            mPhotoList.add(Constant.BASEURL+"content/templates/amsoft/images/head/"+i+".png");
	    }

		// 获取ListView对象
		mListView = (ListView) findViewById(R.id.mListView);
		// ListView数据
		list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "AbActivity基本用法");
		map.put("itemsText", "AbActivity使用示例");
		map.put("itemIntent",TitleBarActivity.class);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "侧边栏");
		map.put("itemsText", "左右侧边栏");
		map.put("itemIntent",SlidingMenuActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "Tab切换");
		map.put("itemsText", "可滑动的tab标签,顶部和底部");
		map.put("itemIntent",TabActivity.class);		
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "一大波Dialog");
		map.put("itemsText", "一大波Dialog,正在靠近");
		map.put("itemIntent",DialogActivity.class);		
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "数据库ORM");
		map.put("itemsText", "注解，数据库对象映射");
		map.put("itemIntent",DBActivity.class);		
		list.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
//		map.put("itemsTitle", "IOC 适配View");
//		map.put("itemsText", "像findViewById说no");
//		map.put("itemIntent",IocViewActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "Http工具类");
		map.put("itemsText", "网络通信首选1");
		map.put("itemIntent",HttpActivity.class);		
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "Soap工具类");
		map.put("itemsText", "网络通信首选2");
		map.put("itemIntent",SoapActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "图片下载与处理");
		map.put("itemsText", "图片下载,裁剪,缩放");
		map.put("itemIntent",ImageDownActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "下载器");
		map.put("itemsText", "多线程，断点续传");
		map.put("itemIntent",DownListActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "UI控件汇总");
		map.put("itemsText", "一些常用的UI控件");
		map.put("itemIntent",UIElementActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "下拉刷新与分页查询");
		map.put("itemsText", "支持下拉刷新，上拉加载下一页");
		map.put("itemIntent",PullToRefreshActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "线程池与线程队列");
		map.put("itemsText", "适应于Http工具类管理范围外，更灵活的应用");
		map.put("itemIntent",ThreadControlActivity.class);		
		list.add(map);
	
//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
//		map.put("itemsTitle", "动画效果汇总");
//		map.put("itemsText", "一些常用的动画效果");
//		map.put("itemIntent",AnimationActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "图表");
		map.put("itemsText", "线状图，柱状图，饼状图，等级条图");
		map.put("itemIntent",ChartActivity.class);		
		list.add(map);

//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(15));
//		map.put("itemsTitle", "图片相近搜索");
//		map.put("itemsText", "phash算法");
//		map.put("itemIntent",PHashActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "旋转木马");
		map.put("itemsText", "旋转木马");
		map.put("itemIntent",CarouselActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "水平，环形进度条");
		map.put("itemsText", "漂亮的水平，环形进度条控件");
		map.put("itemIntent",ProgressBarActivity.class);		
		list.add(map);
//
//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
//		map.put("itemsTitle", "3D翻转效果");
//		map.put("itemsText", "2013纪念币,3D切换界面");
//		map.put("itemIntent",Rotate3DActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "各种滑动嵌套问题");
		map.put("itemsText", "各种滑动嵌套问题的解决例子");
		map.put("itemIntent",NestScrollActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "场景化UI");
		map.put("itemsText", "这玩意很流行");
		map.put("itemIntent",SceneActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "各种菜单");
		map.put("itemsText", "menu sets");
		map.put("itemIntent",MenuSetsActivity.class);		
		list.add(map);


		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "textview合集");
		map.put("itemsText", "史上最全textview");
		map.put("itemIntent",TextViewSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "imageview合集");
		map.put("itemsText", "史上最全imageview");
		map.put("itemIntent",ImageViewSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "gallery合集");
		map.put("itemsText", "史上最全gallery");
		map.put("itemIntent",GallerySetsActivity.class);		
		list.add(map);		

//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(22));
//		map.put("itemsTitle", "indicator合集");
//		map.put("itemsText", "史上最全indicator");
//		 map.put("itemIntent",TitleBarActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "dialog合集");
		map.put("itemsText", "史上最全dialog");
		map.put("itemIntent",DialogSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "toast合集");
		map.put("itemsText", "史上最全toast");
		map.put("itemIntent",ToastSetsActivity.class);		
		list.add(map);		

//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(22));
//		map.put("itemsTitle", "notification合集");
//		map.put("itemsText", "史上最全notification");
//		map.put("itemIntent",TitleBarActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "progressbar合集");
		map.put("itemsText", "史上最全progressbar");
		map.put("itemIntent",ProgressBarSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "listview合集");
		map.put("itemsText", "史上最全listview");
		map.put("itemIntent",ListViewSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "gridview合集");
		map.put("itemsText", "史上最全gridview");
		map.put("itemIntent",GridViewSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "expandlistview合集");
		map.put("itemsText", "史上最全expandlistview");
		map.put("itemIntent",ExpandListViewSetsActivity.class);		
		list.add(map);

//		map = new HashMap<String, Object>();
//		map.put("itemsIcon", mPhotoList.get(22));
//		map.put("itemsTitle", "mainmenu合集");
//		map.put("itemsText", "史上最全mainmenu");
//		map.put("itemIntent",TitleBarActivity.class);		
//		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "guidewelcome合集");
		map.put("itemsText", "史上最全guide/welcome");
		map.put("itemIntent",GuideSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "button合集");
		map.put("itemsText", "史上最全button");
		map.put("itemIntent",BtnSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "动画合集");
		map.put("itemsText", "史上最全animation");
		map.put("itemIntent",AnimationSetsActivity.class);		
		list.add(map);
	 	 		

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "EditText合集");
		map.put("itemsText", "史上最全edittext");
		map.put("itemIntent",EditTextSetsActivity.class);		
		list.add(map);


		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "图像处理合集");
		map.put("itemsText", "史上最全imageprocess");
		map.put("itemIntent",ImageProcessSetsActivity.class);		
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "Calendar合集");
		map.put("itemsText", "史上最全日历时间相关");
		map.put("itemIntent",CalendarSetsActivity.class);		
		list.add(map);
	 
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "SpecialView特色视图合集");
		map.put("itemsText", "史上最全特色视图相关");
		map.put("itemIntent",SpecialViewSetsActivity.class);		
		list.add(map);
	 
		map = new HashMap<String, Object>();
		map.put("itemsIcon", mPhotoList.get(list.size()<75? list.size():0));
		map.put("itemsTitle", "PopupWindow合集");
		map.put("itemsText", "史上最全弹出窗口");
		map.put("itemIntent",PopupSetsActivity.class);
		list.add(map);
		 	 		
		// 使用自定义的Adapter
		myListViewAdapter = new MyListViewAdapter(DemoMainActivity.this, list,
				R.layout.item_list, new String[] { "itemsIcon", "itemsTitle",
						"itemsText" }, new int[] { R.id.itemsIcon,
						R.id.itemsTitle, R.id.itemsText });
		mListView.setAdapter(myListViewAdapter);
		// item被点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Class<?> target = (Class<?>) list.get(position).get("itemIntent");
				if(null == target){
					AfToastUtil.showToast(view.getContext(), "本条目没有演示");
					return;
				}
				Intent intent = new Intent(DemoMainActivity.this, target);
				startActivity(intent);
			}
		});
	}

}
