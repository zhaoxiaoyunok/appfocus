package com.lyyj.activity.demo.guide.xhsreglogin.fragment.base;

import com.appfocusbase.R;
import com.lyyj.activity.demo.guide.xhsreglogin.bean.TextBean;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 


/**
 * 下层文字滚动fragment基类
 */
public class LoginAnimTextBaseFragment extends Fragment {

    TextView tv_title;
    TextView tv_content;

    int mId ;
    String mTitle;
    String mContent;

    public LoginAnimTextBaseFragment(TextBean tb){
        if(tb != null){
            mId = tb.mId;
            mTitle = tb.mTitle;
            mContent = tb.mContent;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.lyyj_guide_xhsreglogin_fragment_welcomanim_text, null);
        tv_title = (TextView)view.findViewById(R.id.tv_title);
        tv_content = (TextView)view.findViewById(R.id.tv_content);
        tv_title.setText(mTitle);
        tv_content.setText(mContent);
        return view;
    }
}
