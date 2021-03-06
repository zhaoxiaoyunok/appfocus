package com.lyyj.activity.demo.calendar.sdcalendar.sample;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appfocusbase.R;
import com.lyyj.activity.demo.calendar.sdcalendar.calendar.OnCalendarChangeListener;
import com.lyyj.activity.demo.calendar.sdcalendar.fragment.BaseCalendarFragment;
import com.lyyj.activity.demo.calendar.sdcalendar.fragment.MonthFragment;

@SuppressLint("SimpleDateFormat")
public class CalendarMonthActivity extends FragmentActivity {
	private boolean undo = false;
	private BaseCalendarFragment caldroidFragment;
	private BaseCalendarFragment dialogCaldroidFragment;

	private void setCustomResourceForDates() {
		Calendar cal = Calendar.getInstance();

		// Min date is last 7 days
		cal.add(Calendar.DATE, -18);
		Date blueDate = cal.getTime();

		// Max date is next 7 days
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 16);
		Date greenDate = cal.getTime();

		if (caldroidFragment != null) {
			caldroidFragment.setBackgroundResourceForDate(R.color.light_blue,
					blueDate);
			caldroidFragment.setBackgroundResourceForDate(R.color.green,
					greenDate);
			caldroidFragment.setTextColorForDate(R.color.white, blueDate);
			caldroidFragment.setTextColorForDate(R.color.white, greenDate);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_calendar_sdcalendar_activity_main);

		final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

		// Setup caldroid fragment
		// **** If you want normal CalendarFragment, use below line ****
		caldroidFragment = new MonthFragment();

		// //////////////////////////////////////////////////////////////////////
		// **** This is to show customized fragment. If you want customized
		// version, uncomment below line ****
//		 caldroidFragment = new CaldroidSampleCustomFragment();

		// Setup arguments

		// If Activity is created after rotation
		if (savedInstanceState != null) {
			caldroidFragment.restoreStatesFromKey(savedInstanceState,
					"CALDROID_SAVED_STATE");
		}
		// If activity is created from fresh
		else {
			Bundle args = new Bundle();
			Calendar cal = Calendar.getInstance();
			args.putInt(BaseCalendarFragment.MONTH, cal.get(Calendar.MONTH) + 1);
			args.putInt(BaseCalendarFragment.YEAR, cal.get(Calendar.YEAR));
			args.putBoolean(BaseCalendarFragment.ENABLE_SWIPE, true);
			args.putBoolean(MonthFragment.SIX_WEEKS_IN_CALENDAR, false);

			// Uncomment this to customize startDayOfWeek
			// args.putInt(CalendarFragment.START_DAY_OF_WEEK,
			// CalendarFragment.TUESDAY); // Tuesday
			caldroidFragment.setArguments(args);
		}

		setCustomResourceForDates();

		// Attach to the activity
		FragmentTransaction t = getSupportFragmentManager().beginTransaction();
		t.replace(R.id.calendar1, caldroidFragment);
		t.commit();

