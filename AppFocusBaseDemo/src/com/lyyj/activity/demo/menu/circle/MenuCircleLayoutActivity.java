package com.lyyj.activity.demo.menu.circle;

import com.appfocusbase.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;


public class MenuCircleLayoutActivity extends ActionBarActivity {
    //菜单按钮的资源图片

    private int[] imgs = new int[]{
            R.drawable.lyyj_menu_circle_ic_item_1,
            R.drawable.lyyj_menu_circle_ic_item_2,
            R.drawable.lyyj_menu_circle_ic_item_3,
            R.drawable.lyyj_menu_circle_ic_item_4,
            R.drawable.lyyj_menu_circle_ic_item_5,
            R.drawable.lyyj_menu_circle_ic_item_6};

    //菜单按钮对应的文案
    private int[] titles = new int[]{
            R.string.title_one,
            R.string.title_two,
            R.string.title_three,
            R.string.title_four,
            R.string.title_five,
            R.string.title_six
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_menu_circle_activity_main);
        CircleMenuLayout circleMenu = (CircleMenuLayout) findViewById(R.id.circle_menu);
        circleMenu.setMenuResource(imgs,titles);
        circleMenu.setOnMenuClickListener(new CircleMenuLayout.OnMenuClickListener() {
            @Override
            public void onMenuClick(int position) {
               Toast.makeText(getApplicationContext(),"position:"+position,Toast.LENGTH_LONG).show();
            }
        });
    }

}
