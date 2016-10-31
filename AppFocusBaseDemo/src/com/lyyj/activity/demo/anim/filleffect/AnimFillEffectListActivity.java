package com.lyyj.activity.demo.anim.filleffect;

import com.appfocusbase.R;
import com.lyyj.activity.demo.anim.filleffect.SwitchAnimationUtil.AnimationType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class AnimFillEffectListActivity extends Activity {
	private ListView mList;
	private SwitchAnimationUtil mSwitchAnimationUtil;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_anim_filleffect_activity_list);
		mList = (ListView) findViewById(R.id.list);
		mList.setAdapter(new ListAdapter());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lyyj_anim_filleffect_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_alpha:
			Constant.mType = AnimationType.ALPHA;
			break;
		case R.id.action_flip_horizon:
			Constant.mType = AnimationType.FLIP_HORIZON;
			break;
		case R.id.action_flip_vertical:
			Constant.mType = AnimationType.FLIP_VERTICAL;
			break;
		case R.id.action_horizon_left:
			Constant.mType = AnimationType.HORIZION_LEFT;
			break;
		case R.id.action_horizon_right:
			Constant.mType = AnimationType.HORIZION_RIGHT;
			break;
		case R.id.action_rotate:
			Constant.mType = AnimationType.ROTATE;
			break;
		case R.id.action_scale:
			Constant.mType = AnimationType.SCALE;
			break;
		case R.id.action_cross:
			Constant.mType = AnimationType.HORIZON_CROSS;
			break;
		case R.id.action_next:
			break;
		}
		Intent intent = new Intent(AnimFillEffectListActivity.this, AnimFillEffectFragmentDemo.class);
		startActivity(intent);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (mSwitchAnimationUtil == null) {
			mSwitchAnimationUtil = new SwitchAnimationUtil();
			mSwitchAnimationUtil.startAnimation(mList, Constant.mType);
		}
	}

	private class ListAdapter extends BaseAdapter {
		private int[] res = new int[] { R.drawable.lyyj_anim_filleffect_charming, R.drawable.lyyj_anim_filleffect_p3 };

		@Override
		public int getCount() {
			return 99;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			if (convertView == null) {
				convertView = LayoutInflater.from(AnimFillEffectListActivity.this).inflate(
						R.layout.lyyj_anim_filleffect_view_list_item, null);
			}
			ImageView avatar = (ImageView) convertView
					.findViewById(R.id.item_avatar);
			avatar.setImageResource(res[position % res.length]);
			return convertView;
		}
	}
}
