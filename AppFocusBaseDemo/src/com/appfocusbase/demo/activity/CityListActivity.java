package com.appfocusbase.demo.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

 















import com.af.activity.AfActivity;
import com.af.fragment.AfDialogFragment.AfDialogOnLoadListener;
import com.af.fragment.AfLoadDialogFragment;
import com.af.task.AfTask;
import com.af.task.AfTaskItem;
import com.af.task.AfTaskListListener;
import com.af.util.AfCharacterParser;
import com.af.util.AfDialogUtil;
import com.af.util.AfLogUtil;
import com.af.util.AfToastUtil;
import com.af.view.sample.AfLetterFilterListView;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;
import com.appfocusbase.demo.adapter.CityListAdapter;
import com.appfocusbase.demo.model.City;
import com.appfocusbase.global.MyApplication;

public class CityListActivity extends AfActivity{

	private MyApplication application;
	private List<City> list = null;
	private ListView mListView = null;
	private EditText mSearchEditText = null;
	private AfTitleBar mAbTitleBar = null;
	private CityListAdapter mCityListAdapter = null;
	private AfLoadDialogFragment  mDialogFragment = null;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAfContentView(R.layout.city_list);
		application = (MyApplication) mAfApplication;

		mAbTitleBar = this.getTitleBar();
		mAbTitleBar.setTitleText(R.string.city_list_name);
		mAbTitleBar.setLogo(R.drawable.button_selector_back);
		mAbTitleBar.setTitleBarBackground(R.drawable.top_bg);
		mAbTitleBar.setTitleTextMargin(10, 0, 0, 0);
		mAbTitleBar.setLogoLine(R.drawable.line);

		// 获取ListView对象
		View headerView = LayoutInflater.from(this).inflate(R.layout.city_header, null);
		mListView = (ListView) this.findViewById(R.id.listView);
		mListView.addHeaderView(headerView);
		
		AfLetterFilterListView letterView = (AfLetterFilterListView)this.findViewById(R.id.letterView);
		
		mSearchEditText = (EditText) this.findViewById(R.id.editText);
		
		// ListView数据
		list = new ArrayList<City>();

		// 使用自定义的Adapter
		mCityListAdapter = new CityListAdapter(this, list);
		mListView.setAdapter(mCityListAdapter);

		// item被点击事件
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				AfToastUtil.showToast(CityListActivity.this, "点击"+position);
			}
		});

		//显示进度框
		mDialogFragment = AfDialogUtil.showLoadDialog(this, R.drawable.ic_load, "查询中,请等一小会");
		mDialogFragment
		.setAfDialogOnLoadListener(new AfDialogOnLoadListener() {

			@Override
			public void onLoad() {
				// 下载网络数据
				downTask();
			}

		});
		
		mSearchEditText.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String str = mSearchEditText.getText().toString().trim();
				int length = str.length();
				if (length > 0) {
					filterData(str);
				} else {
					downTask();
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {

			}
		});

	}

	
	public void downTask() {
		AfLogUtil.prepareLog(CityListActivity.class);
		AfTask mAbTask = AfTask.newInstance();
		final AfTaskItem item = new AfTaskItem();
		item.setListener(new AfTaskListListener() {
			@Override
			public List<?> getList() {
				List<City> newList = null;
				try {
					newList = filledData(getResources().getStringArray(R.array.list));
				} catch (Exception e) {
				}
				return newList;
			}

			@Override
			public void update(List<?> paramList) {
				list.clear();
				list.addAll((List<City>)paramList);
				//通知Dialog
				mDialogFragment.loadFinish();
				mCityListAdapter.notifyDataSetChanged();
			}

		});

		mAbTask.execute(item);
	}
	
	/**
	 * 为ListView填充数据
	 * @param date
	 * @return
	 */
	private List<City> filledData(String [] array){
		List<City> newList = new ArrayList<City>();
		//实例化汉字转拼音类
		AfCharacterParser	characterParser = AfCharacterParser.getInstance();
		
		for(int i=0; i<array.length; i++){
			City city = new City();
			city.setName(array[i]);
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(array[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				city.setFirstLetter(sortString.toUpperCase());
			}else{
				city.setFirstLetter("#");
			}
			newList.add(city);
		}
		Collections.sort(newList);
		return newList;
		
	}
	
	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		//实例化汉字转拼音类
		AfCharacterParser characterParser = AfCharacterParser.getInstance();
		List<City> filterDateList = new ArrayList<City>();
		if(!TextUtils.isEmpty(filterStr)){
			for(City city : list){
				String name = city.getName();
				if(name.indexOf(filterStr) != -1 || characterParser.getSelling(name).startsWith(filterStr)){
					filterDateList.add(city);
				}
			}
		}
		
		// 根据a-z进行排序
		Collections.sort(filterDateList);
		mCityListAdapter.updateListView(filterDateList);
	}

	

	@Override
	protected void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}


}
