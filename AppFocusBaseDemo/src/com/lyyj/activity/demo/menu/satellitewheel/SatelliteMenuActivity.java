package com.lyyj.activity.demo.menu.satellitewheel;

import java.util.ArrayList;
import java.util.List;







import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenu.SateliteClickedListener;
import android.view.ext.SatelliteMenuItem;

public class SatelliteMenuActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_menu_satellite_wheel_activity_satellitemenu_layout);
        
        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
        
//		  Set from XML, possible to programmatically set        
        float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
        menu.setSatelliteDistance((int) distance);
        menu.setExpandDuration(500);
        menu.setCloseItemsOnClick(false);
        menu.setTotalSpacingDegree(90);

        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(4, R.drawable.lyyj_menu_satellite_wheel_ic_1));
        items.add(new SatelliteMenuItem(4, R.drawable.lyyj_menu_satellite_wheel_ic_3));
        items.add(new SatelliteMenuItem(4, R.drawable.lyyj_menu_satellite_wheel_ic_4));
        items.add(new SatelliteMenuItem(3, R.drawable.lyyj_menu_satellite_wheel_ic_5));
        items.add(new SatelliteMenuItem(2, R.drawable.lyyj_menu_satellite_wheel_ic_6));
        items.add(new SatelliteMenuItem(1, R.drawable.lyyj_menu_satellite_wheel_ic_2));
//        items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
        menu.addItems(items);        
        
        menu.setOnItemClickedListener(new SateliteClickedListener() {
			
			public void eventOccured(int id) {
				Log.i("sat", "Clicked on " + id);
			}
		});
    }
}