		// Setup listener
		final OnCalendarChangeListener listener = new OnCalendarChangeListener() {

			@Override
			public void onSelectDate(Date date, View view) {
				Toast.makeText(getApplicationContext(), formatter.format(date),
						Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onChangeDateTime(int year, int month, int day) {
				String text = "month: " + month + " year: " + year + " day: " + day;
				Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onLongClickDate(Date date, View view) {
				Toast.makeText(getApplicationContext(),
						"Long click " + formatter.format(date),
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onCaldroidViewCreated() {
				if (caldroidFragment.getLeftArrowButton() != null) {
					Toast.makeText(getApplicationContext(),
							"Caldroid view is created", Toast.LENGTH_SHORT)
							.show();
				}
			}

		};

		// Setup Caldroid
		caldroidFragment.setCaldroidListener(listener);

		final TextView textView = (TextView) findViewById(R.id.textview);
//
//		final Button customizeButton = (Button) findViewById(R.id.customize_button);
//
//		// Customize the calendar
//		customizeButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (undo) {
//					customizeButton.setText(getString(R.string.customize));
//					textView.setText("");
//
//					// Reset calendar
//					caldroidFragment.clearDisableDates();
//					caldroidFragment.clearSelectedDates();
//					caldroidFragment.setMinDate(null);
//					caldroidFragment.setMaxDate(null);
//					caldroidFragment.setShowNavigationArrows(true);
//					caldroidFragment.setEnableSwipe(true);
//					caldroidFragment.refreshView();
//					undo = false;
//					return;
//				}
//
//				// Else
//				undo = true;
//				customizeButton.setText(getString(R.string.undo));
//				Calendar cal = Calendar.getInstance();
//
//				// Min date is last 7 days
//				cal.add(Calendar.DATE, -7);
//				Date minDate = cal.getTime();
//
//				// Max date is next 7 days
//				cal = Calendar.getInstance();
//				cal.add(Calendar.DATE, 14);
//				Date maxDate = cal.getTime();
//
//				// Set selected dates
//				// From Date
//				cal = Calendar.getInstance();
//				cal.add(Calendar.DATE, 2);
//				Date fromDate = cal.getTime();
//
//				// To Date
//				cal = Calendar.getInstance();
//				cal.add(Calendar.DATE, 3);
//				Date toDate = cal.getTime();
//
//				// Set disabled dates
//				ArrayList<Date> disabledDates = new ArrayList<Date>();
//				for (int i = 5; i < 8; i++) {
//					cal = Calendar.getInstance();
//					cal.add(Calendar.DATE, i);
//					disabledDates.add(cal.getTime());
//				}
//
//				// Customize
//				caldroidFragment.setMinDate(minDate);
//				caldroidFragment.setMaxDate(maxDate);
//				caldroidFragment.setDisableDates(disabledDates);
//				caldroidFragment.setSelectedDates(fromDate, toDate);
//				caldroidFragment.setShowNavigationArrows(false);
//				caldroidFragment.setEnableSwipe(false);
//
//				caldroidFragment.refreshView();
//
//				// Move to date
//				// cal = Calendar.getInstance();
//				// cal.add(Calendar.MONTH, 12);
//				// caldroidFragment.moveToDate(cal.getTime());
//
//				String text = "Today: " + formatter.format(new Date()) + "\n";
//				text += "Min Date: " + formatter.format(minDate) + "\n";
//				text += "Max Date: " + formatter.format(maxDate) + "\n";
//				text += "Select From Date: " + formatter.format(fromDate)
//						+ "\n";
//				text += "Select To Date: " + formatter.format(toDate) + "\n";
//				for (Date date : disabledDates) {
//					text += "Disabled Date: " + formatter.format(date) + "\n";
//				}
//
//				textView.setText(text); 
//			}
//		});
//
//		Button showDialogButton = (Button) findViewById(R.id.show_dialog_button);
//
//		final Bundle state = savedInstanceState;
//		showDialogButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// Setup caldroid to use as dialog
//				dialogCaldroidFragment = new MonthFragment();
//				dialogCaldroidFragment.setCaldroidListener(listener);
//
//				// If activity is recovered from rotation
//				final String dialogTag = "CALDROID_DIALOG_FRAGMENT";
//				if (state != null) {
//					dialogCaldroidFragment.restoreDialogStatesFromKey(
//							getSupportFragmentManager(), state,
//							"DIALOG_CALDROID_SAVED_STATE", dialogTag);
//					Bundle args = dialogCaldroidFragment.getArguments();
//					if (args == null) {
//						args = new Bundle();
//						dialogCaldroidFragment.setArguments(args);
//					}
//					args.putString(BaseCalendarFragment.DIALOG_TITLE,
//							"Select a date");
//				} else {
//					// Setup arguments
//					Bundle bundle = new Bundle();
//					// Setup dialogTitle
//					bundle.putString(BaseCalendarFragment.DIALOG_TITLE,
//							"Select a date");
//					dialogCaldroidFragment.setArguments(bundle);
//				}
//
//				dialogCaldroidFragment.show(getSupportFragmentManager(),
//						dialogTag);
//			}
//		});
	}

	/**
	 * Save current states of the Caldroid here
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

		if (caldroidFragment != null) {
			caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
		}

		if (dialogCaldroidFragment != null) {
			dialogCaldroidFragment.saveStatesToKey(outState,
					"DIALOG_CALDROID_SAVED_STATE");
		}
	}

}
