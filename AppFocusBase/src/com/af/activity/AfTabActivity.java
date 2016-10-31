/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.af.activity;

import com.af.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * <p>For apps developing against {@link android.os.Build.VERSION_CODES#HONEYCOMB}
 * or later, tabs are typically presented in the UI using the new
 * {@link ActionBar#newTab() ActionBar.newTab()} and
 * related APIs for placing tabs within their action bar area.</p>
 *
 * <p>A replacement for TabActivity can also be implemented by directly using
 * TabHost.  You will need to define a layout that correctly uses a TabHost
 * with a TabWidget as well as an area in which to display your tab content.
 * A typical example would be:</p>
 *
 * {@sample development/samples/Support4Demos/res/layout/fragment_tabs.xml complete}
 *
 * <p>The implementation needs to take over responsibility for switching
 * the shown content when the user switches between tabs.
 *
 * {@sample development/samples/Support4Demos/src/com/example/android/supportv4/app/FragmentTabs.java
 *      complete}
 *
 * <p>Also see the <a href="{@docRoot}resources/samples/Support4Demos/src/com/example/android/supportv4/app/FragmentTabsPager.html">
 * Fragment Tabs Pager</a> sample for an example of using the support library's ViewPager to
 * allow the user to swipe the content to switch between tabs.</p>
 *
 * @deprecated New applications should use Fragments instead of this class;
 * to continue to run on older devices, you can use the v4 support library
 * which provides a version of the Fragment API that is compatible down to
 * {@link android.os.Build.VERSION_CODES#DONUT}.
 */
 
public class AfTabActivity extends AfActivityGroup {
    private TabHost mTabHost;
    private String mDefaultTab = null;
    private int mDefaultTabIndex = -1;

    public AfTabActivity() {
    }

    /**
     * Sets the default tab that is the first tab highlighted.
     * 
     * @param tag the name of the default tab
     */
    public void setDefaultTab(String tag) {
        mDefaultTab = tag;
        mDefaultTabIndex = -1;
    }

    /**
     * Sets the default tab that is the first tab highlighted.
     * 
     * @param index the index of the default tab
     */
    public void setDefaultTab(int index) {
        mDefaultTab = null;
        mDefaultTabIndex = index;
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        ensureTabHost();
        String cur = state.getString("currentTab");
        if (cur != null) {
            mTabHost.setCurrentTabByTag(cur);
        }
        if (mTabHost.getCurrentTab() < 0) {
            if (mDefaultTab != null) {
                mTabHost.setCurrentTabByTag(mDefaultTab);
            } else if (mDefaultTabIndex >= 0) {
                mTabHost.setCurrentTab(mDefaultTabIndex);
            }
        }
    }

    @Override
    protected void onPostCreate(Bundle icicle) {        
        super.onPostCreate(icicle);

        ensureTabHost();

        if (mTabHost.getCurrentTab() == -1) {
            mTabHost.setCurrentTab(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String currentTabTag = mTabHost.getCurrentTabTag();
        if (currentTabTag != null) {
            outState.putString("currentTab", currentTabTag);
        }
    }

    /**
     * Updates the screen state (current list and other views) when the
     * content changes.
     * 
     *@see Activity#onContentChanged()
     */
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);

        if (mTabHost == null) {
            throw new RuntimeException(
                    "Your content must have a TabHost whose id attribute is " +
                    "'android.R.id.tabhost'");
        }
        mTabHost.setup(getLocalActivityManager());
    }

    private void ensureTabHost() {
        if (mTabHost == null) {
            this.setContentView( R.layout.ab_tab_content);
        }
    }

    @Override
    protected void
    onChildTitleChanged(Activity childActivity, CharSequence title) {
        // Dorky implementation until we can have multiple activities running.
        if (getLocalActivityManager().getCurrentActivity() == childActivity) {
            View tabView = mTabHost.getCurrentTabView();
            if (tabView != null && tabView instanceof TextView) {
                ((TextView) tabView).setText(title);
            }
        }
    }

    /**
     * Returns the {@link TabHost} the activity is using to host its tabs.
     *
     * @return the {@link TabHost} the activity is using to host its tabs.
     */
    public TabHost getTabHost() {
        ensureTabHost();
        return mTabHost;
    }

    /**
     * Returns the {@link TabWidget} the activity is using to draw the actual tabs.
     *
     * @return the {@link TabWidget} the activity is using to draw the actual tabs.
     */
    public TabWidget getTabWidget() {
        return mTabHost.getTabWidget();
    }
    
    /**
	 * Add a new tab to this GDTabActivity
	 * 
	 * @param tag
	 *            The tag associated this the new tab
	 * @param labelId
	 *            A resource ID to the label of the new tab
	 * @param intent
	 *            The Intent used to start the content of the tab
	 */
	public void addTab(String tag, int labelId, Intent intent) {
		addTab(tag, getString(labelId), intent);
	}

	/**
	 * Add a new tab to this GDTabActivity
	 * 
	 * @param tag
	 *            The tag associated this the new tab
	 * @param label
	 *            The label of the new tab
	 * @param intent
	 *            The Intent used to start the content of the tab
	 */
	@SuppressLint("NewApi")
	public void addTab(String tag, CharSequence label, Intent intent) {
		final TabHost host = getTabHost();

		View indicator = createTabIndicator(tag, label, getTabWidget());
		if (indicator == null) {
			final TextView textIndicator = (TextView) getLayoutInflater()
					.inflate(R.layout.ab_tab_indicator, getTabWidget(), false);
			textIndicator.setText(label);
			indicator = textIndicator;
		}

		host.addTab(host.newTabSpec(tag).setIndicator(indicator)
				.setContent(intent));
	}

	@Deprecated
	protected View createTabIndicator(CharSequence label) {
		return createTabIndicator(null, label, getTabWidget());
	}

	/**
	 * Callback allowing client to create their own tabs.
	 * 
	 * @param tag
	 *            The tag of the tab to create
	 * @param label
	 *            The label that need to be displayed in the tab
	 * @param parent
	 *            The parent in which the tab will be added.Please note you
	 *            shouldn't add the newly created/inflated View to the parent.
	 *            GDTabActivity will deal with this automatically.
	 * @return A newly allocated View representing the tab.
	 */
	protected View createTabIndicator(String tag, CharSequence label,
			ViewGroup parent) {
		return null;
	}
   
    
    
}
