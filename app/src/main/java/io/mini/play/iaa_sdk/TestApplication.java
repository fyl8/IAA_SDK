package io.mini.play.iaa_sdk;

import android.app.Application;
import android.util.Log;

import com.github.iaa.applovin.MaxManager;

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MaxManager.init(this);
        MaxManager.initializedNewMax(this, configuration -> {
            Log.e("IAA","初始化成功");
        });
    }

}
