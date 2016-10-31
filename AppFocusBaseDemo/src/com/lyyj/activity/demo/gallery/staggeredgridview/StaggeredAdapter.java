package com.lyyj.activity.demo.gallery.staggeredgridview;

import com.appfocusbase.R;
import com.lyyj.activity.demo.gallery.staggeredgridview.creditsrolldemo.CreditScrollMainActivity;
import com.lyyj.activity.demo.gallery.staggeredgridview.loader.ImageLoader;
import com.lyyj.activity.demo.gallery.staggeredgridview.views.ScaleImageView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;



public class StaggeredAdapter extends ArrayAdapter<String> {

	private ImageLoader mLoader;
     private Context mContext ;
     
 	
 	private int rawids[] = { 
 			R.raw.lyyj_gallery_staggeredgridview_test
 	};   
	public StaggeredAdapter(Context context, int textViewResourceId,
			String[] objects) {
		super(context, textViewResourceId, objects);
		mLoader = new ImageLoader(context);
		mContext = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

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
		holder.imageView.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(final View v) {
	               // Toast.makeText(getContext(), "Button Clicked Position " +
	               //         position, Toast.LENGTH_SHORT).show();
	            	 Intent intent=new Intent(mContext,CreditScrollMainActivity.class);   
	                 Bundle bundle=new Bundle();  
	                 bundle.putInt("rawid",rawids[0]);  
	                 intent.putExtras(bundle);  
	                 //Ҳ���������ַ�ʽ����.  
	                 //intent.putExtra("result", "��һ��activity������");  
	                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	                 mContext.startActivity(intent);  
	            }
	        }); 
		return convertView;
	}

	public void refresh(){
		
	}
	
	static class ViewHolder {
		ScaleImageView imageView;
	}
}
