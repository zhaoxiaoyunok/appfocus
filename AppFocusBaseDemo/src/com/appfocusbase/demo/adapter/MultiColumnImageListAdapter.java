package com.appfocusbase.demo.adapter;




import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.af.view.pullview.AfMultiColumnListAdapter;
import com.af.view.pullview.AfViewInfo;
import com.appfocusbase.R;
import com.appfocusbase.demo.model.ImageInfo;
import com.koushikdutta.ion.Ion;
/**
 * © 2015 lyyj.com
 * 名称：MyListViewAdapter
 * 描述：在Adapter中释放Bitmap
 * @author kaka
 * @date 2011-12-10
 * @version
 */
public class MultiColumnImageListAdapter extends AfMultiColumnListAdapter{
	
	private Context mContext;
    //列表展现的数据
    private List<ImageInfo> mImageList;

    
    
   /**
    * 构造方法
    * @param context
    * @param data 列表展现的数据
    * @param resource 单行的布局
    * @param from Map中的key
    * @param to view的id
    */
    public MultiColumnImageListAdapter(Context context, List<ImageInfo> imageList){
    	this.mContext = context;
    	this.mImageList = imageList;

    }   
    
    @Override
    public int getCount() {
        return mImageList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position){
      return position;
    }
    
    @Override
    public AfViewInfo getView(int position, AfViewInfo convertView, ViewGroup parent){
    	  final ViewHolder holder;
          if(convertView == null){
        	   //减少findView的次数
			   holder = new ViewHolder();
	           //使用自定义的list_items作为Layout
	           View view = LayoutInflater.from(mContext).inflate(R.layout.multi_list_items, parent);
	           convertView  = new AfViewInfo(view);
	           //初始化布局中的元素
			   holder.itemsIcon = (ImageView) view.findViewById(R.id.itemsIcon);
			   convertView.setTag(holder);
          }else{
        	   holder = (ViewHolder) convertView.getTag();
          }
          
          //获取该行的数据
          ImageInfo  mImageInfo = mImageList.get(position);
		  String url = mImageInfo.getUrl();
		  //设置加载中的View
          View loadingView = convertView.getView().findViewById(R.id.progressBar);
         // mAbImageLoader.display(holder.itemsIcon,loadingView,url,mImageInfo.getWidth(),mImageInfo.getHeight());
          Ion.with(holder.itemsIcon).placeholder(R.drawable.progress_loading2).resize(mImageInfo.getWidth(),mImageInfo.getHeight()).load(url);
          convertView.setWidth(mImageInfo.getWidth());
          convertView.setHeight(mImageInfo.getHeight());
          return convertView;
    }
    
    
    /**
	 * View元素
	 */
	static class ViewHolder {
		ImageView itemsIcon;
	}
    
}
