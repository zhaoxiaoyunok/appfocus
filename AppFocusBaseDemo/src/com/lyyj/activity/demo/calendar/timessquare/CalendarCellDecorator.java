package com.lyyj.activity.demo.calendar.timessquare;

import java.util.Date;

public interface CalendarCellDecorator {
  void decorate(CalendarCellView cellView, Date date);
}
