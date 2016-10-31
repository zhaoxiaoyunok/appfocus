package com.lyyj.activity.demo.gallery.staggeredgridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.appfocusbase.R;
import com.lyyj.activity.demo.gallery.staggeredgridview.library.views.StaggeredGridView;


/**
 * 
 * This will not work so great since the heights of the imageViews 
 * are calculated on the iamgeLoader callback ruining the offsets. To fix this try to get 
 * the (intrinsic) image width and height and set the views height manually. I will
 * look into a fix once I find extra time.
 * 
 * @author Maurycy Wojtowicz
 *
 */
public class StaggeredGridViewActivity extends Activity {

	/**
	 * Images are taken by Romain Guy ! He's a great photographer as well as a
	 * great programmer. http://www.flickr.com/photos/romainguy
	 */
	
	private String urls[] = { 
			"http://s13.sinaimg.cn/middle/003i5RUngy6NlRZUWZeec&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlS1HNBSa8&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlS1Wv7Va7&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlS2wYmr7f&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlS399rLc5&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlS4n9WSee&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlS5Guajf7&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlS72lDD4d&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlS72lDD4d&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlS9cUsV11&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlSaHHgoec&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlSc8txn09&690",
				"http://s4.sinaimg.cn/small/003i5RUngy6NlSdhvGj03&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlSeHIph57&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlSfBHwW6e&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlSgmOssfc&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlSh54KJ49&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlSiADzB3b&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlSlUviR9d&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlSmZkfH95&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlSo7yxgde&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlSqsIz4ee&690",
				"http://s1.sinaimg.cn/small/003i5RUngy6NlSqZdKM40&690",
				"http://s4.sinaimg.cn/small/003i5RUngy6NlSrYDfR43&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlSsY9pR47&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlSuncbv21&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlSKkAUm76&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlSKRWub6f&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlSLyVkMcc&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlSMCBS981&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlST6QL97b&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlSTRA5ja1&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlSVtDc531&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlSXsDzR1b&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlSXMTMcb8&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlSY99iL27&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlSYNkNh77&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlSZ0Jfu44&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlSZnSwFd9&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlSZNZ3n0d&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlT0U3Tza1&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlT1ibI73b&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlT1Fzjbd9&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlT1WvA95d&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlT2tb1Eaa&690",
				"http://s3.sinaimg.cn/small/003i5RUngy6NlT2VSuK82&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlT3gHpb55&690",

				"http://s13.sinaimg.cn/small/003i5RUngy6NlT3Gcnq9c&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlT44cLb9d&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlT4qqrL71&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlT4XcS591&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlT5hI3c1e&690",
				"http://s1.sinaimg.cn/small/003i5RUngy6NlT5D2us60&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlT62LVWf4&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlT6rbMi46&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlT6NRQy46&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlT79Ymy6e&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlT7lYWOd6&690",

				"http://s7.sinaimg.cn/small/003i5RUngy6NlT7Csdwd6&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlT7XXhd8f&690",

				"http://s16.sinaimg.cn/small/003i5RUngy6NlT8iEpx9f&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlT8GOy014&690",

				"http://s4.sinaimg.cn/small/003i5RUngy6NlT8UQVB63&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlT9yNPUb6&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlT9VDg327&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlTCj1J395&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlTCIe1o6e&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlTDb06d79&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlTKXjok18&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlTLegsO9a&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlTLGZYq16&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlTM2SSdc9&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlTMv6kiea&690",

				"http://s2.sinaimg.cn/small/003i5RUngy6NlTMSUKd41&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlTN8Mpp97&690",
				"http://s4.sinaimg.cn/small/003i5RUngy6NlTNBiG743&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlTO7KwC66&690",
				"http://s4.sinaimg.cn/small/003i5RUngy6NlTOtANta3&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlTOTYTh9b&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlTPj5tXcd&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlTPP8fx5b&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlTQfQkc5c&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlTQH1Zd9f&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlTR8w9Ncf&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlTRwXZqfc&690",
				"http://s1.sinaimg.cn/small/003i5RUngy6NlTRSiiI50&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlWaVW7j05&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWbDjUke8&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWc4l7228&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWcjwfed8&690",

				"http://s3.sinaimg.cn/small/003i5RUngy6NlWcLlDAb2&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlWd6ILj45&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlWdw2zyf4&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlWdUY6S2a&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlWeheKR69&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWeACKY38&690",
				"http://s15.sinaimg.cn/small/003i5RUngy6NlWeUTbE4e&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlWfiirFdb&690",
				"http://s1.sinaimg.cn/small/003i5RUngy6NlWfILeg40&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlWgcdzzf1&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlWgzep3b1&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlWgXZCJfd&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlWhnMyBf9&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlWiiZWp29&690",

				"http://s13.sinaimg.cn/small/003i5RUngy6NlWiH4jafc&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlWjaCU38b&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlWjDsaN85&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlWjYLQm3a&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlWBfxfza1&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlWBGSQQ24&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlWC2O53d1&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlWCK0lxa7&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWDbhP238&690",

				"http://s11.sinaimg.cn/small/003i5RUngy6NlWDsIs28a&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlWDNwLi5c&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWEe3i0f8&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlWEEfswe4&690",
				"http://s2.sinaimg.cn/small/003i5RUngy6NlWF8q5z91&690",
				"http://s11.sinaimg.cn/small/003i5RUngy6NlWFU7bA3a&690",
				"http://s3.sinaimg.cn/small/003i5RUngy6NlWGsF7sb2&690",
				"http://s14.sinaimg.cn/small/003i5RUngy6NlWGVaJn7d&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWHopTi38&690",

				"http://s13.sinaimg.cn/small/003i5RUngy6NlWItJRG0c&690",
				"http://s5.sinaimg.cn/small/003i5RUngy6NlWJ0XHKd4&690",
				"http://s13.sinaimg.cn/small/003i5RUngy6NlWJClSQbc&690",
				"http://s10.sinaimg.cn/small/003i5RUngy6NlWK5ps569&690",
				"http://s8.sinaimg.cn/small/003i5RUngy6NlWKwBYHa7&690",

				"http://s8.sinaimg.cn/small/003i5RUngy6NlWKVfUz87&690",

				"http://s15.sinaimg.cn/small/003i5RUngy6NlWLnFPUee&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWM6jd6c8&690",

				"http://s14.sinaimg.cn/small/003i5RUngy6NlWMtC452d&690",
				"http://s7.sinaimg.cn/small/003i5RUngy6NlWMOib4e6&690",
				"http://s16.sinaimg.cn/small/003i5RUngy6NlWNh5bVff&690",
				"http://s9.sinaimg.cn/small/003i5RUngy6NlWNDfio08&690",
				"http://s6.sinaimg.cn/small/003i5RUngy6NlWOb9Ax45&690",
				"http://s12.sinaimg.cn/small/003i5RUngy6NlWOClt1cb&690"
	};
	
	StaggeredAdapter adapter;
	/**
	 * This will not work so great since the heights of the imageViews 
	 * are calculated on the iamgeLoader callback ruining the offsets. To fix this try to get 
	 * the (intrinsic) image width and height and set the views height manually. I will
	 * look into a fix once I find extra time.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyyj_gallery_staggeredgridview_activity_main);
		
		StaggeredGridView gridView = (StaggeredGridView) this.findViewById(R.id.staggeredGridView1);
		
		int margin = getResources().getDimensionPixelSize(R.dimen.margin);
		
		gridView.setItemMargin(margin); // set the GridView margin
		
		gridView.setPadding(margin, 0, margin, 0); // have the margin on the sides as well 
		
		 adapter = new StaggeredAdapter(StaggeredGridViewActivity.this, R.id.imageView1, urls);
		gridView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.notifyDataSetChanged();
		
	}


}
