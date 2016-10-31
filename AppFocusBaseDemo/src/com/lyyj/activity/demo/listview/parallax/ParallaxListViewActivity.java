package com.lyyj.activity.demo.listview.parallax;


import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ParallaxListViewActivity extends Activity {

    private ParallaxScollListView mListView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_lv_parallax_activity_parallax);

        mListView = (ParallaxScollListView) findViewById(R.id.layout_listview);
        View header = LayoutInflater.from(this).inflate(R.layout.lyyj_lv_parallax_listview_header, null);
        mImageView = (ImageView) header.findViewById(R.id.layout_header_image);

        mListView.setParallaxImageView(mImageView);
        mListView.addHeaderView(header);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{
                        "First Item",
                        "Second Item",
                        "Third Item",
                        "Fifth Item",
                        "Sixth Item",
                        "Seventh Item",
                        "Eighth Item",
                        "Ninth Item",
                        "Tenth Item",
                        "....."
                }
        );
        mListView.setAdapter(adapter);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            mListView.setViewsBounds(ParallaxScollListView.ZOOM_X2);
        }
    }

}
