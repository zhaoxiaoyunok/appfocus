package com.lyyj.activity.demo.edittext.validator.sample.utils;

import android.content.Context;

import com.lyyj.activity.demo.edittext.validator.sample.LayoutExampleActivity;

public class LayoutListItem extends ListItem {
	private int layoutRes;
	private int explanationString;

	public LayoutListItem(String _listString, int _layoutRes, int _explanationStringRes) {
		super(_listString);
		layoutRes = _layoutRes;
		explanationString = _explanationStringRes;
	}

	public void goToDemo(Context ctx) {
		ctx.startActivity(LayoutExampleActivity.buildIntent(ctx, getListTitle(), layoutRes, explanationString));
	}

}
