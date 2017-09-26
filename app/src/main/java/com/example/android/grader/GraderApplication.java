package com.example.android.grader;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Gauri Gadkari on 9/25/17.
 */

public class GraderApplication extends Application {
    private RefWatcher refWatcher;

    public static RefWatcher getRefWatcher(Context context) {
        GraderApplication application = (GraderApplication) context.getApplicationContext();
        return application.refWatcher;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // This instantiates DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
        // add for verbose logging
        // FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }
}
