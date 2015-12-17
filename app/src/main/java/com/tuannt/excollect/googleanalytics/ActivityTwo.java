package com.tuannt.excollect.googleanalytics;

import android.os.Bundle;

import com.tuannt.excollect.AppController;
import com.tuannt.excollect.BaseActivity;
import com.tuannt.excollect.R;

/**
 * Created by TuanNT on 12/17/2015.
 */
public class ActivityTwo extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Manual tracking
        //AppController.getInstance().trackScreenView("Screen 2");
        setContentView(R.layout.activity_two);
    }

    @Override
    public void finish() {
        AppController.getInstance().trackEvent("Click","back","activity 2 back");
        super.finish();
    }
}
