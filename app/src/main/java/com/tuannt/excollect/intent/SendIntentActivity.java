package com.tuannt.excollect.intent;

import android.os.Bundle;
import android.widget.TextView;

import com.tuannt.excollect.BaseActivity;
import com.tuannt.excollect.R;

/**
 * Comment
 *
 * @author TuanNT
 */
public class SendIntentActivity extends BaseActivity {
    private TextView mTvCustomScheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_intent);
        mTvCustomScheme = (TextView) findViewById(R.id.mTvCustomScheme);

    }


}
