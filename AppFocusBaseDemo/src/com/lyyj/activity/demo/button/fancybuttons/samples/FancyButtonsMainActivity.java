package com.lyyj.activity.demo.button.fancybuttons.samples;

import com.appfocusbase.R;
import com.lyyj.activity.demo.button.fancybuttons.FancyButton;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
 

public class FancyButtonsMainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    String[] listItems = {"XML buttons", "Programmatically Buttons",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_btn_fancybuttons_activity_main);

        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));
        getListView().setOnItemClickListener(this);


    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        switch(position){
            case 0 :
                Intent intentXML = new Intent(FancyButtonsMainActivity.this,XmlButtons.class);
                startActivity(intentXML);

                break;
            case 1 :
                Intent intentProg = new Intent(FancyButtonsMainActivity.this,ProgramButtons.class);
                startActivity(intentProg);
                break;
            default: throw new IllegalArgumentException("Hold up, hold my phone :)");
        }
    }
}
