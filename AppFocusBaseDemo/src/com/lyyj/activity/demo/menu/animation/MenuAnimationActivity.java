package com.lyyj.activity.demo.menu.animation;

import com.appfocusbase.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MenuAnimationActivity extends FragmentActivity {


    private int[] mDrawables = new int[]{
            R.drawable.lyyj_menu_anim_ic_menu_1,
            R.drawable.lyyj_menu_anim_ic_menu_2,
            R.drawable.lyyj_menu_anim_ic_menu_3,
            R.drawable.lyyj_menu_anim_ic_menu_4,
            R.drawable.lyyj_menu_anim_ic_menu_5,
            R.drawable.lyyj_menu_anim_ic_menu_6};

    private String[] mTitles = new String[]{
            "AB","AC","AD","AE","AF","AG"};


    private MenuView mMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_menu_anim_activity_main);
        mMenuView = (MenuView) findViewById(R.id.menu_view);

        mMenuView.setMenuResource(mDrawables,mTitles);
        mMenuView.setOnMenuClickListener(new MenuView.onMenuClickListener() {
            @Override
            public void onMenuClick(int position) {
                Toast.makeText(MenuAnimationActivity.this,""+(position+1),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startAnim(View view){
        Log.e("stat:","start");
        mMenuView.toggleMenu();
    }
}
