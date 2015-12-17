package com.tuannt.excollect.googleanalytics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tuannt.excollect.AppController;
import com.tuannt.excollect.BaseActivity;
import com.tuannt.excollect.R;

/**
 * Created by TuanNT on 12/17/2015.
 */
public class ActivityOne extends BaseActivity {
    private Button mButton;
    private Button mBtnCrash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mButton = (Button) findViewById(R.id.btn);
        mBtnCrash = (Button) findViewById(R.id.btnCrash);


        mBtnCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int a = 2/0;
                }catch (Exception e){
                    AppController.getInstance().trackException(e);
                }

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppController.getInstance().trackEvent("Button", "Click", "switch activity");
                Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
                startActivity(intent);
            }
        });
    }
}
