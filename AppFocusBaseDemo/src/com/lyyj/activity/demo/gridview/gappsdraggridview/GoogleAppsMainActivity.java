package com.lyyj.activity.demo.gridview.gappsdraggridview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.appfocusbase.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * @blog http://blog.csdn.net/xiaanming 
 * 
 * @author xiaanming
 *
 */
public class GoogleAppsMainActivity extends Activity {
	private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
	  List<String> showPNs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lyyj_gridview_gapps_activity_main);
		  showPNs = Arrays.asList(Cheeses.sCheeseStrings);
	        PackageManager packageManager = getPackageManager();
	        Intent mainIntent = new Intent("android.intent.action.MAIN");
	        mainIntent.addCategory("android.intent.category.LAUNCHER");
	        List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0x0);
	        if(apps == null) {
	            return;
	        }
	        ArrayList<ResolveInfo> showApps = new ArrayList<ResolveInfo>();
	        if(apps.size() != 0) {
	            int size = apps.size();
	            for(int i = 0x0; i < size; i = i + 0x1) {
	                ResolveInfo resolveInfo = (ResolveInfo)apps.get(i);
	                String pkg = resolveInfo.activityInfo.packageName;
	                
	                if(showPNs.contains(pkg)) {
	                    showApps.add(resolveInfo);
	                }
	            }       
	        }
		DragGridView mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
		for (int i = 0; i <showApps.size(); i++) {
			HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
			itemHashMap.put("item_resolve_info",showApps.get(i));			
			dataSourceList.add(itemHashMap);
		}
		
		final DragAdapter mDragAdapter = new DragAdapter(this, dataSourceList);
		
		mDragGridView.setAdapter(mDragAdapter);
		mDragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(GridActivity.this, parent.getAdapter().getItem(position).toString(),
                //        Toast.LENGTH_SHORT).show();
            	HashMap<String, Object> map =(HashMap<String, Object>) parent.getAdapter().getItem(position);
                ResolveInfo info = (ResolveInfo)map.get("item_resolve_info");
                String pkg = info.activityInfo.packageName;
                String cls = info.activityInfo.name;
                ComponentName componet = new ComponentName(pkg, cls);
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setComponent(componet);
                startActivity(intent); 
                finish();
            }
        });
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

}
