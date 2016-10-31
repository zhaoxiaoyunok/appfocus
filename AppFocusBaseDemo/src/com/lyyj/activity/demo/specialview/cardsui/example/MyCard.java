package com.lyyj.activity.demo.specialview.cardsui.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.appfocusbase.R;
import com.lyyj.activity.demo.specialview.cardsui.objects.Card;

public class MyCard extends Card {

	public MyCard(String title){
		super(title);
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.lyyj_specialview_cardsui_card_ex, null);

		((TextView) view.findViewById(R.id.title)).setText(title);

		
		return view;
	}

	
	
	
}
