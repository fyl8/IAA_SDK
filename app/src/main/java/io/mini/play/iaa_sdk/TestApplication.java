package io.mini.play.iaa_sdk;

import android.app.Application;
import android.util.Log;
import com.github.iaa.applovin.MaxManager;

public class TestApplication extends Application {
    public static final String MAX_REWARD_AD_ID = "7fc43e000ab7f2ae ";
    public static final String APPLOVIN_MAX_SDK = "K3dpPzFlL2McMkQ0opxRbPJmMXvo0mTtrlARsNRhxAIRAQnT2DhDkrPP8ROP1TT2WO7yCpa3ixNbHdI8TTxRsA";
    public static final String ADJUST_APP_TOKEN = "hgtzq2p9i8lc";
    @Override
    public void onCreate() {
        super.onCreate();
        MaxManager.getInstance(this)
                .setEnvironment(true)//设置是否是测试环境，false:关闭测试环境
                .setEnabledLog(true)//设置是否输出日志，false：关闭日志
                .initAdjust(TestApplication.ADJUST_APP_TOKEN)//初始化Adjust，MaxConstant.ADJUST_APP_TOKEN：token找运营要
                .initializedNewMax(TestApplication.APPLOVIN_MAX_SDK, configuration -> { //初始化MAX，MaxConstant.APPLOVIN_MAX_SDK：KEY 找运营要
                    Log.e("IAA","初始化成功");
                 });
    }
}
