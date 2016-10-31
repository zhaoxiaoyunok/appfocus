package com.lyyj.activity.demo.popup.uc;

 

import com.appfocusbase.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GirdViewAdapter extends BaseAdapter {
	private int imgRecouse[];
	private String title[];
	LayoutInflater inflater;
	Context context;

	public GirdViewAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		imgRecouse = new int[] { R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher, R.drawable.ic_launcher,
				R.drawable.ic_launcher };
		title = new String[] { "加入书签", "书签/历史", "刷新", "夜间模式", "账户", "下载文件",
				"全屏", "退出" };
	}

	public int getCount() {
		return imgRecouse.length;
	}

	@Override
	public Object getItem(int position) {
		return imgRecouse[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View currentView, ViewGroup arg2) {
		currentView = inflater.inflate(R.layout.lyyj_popup_uc_child_imgbtn_layout, null);
		ImageView imageView = (ImageView) currentView
				.findViewById(R.id.imgbtn_img);
		TextView textView = (TextView) currentView
				.findViewById(R.id.imgbtn_text);
		imageView.setBackgroundResource(imgRecouse[position]);
		textView.setText(title[position]);
		return currentView;
	}

}
