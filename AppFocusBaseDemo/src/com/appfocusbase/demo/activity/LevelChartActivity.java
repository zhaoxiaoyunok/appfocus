package com.appfocusbase.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.af.activity.AfActivity;
import com.af.view.level.AfLevelChartFactory;
import com.af.view.level.AfLevelSeriesDataset;
import com.af.view.level.AfLevelSeriesRenderer;
import com.af.view.level.AfLevelView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;

public class LevelChartActivity extends AfActivity {
	private MyApplication application;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.level_view);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.level_name);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
        application = (MyApplication)mAfApplication;
        
        //等级图形宽
        int width = 300;
        //等级图形高
        int height = 200;
        //各等级段的颜色
    	int [] color = new int[]{Color.rgb(71, 190, 222),Color.rgb(153, 234, 71),Color.rgb(153, 234, 71),Color.rgb(249, 135, 65),Color.rgb(249, 135, 65),Color.rgb(249, 135, 65)};
    	//假如 每段的百分比  2  3  2  1  1  1
    	float [] part = new float[]{2,3,2,1,1,1};
    	//各等级段的值
    	float [] partValue = new float[]{12.1f,15.0f,20.0f,30.0f,50.0f,60.0f};
    	//当前值
    	String textValue = "126/76";
    	//当前值描述
    	String textDesc = "正常";
    	//当前值的等级
    	int textlevelIndex = 2;
    	//当前值文字大小
    	int textLevelSize = 30;
    	//当前值文字与顶部的距离
    	int marginTop = 30;
    	//指示三角形的宽度
    	int arrowWidth  = 20;
    	//指示三角形的高度
    	int arrowHeight = 10;
    	//等级条的高度
    	int levelHeight = 20;
    	//指示三角形与其他间距
    	int arrowMarginTop = 10;
    	//等级坐标文字大小
    	int partTextSize = 15;
    	//等级说明文字大小
    	int textDescSize = 22;
        
        //要显示图形的View
        LinearLayout chartLayout = (LinearLayout) findViewById(R.id.chartLayout);
        
        
        AfLevelSeriesRenderer renderer = new AfLevelSeriesRenderer();
        
        renderer.setWidth(width);
        renderer.setHeight(height);
        renderer.setColor(color);
        renderer.setPart(part);
        renderer.setPartValue(partValue);
        renderer.setTextValue(textValue);
        renderer.setTextDesc(textDesc);
        renderer.setTextlevelIndex(textlevelIndex);
        renderer.setTextLevelSize(textLevelSize);
        renderer.setMarginTop(marginTop);
        renderer.setArrowWidth(arrowWidth);
        renderer.setArrowHeight(arrowHeight);
        renderer.setArrowMarginTop(arrowMarginTop);
        renderer.setLevelHeight(levelHeight);
        renderer.setPartTextSize(partTextSize);
        renderer.setTextDescSize(textDescSize);
        renderer.setTextRectWidth(120);
        renderer.setTextRectHeight(50);
        
        AfLevelSeriesDataset mDataset = new AfLevelSeriesDataset();
        AfLevelView mAbLevelView = AfLevelChartFactory.getLevelChartView(this,mDataset,renderer);
        
        chartLayout.addView(mAbLevelView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
       
       
    }
    
}


