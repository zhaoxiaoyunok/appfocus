package com.lyyj.activity.demo.listview.multichoicewithiconandpreviewgrid;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.appfocusbase.R;
import com.lyyj.activity.demo.listview.multichoicewithiconandpreviewgrid.MultiChoiceWithIconAndPreviewGridMainActivity.CheckedImg;

public class CheckedImgAdapter extends BaseAdapter {
	private Context ct;
	private ArrayList<CheckedImg> data;

	public ArrayList<CheckedImg> getData() {
		return data;
	}

	public CheckedImgAdapter(Context ct, ArrayList<CheckedImg> data) {
		this.ct = ct;
		this.data = data;
	}

	public void addImg(String name, String touxiang, String id) {
		CheckedImg map = new CheckedImg();
		map.name = name;
		map.touxiang = touxiang;
		map.id = id;
		data.add(map);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CheckedImg map = data.get(position);
		final String touxiang = map.touxiang;
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(ct).inflate(
					R.layout.lyyj_lv_multichoicewithiconandpreviewgrid_checked_contact_item, null);

			viewHolder = new ViewHolder();
			viewHolder.touxiang = (ImageView) convertView
					.findViewById(R.id.contactitem_touxiang);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		if (map.id.equals("default"))
			viewHolder.touxiang.setImageDrawable(ct.getResources().getDrawable(
					R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_none));
		else 
			viewHolder.touxiang.setImageDrawable(getImage(touxiang));
		viewHolder.id = map.id;
		return convertView;
	}

	private Drawable getImage(String touxiang) {
		switch (Integer.parseInt(touxiang)) {
		case 0:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img0);
		case 1:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img1);
		case 2:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img2);
		case 3:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img3);
		case 4:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img4);
		case 5:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img5);
		case 6:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img6);
		case 7:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img7);
		case 8:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img8);
		case 9:
			return ct.getResources().getDrawable(R.drawable.lyyj_lv_multichoicewithiconandpreviewgrid_img9);
		default:
			throw new IllegalArgumentException("�����ͼƬ����");
		}
	}

	static class ViewHolder {
		String id;
		ImageView touxiang;
	}
}