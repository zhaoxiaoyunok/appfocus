package com.lyyj.activity.demo.edittext.validator.sample.utils;

import android.content.Context;

public abstract class ListItem {
	private String listString;

	public ListItem(String _listString) {
		listString = _listString;
	}
	public String getListTitle() {
	  return listString;
	}
	public abstract void goToDemo(Context ctx);
}
