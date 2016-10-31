package com.lyyj.activity.demo.textview.myannouncementview;

import java.util.ArrayList;
import java.util.List;
 


import com.appfocusbase.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AnnouncementViewMainActivity extends Activity {

	private MyVerticalTextView mSampleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_tv_myannouncementview_activity_main);
        mSampleView = (MyVerticalTextView) findViewById(R.id.sampleView1);
		List lst=new ArrayList<Sentence>();
		for(int i=0;i<30;i++){
			if(i%2==0){
				Sentence sen=new Sentence(i,i+"、金球奖三甲揭晓 C罗梅西哈维入围 ");
				lst.add(i, sen);
			}else{
				Sentence sen=new Sentence(i,i+"、公牛欲用三大主力换魔兽？？？？");
				lst.add(i, sen);
			}
		}	
		//给View传递数据
		mSampleView.setList(lst);
		//更新View
		mSampleView.updateUI();		
    }
 
}
