package com.lyyj.activity.demo.textview.fontcustom;

import com.appfocusbase.R;

import android.app.Activity;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FontCustomActivity extends Activity {
	
	private ListView lv;
	private TextView tv;
	
	private void initView(){
		lv = (ListView)findViewById(R.id.listView1);
		tv = (TextView)findViewById(R.id.textView1);
	}
	
	private void addAdapter(){
		String[] str = new String[]{"演示自定义字体","改变文字颜色及背景颜色","变成粗体","变成斜体",
				"增加下划线","增加删除线","设置缩放效果","演示上标",
				"演示下标","设置链接样式","设置文字模糊效果"};
		lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str));
	}
	
	/**本示例重点部分。根据点击的条目改变TextView的文字样式*/
	private void addListener(){
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				tv.setTypeface(null);  //初始化字体，为了还原设置的自定义字体
				switch(position){
				//演示自定义字体
				case 0:
					Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/BRUSHSCI.TTF");  //通过自定义字体生成字体对象
					tv.setText("hello.i'm \"brushsci\" fonts");
					tv.setTypeface(tf);  //将TextView设置自定义字体。
					break;
				//改变文字颜色及背景颜色
				case 1:
					Spannable spanColor = new SpannableString("红色字体，蓝色背景字体，绿色文字及黄色背景");
					BackgroundColorSpan bcSpanBlue = new BackgroundColorSpan(Color.BLUE);  //定义蓝色背景样式
					BackgroundColorSpan bcSpanYellow = new BackgroundColorSpan(Color.YELLOW);  //定义黄色背景样式
					ForegroundColorSpan fcSpanRed = new ForegroundColorSpan(Color.RED);  //定义红色字体样式
					ForegroundColorSpan fcSpanGreen = new ForegroundColorSpan(Color.GREEN);  //定义红色字体样式
				
					spanColor.setSpan(fcSpanRed,0 , 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); //设置字体样式。参数为样式对象，起始位置，结束位置，是否包含断点标记
					spanColor.setSpan(fcSpanGreen,12 , 21, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); 
					spanColor.setSpan(bcSpanBlue,5, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); 
					spanColor.setSpan(bcSpanYellow,12 , 21, Spannable.SPAN_INCLUSIVE_EXCLUSIVE); 
					
					tv.setText(spanColor);
					break;
				//变成粗体
				case 2:
					Spannable spanStyleBold = new SpannableString("显示粗体文字");
					StyleSpan BoldStyleSpan = new StyleSpan(Typeface.BOLD);  //设置粗体样式
					spanStyleBold.setSpan(BoldStyleSpan, 2, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(spanStyleBold);
					break;
				//变成斜体
				case 3:
					Spannable spanStyleItalic = new SpannableString("Italic Font");  //中文不支持 
					StyleSpan ItalicStyleSpan = new StyleSpan(Typeface.ITALIC);  //设置斜体样式
					spanStyleItalic.setSpan(ItalicStyleSpan, 0, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(spanStyleItalic);
					break;
				//增加下划线
				case 4:
					Spannable underlineSpan = new SpannableString("下划线演示");
					UnderlineSpan ulSpan = new UnderlineSpan();  //设置下划线样式
					underlineSpan.setSpan(ulSpan, 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(underlineSpan);
					break;
				//增加删除线
				case 5:
					Spannable spanStrikethrough = new SpannableString("显示删除线样式");
					StrikethroughSpan stSpan = new StrikethroughSpan();  //设置删除线样式
					spanStrikethrough.setSpan(stSpan, 2, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(spanStrikethrough);
					break;
				//设置缩放效果
				case 6:
					Spannable scaleSpan = new SpannableString("横向放大，正常文字，横向缩小");
					ScaleXSpan sxSpan1 = new ScaleXSpan(1.5f);  //设置横向放大1.5倍
					ScaleXSpan sxSpan2 = new ScaleXSpan(0.5f);  //设置横向缩小到0.5
					scaleSpan.setSpan(sxSpan1, 0, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					scaleSpan.setSpan(sxSpan2, 10, 14, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(scaleSpan);
					break;
				//演示上标
				case 7:
					Spannable superSpan = new SpannableString("勾股定理：a2+b2=c2");
					superSpan.setSpan(new SuperscriptSpan(), 6, 7, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					superSpan.setSpan(new SuperscriptSpan(), 9, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					superSpan.setSpan(new SuperscriptSpan(), 12, 13, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(superSpan);
					break;
				//演示下标
				case 8:
					Spannable subSpan = new SpannableString("碳酸钠的化学方程式是:Na2CO3");
					subSpan.setSpan(new SubscriptSpan(), 13, 14, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					subSpan.setSpan(new SubscriptSpan(), 16, 17, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(subSpan);
					break;
				//设置链接样式
				case 9:
					Spanned linkStr = Html.fromHtml("点击<a href=\"http://www.baidu.com\">这里</a>访问百度，点击<a href=\"tel:10086\">这里</a>拨打10086");  //将字符串按html格式解析为spanned对象
					tv.setText(linkStr);
					tv.setMovementMethod(LinkMovementMethod.getInstance());  //允许TextView使用超连接。如果不设置点击没有效果。
					tv.setLinkTextColor(Color.RED);  //设置超链接文本颜色，默认为蓝色
					break;
				//设置文字模糊效果
				case 10:
					Spannable spanMohu = new SpannableString("模糊效果演示");
					MaskFilterSpan mfSpan1 = new MaskFilterSpan(new BlurMaskFilter(5, Blur.OUTER));  //设置发光半径及发光效果
					spanMohu.setSpan(mfSpan1, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
					tv.setText(spanMohu);
					break;
									
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_tv_fontcustom_activity_main);
		initView();
		addAdapter();
		addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
