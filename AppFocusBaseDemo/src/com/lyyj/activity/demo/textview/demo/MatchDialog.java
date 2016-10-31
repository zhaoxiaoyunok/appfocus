package com.lyyj.activity.demo.textview.demo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appfocusbase.R;
import com.lyyj.activity.demo.textview.library.MatchButton;
import com.lyyj.activity.demo.textview.library.MatchTextView;
import com.lyyj.activity.demo.textview.library.util.MatchView;
 

/**
 * Description:MatchDialog Demo
 * User: Lj
 * Date: 2014-12-04
 * Time: 10:00
 * FIXME
 */
//@SuppressLint("ValidFragment")
public class MatchDialog extends DialogFragment {

    public MatchDialog() {
    }

    Dialog mDialog;
    MatchTextView matchTextView;
    MatchButton mMatchButton;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mDialog == null) {
            mDialog = new Dialog(getActivity(), R.style.cart_dialog);
            mDialog.setContentView(R.layout.lyyj_tv_matchview_dialog_match);
            mDialog.setCanceledOnTouchOutside(true);
            mDialog.getWindow().setGravity(Gravity.CENTER);
            View view = mDialog.getWindow().getDecorView();
            matchTextView = (MatchTextView) view.findViewById(R.id.mTextView);
            matchTextView.setMatchOutListener(new MatchView.MatchOutListener() {
                @Override
                public void onBegin() {

                }

                @Override
                public void onProgressUpdate(float progress) {
                }

                @Override
                public void onFinish() {
                    MatchDialog.super.onStop();
                }
            });

            mMatchButton = (MatchButton) view.findViewById(R.id.mButton);
            mMatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMatchButton.hide();
                    matchTextView.hide();
                }
            });
        }
        return mDialog;
    }
}
