package com.lyyj.activity.demo.calendar.sdcalendar.sample;

import hirondelle.date4j.DateTime;

import com.lyyj.activity.demo.calendar.sdcalendar.adapter.BaseCalendarGridAdapter;
import com.lyyj.activity.demo.calendar.sdcalendar.fragment.MonthFragment;

public class CalendarSampleCustomFragment extends MonthFragment {

	@Override
	public BaseCalendarGridAdapter getNewDatesGridAdapter(DateTime datetime) {
		return new CalendarSampleCustomAdapter(getActivity(), month, year,
				getCaldroidData(), extraData);	}
}
