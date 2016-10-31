package com.lyyj.activity.demo.button.circlebutton;

import com.appfocusbase.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
 

public class CircleButtonMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_btn_circlebtn_activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public static class PlaceholderFragment extends Fragment implements View.OnClickListener {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.lyyj_btn_circlebtn_fragment_main, container, false);

			rootView.findViewById(R.id.button0).setOnClickListener(this);
			rootView.findViewById(R.id.button1).setOnClickListener(this);
			rootView.findViewById(R.id.button2).setOnClickListener(this);

			return rootView;
		}

		@Override
		public void onClick(View view) {
			Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();
		}
	}

}
