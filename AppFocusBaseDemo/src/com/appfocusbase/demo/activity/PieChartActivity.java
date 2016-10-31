package com.appfocusbase.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

 







import com.af.activity.AfActivity;
import com.af.view.chart.CategorySeries;
import com.af.view.chart.ChartFactory;
import com.af.view.chart.DefaultRenderer;
import com.af.view.chart.SimpleSeriesRenderer;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.global.MyApplication;
/**
 * © 2015 lyyj.com。
 * 名称：PieChartActivity
 * 描述：饼图
 * @author kaka
 * @date 2011-12-13
 * @version
 */
public class PieChartActivity extends AfActivity {
	
	private MyApplication application;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setAfContentView(R.layout.chart);
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText(R.string.chart_pie);
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)mAfApplication;
        
    	//要显示图形的View
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.chart01);
		
		//每个块的颜色
		int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
		//渲染器
		DefaultRenderer renderer = new DefaultRenderer();
		//图表标题
	    renderer.setChartTitle("我是图表的标题");
	    //图形标题文字大小
  		renderer.setChartTitleTextSize(25);
  		//轴线上标签文字大小
  		renderer.setLabelsTextSize(15);
  		//说明文字大小
  		renderer.setLegendTextSize(15);
  	    //设置图表上标题与X轴与Y轴的说明文字颜色
	    renderer.setLabelsColor(Color.GRAY);
		//说明文字大小
		renderer.setLegendTextSize(15);
		for (int color : colors) {
			  //创建SimpleSeriesRenderer对象
		      SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		      r.setColor(color);
		      renderer.addSeriesRenderer(r);
		}
		//创建数据填充器
		CategorySeries series = new CategorySeries("支出情况");
		series.add("住房", 28);
		series.add("食物", 25);
		series.add("水电", 2);
		series.add("娱乐", 20);
		series.add("服装", 25);
		
		//设置图表的背景颜色
	    renderer.setApplyBackgroundColor(true);
	    renderer.setBackgroundColor(Color.rgb(222, 222, 200));
	    //renderer.setMargins(new int[]{5,5,5,5});
		//获取图表View
	    View chart = ChartFactory.getPieChartView(this,series,renderer);
        linearLayout.addView(chart);
		
      } 
    
}
