package com.lyyj.activity.demo.calendar.scrollercalendar.demo;

import com.appfocusbase.R;
import com.lyyj.activity.demo.calendar.scrollercalendar.ScrollerCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class iosScrollerCalendarMainActivity extends Activity  implements
com.lyyj.activity.demo.calendar.scrollercalendar.ScrollerCalendarController{
	
	private ScrollerCalendar monthPickerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_calendar_iosscrollercalendar_activity_main);
		
		monthPickerView = (ScrollerCalendar) findViewById(R.id.pickerView);
		monthPickerView.setController(this);
		
	}

	@Override
	public void onMonthOfYearSelected(int year, int month) {
		Toast.makeText(getApplicationContext(), year+"-"+month, Toast.LENGTH_SHORT).show();
		
	}



}
