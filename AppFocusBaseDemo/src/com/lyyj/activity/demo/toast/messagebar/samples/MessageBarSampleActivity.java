package com.lyyj.activity.demo.toast.messagebar.samples;
 
import com.af.activity.AfActivity;
import com.af.view.titlebar.AfTitleBar;
import com.appfocusbase.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import net.simonvt.messagebar.MessageBar;

public class MessageBarSampleActivity extends  AfActivity implements MessageBar.OnMessageClickListener {

    private static final String STATE_MESSAGEBAR = "net.simonvt.messagebar.samples.SampleActivity.messageBar";
    private static final String STATE_COUNT = "net.simonvt.messagebar.samples.SampleActivity.count";

    private MessageBar mMessageBar;

    private TextView mTextView;

    private int mCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAfContentView(R.layout.lyyj_toast_messagebar_main);
        
        
        AfTitleBar mAfTitleBar = this.getTitleBar();
        mAfTitleBar.setTitleText("messagebar");
        mAfTitleBar.setLogo(R.drawable.button_selector_back);
        mAfTitleBar.setTitleBarBackground(R.drawable.top_bg);
        mAfTitleBar.setTitleTextMargin(10, 0, 0, 0);
        mAfTitleBar.setLogoLine(R.drawable.line);
	    
        
        mTextView = (TextView) findViewById(R.id.messageClickedTextView);

        mMessageBar = new MessageBar(this);
        mMessageBar.setOnClickListener(this);

        findViewById(R.id.withText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMessageBar.show("Message #" + mCount);
                mCount++;
            }
        });

        findViewById(R.id.withTextAndButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("count", mCount);
                mMessageBar.show("Message #" + mCount, "Button!", R.drawable.lyyj_toast_messagebar_ic_messagebar_undo, b);
                mCount++;
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        mMessageBar.onRestoreInstanceState(inState.getBundle(STATE_MESSAGEBAR));
        mCount = inState.getInt(STATE_COUNT);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(STATE_MESSAGEBAR, mMessageBar.onSaveInstanceState());
        outState.putInt(STATE_COUNT, mCount);
    }

    @Override
    public void onMessageClick(Parcelable token) {
        Bundle b = (Bundle) token;
        final int count = b.getInt("count");
        mTextView.setText("You clicked message #" + count);
    }
}
