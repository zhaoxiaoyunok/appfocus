/*
 * Copyright (C) 2013 47 Degrees, LLC
 *  http://47deg.com
 *  hello@47deg.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.lyyj.activity.demo.listview.swipelistview.sample.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.lyyj.activity.demo.listview.swipelistview.BaseSwipeListViewListener;
import com.lyyj.activity.demo.listview.swipelistview.SwipeListView;
import com.lyyj.activity.demo.listview.swipelistview.sample.adapters.PackageAdapter;
import com.lyyj.activity.demo.listview.swipelistview.sample.adapters.PackageItem;
import com.lyyj.activity.demo.listview.swipelistview.sample.dialogs.AboutDialog;
import com.lyyj.activity.demo.listview.swipelistview.sample.utils.PreferencesManager;
import com.lyyj.activity.demo.listview.swipelistview.sample.utils.SettingsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwipeListViewExampleActivity extends AfActivity  {

    private static final int REQUEST_CODE_SETTINGS = 0;
    private PackageAdapter adapter;
    private List<PackageItem> data;

    private SwipeListView swipeListView;

    private ProgressDialog progressDialog;

    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setAfContentView(R.layout.lyyj_lv_swipelv_swipe_list_view_activity);
        AfTitleBar mAfTitleBar = this.getTitleBar();
		mAfTitleBar.setTitleText(R.string.demo_name);
		mAfTitleBar.setLogo(R.drawable.button_selector_back);
		mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAfTitleBar.setLogoLine(R.drawable.line);
		// mAfTitleBar.setVisibility(View.GONE);
		// 设置AfTitleBar在最上
		this.setTitleBarOverlay(false);
		mAfTitleBar.clearRightView();
		View rightViewMore = mInflater.inflate(R.layout.more_btn, null);
		mAfTitleBar.addRightView(rightViewMore);
		Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
		about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTINGS); 
			}

		});
        data = new ArrayList<PackageItem>();

        adapter = new PackageAdapter(this, data);

        swipeListView = (SwipeListView) findViewById(R.id.example_lv_list);

        swipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            swipeListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//
//                @SuppressLint("NewApi")
//				@Override
//                public void onItemCheckedStateChanged(ActionMode mode, int position,
//                                                      long id, boolean checked) {
//                    mode.setTitle("Selected (" + swipeListView.getCountSelected() + ")");
//                }
//
//                @SuppressLint("NewApi")
//				@Override
//                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                    switch (item.getItemId()) {
//                        case R.id.menu_delete:
//                            swipeListView.dismissSelected();
//                            mode.finish();
//                            return true;
//                        default:
//                            return false;
//                    }
//                }
//
//                @SuppressLint("NewApi")
//				@Override
//                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                    MenuInflater inflater = mode.getMenuInflater();
//                    inflater.inflate(R.menu.lyyj_lv_swipelv_menu_choice_items, menu);
//                    return true;
//                }
//
//                @Override
//                public void onDestroyActionMode(ActionMode mode) {
//                    swipeListView.unselectedChoiceStates();
//                }
//
//                @Override
//                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                    return false;
//                }
//            });
//        }

        swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }

            @Override
            public void onListChanged() {
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d("swipe", String.format("onStartClose %d", position));
            }

            @Override
            public void onClickFrontView(int position) {
                Log.d("swipe", String.format("onClickFrontView %d", position));
            }

            @Override
            public void onClickBackView(int position) {
                Log.d("swipe", String.format("onClickBackView %d", position));
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    data.remove(position);
                }
                adapter.notifyDataSetChanged();
            }

        });

        swipeListView.setAdapter(adapter);

        reload();

        new ListAppTask().execute();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    private void reload() {
        SettingsManager settings = SettingsManager.getInstance();
        swipeListView.setSwipeMode(settings.getSwipeMode());
        swipeListView.setSwipeActionLeft(settings.getSwipeActionLeft());
        swipeListView.setSwipeActionRight(settings.getSwipeActionRight());
        swipeListView.setOffsetLeft(convertDpToPixel(settings.getSwipeOffsetLeft()));
        swipeListView.setOffsetRight(convertDpToPixel(settings.getSwipeOffsetRight()));
        swipeListView.setAnimationTime(settings.getSwipeAnimationTime());
        swipeListView.setSwipeOpenOnLongPress(settings.isSwipeOpenOnLongPress());
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lyyj_lv_swipelv_menu_app, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case android.R.id.home: //Actionbar home/up icon
                finish();
                break;
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTINGS);
                break;
        }
        return handled;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SETTINGS:
                reload();
        }
    }

    public class ListAppTask extends AsyncTask<Void, Void, List<PackageItem>> {

        protected List<PackageItem> doInBackground(Void... args) {
            PackageManager appInfo = getPackageManager();
            List<ApplicationInfo> listInfo = appInfo.getInstalledApplications(0);
            Collections.sort(listInfo, new ApplicationInfo.DisplayNameComparator(appInfo));

            List<PackageItem> data = new ArrayList<PackageItem>();

            for (int index = 0; index < listInfo.size(); index++) {
                try {
                    ApplicationInfo content = listInfo.get(index);
                    if ((content.flags != ApplicationInfo.FLAG_SYSTEM) && content.enabled) {
                        if (content.icon != 0) {
                            PackageItem item = new PackageItem();
                            item.setName(getPackageManager().getApplicationLabel(content).toString());
                            item.setPackageName(content.packageName);
                            item.setIcon(getPackageManager().getDrawable(content.packageName, content.icon, content));
                            data.add(item);
                        }
                    }
                } catch (Exception e) {

                }
            }

            return data;
        }

        protected void onPostExecute(List<PackageItem> result) {
            data.clear();
            data.addAll(result);
            adapter.notifyDataSetChanged();
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
            if (PreferencesManager.getInstance(SwipeListViewExampleActivity.this).getShowAbout()) {
                AboutDialog logOutDialog = new AboutDialog();
                logOutDialog.show(getSupportFragmentManager(), "dialog");
            }
        }
    }

}
