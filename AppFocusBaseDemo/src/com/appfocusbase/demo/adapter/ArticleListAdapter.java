package com.appfocusbase.demo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appfocusbase.R;
import com.appfocusbase.demo.model.Article;
import com.koushikdutta.ion.Ion;

/**
 * © 2015 lyyj.com 
 * 名称：ArticleListAdapter 
 * 描述：文章对象自定义Adapter例子
 * @author kaka
 * @date 2011-11-8
 * @version
 */
public class ArticleListAdapter extends BaseAdapter{
  
	private Context mContext;
    //列表展现的数据
    private List<Article> mList;

    
   /**
    * 构造方法
    * @param context
    * @param list 列表展现的数据
    */
    public ArticleListAdapter(Context context, List<Article> list){
    	 mContext = context;
    	 mList = list;

    }   
    
    @Override
    public int getCount() {
    	if(mList==null){
    		return 0;
    	}
        return mList.size();
    }
    
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position){
      return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
    	  final ViewHolder holder;
          if(convertView == null){
	          //使用自定义的list_items作为Layout
	          convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
	          //使用减少findView的次数
			  holder = new ViewHolder();
			  holder.itemsIcon = ((ImageView) convertView.findViewById(R.id.itemsIcon)) ;
			  holder.itemsTitle = ((TextView) convertView.findViewById(R.id.itemsTitle));
			  holder.itemsText = ((TextView) convertView.findViewById(R.id.itemsText));
			  //设置标记
			  convertView.setTag(holder);
          }else{
        	  holder = (ViewHolder) convertView.getTag();
          }
          
          //获取该行数据
          Article mArticle = (Article)mList.get(position);
          
          //设置数据到View
          String imageUrl = (String)mArticle.getImageUrl();
          //设置加载中的View
          View loadingView = convertView.findViewById(R.id.progressBar);
          //图片的下载
          //mAbImageLoader.display(holder.itemsIcon,loadingView,imageUrl,500,500);
          Ion.with(holder.itemsIcon).placeholder(R.drawable.progress_loading2).resize(500,500).load(imageUrl);
          holder.itemsTitle.setText(mArticle.getTitle());
          holder.itemsText.setText(mArticle.getCreateTime());
          return convertView;
    }
    
    /**
	 * ViewHolder类
	 */
	static class ViewHolder {
		ImageView itemsIcon;
		TextView itemsTitle;
		TextView itemsText;
	}
}
