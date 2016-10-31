package com.lyyj.activity.demo.button.processbutton.sample;

import com.appfocusbase.R;
import com.lyyj.activity.demo.button.processbutton.iml.GenerateProcessButton;
import com.lyyj.activity.demo.button.processbutton.sample.utils.ProgressGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class UploadActivity extends Activity implements ProgressGenerator.OnCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyyj_btn_processbtn_ac_upload);

        final ProgressGenerator progressGenerator = new ProgressGenerator(this);
        final GenerateProcessButton btnUpload =
                (GenerateProcessButton) findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressGenerator.start(btnUpload);
                btnUpload.setEnabled(false);
            }
        });
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, R.string.Loading_Complete, Toast.LENGTH_LONG).show();
    }
}
