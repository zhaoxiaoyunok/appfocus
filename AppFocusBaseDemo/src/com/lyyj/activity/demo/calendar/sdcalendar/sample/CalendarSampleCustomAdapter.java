package com.lyyj.activity.demo.calendar.sdcalendar.sample;

import hirondelle.date4j.DateTime;

import java.util.HashMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appfocusbase.R;
import com.lyyj.activity.demo.calendar.sdcalendar.adapter.MonthCalendarGridAdapter;
import com.lyyj.activity.demo.calendar.sdcalendar.fragment.BaseCalendarFragment;

public class CalendarSampleCustomAdapter extends MonthCalendarGridAdapter {

	public CalendarSampleCustomAdapter(Context context, int month, int year, HashMap<String, Object> caldroidData, HashMap<String, Object> extraData) {
		super(context, month, year, caldroidData, extraData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View cellView = convertView;

		// For reuse
		if (convertView == null) {
			cellView = inflater.inflate(R.layout.lyyj_calendar_sdcalendar_custom_cell, null);
		}

		int topPadding = cellView.getPaddingTop();
		int leftPadding = cellView.getPaddingLeft();
		int bottomPadding = cellView.getPaddingBottom();
		int rightPadding = cellView.getPaddingRight();

		TextView tv1 = (TextView) cellView.findViewById(R.id.tv1);
		TextView tv2 = (TextView) cellView.findViewById(R.id.tv2);

		tv1.setTextColor(Color.BLACK);

		// Get dateTime of this cell
		DateTime dateTime = this.datetimeList.get(position);
		Resources resources = context.getResources();

		// Set color of the dates in previous / next month
		if (dateTime.getMonth() != month) {
			tv1.setTextColor(resources.getColor(R.color.darker_gray));
		}

		boolean shouldResetDiabledView = false;
		boolean shouldResetSelectedView = false;

		// Customize for disabled dates and date outside min/max dates
		if ((minDateTime != null && dateTime.lt(minDateTime)) || (maxDateTime != null && dateTime.gt(maxDateTime)) || (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

			tv1.setTextColor(BaseCalendarFragment.disabledTextColor);
			if (BaseCalendarFragment.disabledBackgroundDrawable == -1) {
				cellView.setBackgroundResource(R.drawable.lyyj_calendar_sdcalendar_cell_disable);
			} else {
				cellView.setBackgroundResource(BaseCalendarFragment.disabledBackgroundDrawable);
			}

			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(R.drawable.lyyj_calendar_sdcalendar_cell_today);
			}

		} else {
			shouldResetDiabledView = true;
		}

		// Customize for selected dates
		if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
			if (BaseCalendarFragment.selectedBackgroundDrawable != -1) {
				cellView.setBackgroundResource(BaseCalendarFragment.selectedBackgroundDrawable);
			} else {
				cellView.setBackgroundColor(resources.getColor(R.color.sky_blue));
			}

			tv1.setTextColor(BaseCalendarFragment.selectedTextColor);

		} else {
			shouldResetSelectedView = true;
		}

		if (shouldResetDiabledView && shouldResetSelectedView) {
			// Customize for today
			if (dateTime.equals(getToday())) {
				cellView.setBackgroundResource(R.drawable.lyyj_calendar_sdcalendar_cell_today);
			} else {
				cellView.setBackgroundResource(R.drawable.lyyj_calendar_sdcalendar_cell_default);
			}
		}

		tv1.setText("" + dateTime.getDay());
		tv2.setText("Hi");

		// Somehow after setBackgroundResource, the padding collapse.
		// This is to recover the padding
		cellView.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);

		// Set custom color if required
		setCustomResources(dateTime, cellView, tv1);

		return cellView;
	}

}
