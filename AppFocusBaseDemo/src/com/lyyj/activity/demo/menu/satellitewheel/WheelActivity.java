package com.lyyj.activity.demo.menu.satellitewheel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.appfocusbase.R;
import com.lyyj.activity.demo.menu.wheelmenu.WheelMenu;

public class WheelActivity extends Activity {
    private WheelMenu wheelMenu;
    private TextView selectedPositionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_menu_satellite_wheel_activity_wheel_layout);

        wheelMenu = (WheelMenu) findViewById(R.id.wheelMenu);

        wheelMenu.setDivCount(12);
        wheelMenu.setWheelImage(R.drawable.lyyj_menu_satellite_wheel_wheel);

        selectedPositionText = (TextView) findViewById(R.id.selected_position_text);
        selectedPositionText.setText("selected: " + (wheelMenu.getSelectedPosition() + 1));

        wheelMenu.setWheelChangeListener(new WheelMenu.WheelChangeListener() {
            @Override
            public void onSelectionChange(int selectedPosition) {
                selectedPositionText.setText("selected: " + (selectedPosition + 1));
            }
        });
    }
}
