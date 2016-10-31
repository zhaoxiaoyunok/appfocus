package com.lyyj.activity.demo.menu.filtermenu;

import com.appfocusbase.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class FilterMenuMainActivity extends  Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_menu_filtermenu_activity_main);
        FilterMenuLayout layout1 = (FilterMenuLayout) findViewById(R.id.filter_menu1);
        attachMenu1(layout1);

        FilterMenuLayout layout2 = (FilterMenuLayout) findViewById(R.id.filter_menu2);
        attachMenu2(layout2);

        FilterMenuLayout layout3 = (FilterMenuLayout) findViewById(R.id.filter_menu3);
        attachMenu3(layout3);

        FilterMenuLayout layout4 = (FilterMenuLayout) findViewById(R.id.filter_menu4);
        attachMenu4(layout4);
    }
    private FilterMenu attachMenu1(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_add)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_clock)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_info)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_io)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_location_2)
                .attach(layout)
                .withListener(new FilterMenu.OnMenuChangeListener() {
                    @Override
                    public void onMenuItemClick(View view, int position) {
                    }

                    @Override
                    public void onMenuCollapse() {

                    }

                    @Override
                    public void onMenuExpand() {

                    }
                })
                .build();
    }
    private FilterMenu attachMenu2(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_add)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_clock)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_info)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_location_2)
                .attach(layout)
                .build();
    }
    private FilterMenu attachMenu3(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_add)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_clock)
                .addItem(R.drawable.lyyj_menu_filtermenu_ic_action_location_2)
                .attach(layout)
                .build();
    }
    private FilterMenu attachMenu4(FilterMenuLayout layout){
        return new FilterMenu.Builder(this)
                .inflate(R.menu.lyyj_menu_filtermenu_menu_filter)
                .attach(layout)
                .build();
    }

}