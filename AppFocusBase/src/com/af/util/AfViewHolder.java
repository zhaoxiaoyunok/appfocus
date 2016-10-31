package com.af.util;

import android.util.SparseArray;
import android.view.View;

/**
 * © 2015 lyyj.com
 * 名称：AfViewHolder.java 
 * 描述：超简洁的ViewHolder.
 * 代码更简单，性能略低，可以忽略
 * @author kaka
 * @version v1.0
 * 
 */
public class AfViewHolder {
    
    /**
     * ImageView view = AfViewHolder.get(convertView, R.id.imageView);
     * @param view
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
