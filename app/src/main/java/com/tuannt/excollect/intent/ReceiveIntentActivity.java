package com.tuannt.excollect.intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.tuannt.excollect.BaseActivity;
import com.tuannt.excollect.R;

import java.util.List;

/**
 * Comment
 *
 * @author TuanNT
 */
public class ReceiveIntentActivity extends BaseActivity {
    private static final String TAG = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_itent);

        Uri data = getIntent().getData();
        Log.d(TAG, "host " + data.getHost());
        Log.d(TAG, "scheme" + data.getScheme());
        List<String> params = data.getPathSegments();
        for (String param : params) {
            Log.d(TAG, "param: " + param);
        }
    }
}
