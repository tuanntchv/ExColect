package com.tuannt.excollect.googleanalytics;

import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.tuannt.excollect.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TuanNT on 12/17/2015.
 */
public class AnalyticsTracker {
    public enum Target {
        APP,
        // Add more trackers here if you need, and update the code in #get(Target) below
    }

    private static AnalyticsTracker sInstance;
    private Context mContext;
    private Map<Target, Tracker> mTrackers = new HashMap<>();
    public static synchronized void init(Context context){
        if(sInstance != null){
            throw new IllegalStateException("Extra call to initialize analytics trackers");
        }
        sInstance = new AnalyticsTracker(context);
    }

    public static synchronized AnalyticsTracker getInstance(){
        if(sInstance == null){
            throw new IllegalStateException("Please call init first");
        }
        return sInstance;
    }

    private AnalyticsTracker(Context context) {
        mContext = context;
    }

    public synchronized Tracker get(Target target) {
        if (!mTrackers.containsKey(target)) {
            Tracker tracker;
            switch (target) {
                case APP:
                    tracker = GoogleAnalytics.getInstance(mContext).newTracker(R.xml.app_tracker);
                    break;
                default:
                    throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            mTrackers.put(target, tracker);
        }
        return mTrackers.get(target);
    }
}
