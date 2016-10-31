package com.lyyj.activity.demo.gallery.staggeredgridview;

import com.appfocusbase.R;
import com.lyyj.activity.demo.gallery.staggeredgridview.loader.ImageLoader;
import com.lyyj.activity.demo.gallery.staggeredgridview.views.ScaleImageView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;



public class StaggeredAdapterAssets extends ArrayAdapter<String> {

	private ImageLoader mLoader;

	public StaggeredAdapterAssets(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		mLoader = new ImageLoader(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater layoutInflator = LayoutInflater.from(getContext());
			convertView = layoutInflator.inflate(R.layout.lyyj_gallery_staggeredgridview_row_staggered_demo,
					null);
			holder = new ViewHolder();
			holder.imageView = (ScaleImageView) convertView .findViewById(R.id.imageView1);
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		mLoader.DisplayImage(getItem(position), holder.imageView);

		return convertView;
	}

	static class ViewHolder {
		ScaleImageView imageView;
	}
}
