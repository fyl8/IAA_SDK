package com.github.iaa.applovin;
import android.content.Context;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkInitializationConfiguration;

public class MaxManager {

    private final Context context;
    private static boolean log_level = true;
    private static boolean isEnvironment = true;
    public static boolean isInitAdjust = false;

    private static MaxManager maxManager = null;

    public static MaxManager getInstance(Context context) {
        if (maxManager == null) {
            maxManager = new MaxManager(context);
        }
        return maxManager;
    }

    public MaxManager(Context context){
        this.context = context;
    }

    /**
     * 是否开启日志
     */
    public MaxManager setEnabledLog(boolean isLog){
        log_level = isLog;
        return maxManager;
    }

    /***
     * 是否开启测试环境
     */
    public MaxManager setEnvironment(boolean environment){
        isEnvironment = environment;
        return maxManager;
    }

    /**初始化Adjust*/
    public MaxManager initAdjust(String app_token) {
//        String appToken = (TextUtils.isEmpty(app_token))?MaxConstant.ADJUST_APP_TOKEN:app_token;
        String environment = isEnvironment?AdjustConfig.ENVIRONMENT_SANDBOX:AdjustConfig.ENVIRONMENT_PRODUCTION;//测试环境   ENVIRONMENT_PRODUCTION//正式环境
        AdjustConfig config = new AdjustConfig(context, app_token, environment);
        config.setLogLevel(log_level?LogLevel.VERBOSE:LogLevel.SUPPRESS); //LogLevel.VERBOSE	启用所有日志记录  LogLevel.SUPPRESS	禁止所有日志记录
        Adjust.initSdk(config);
        isInitAdjust = true;
        return maxManager;
    }
//    private MaxManager initMax(){
//        AppLovinSdk.getInstance(context).setMediationProvider( AppLovinMediationProvider.MAX );
//        AppLovinSdk.initializeSdk( context, configuration -> {});
//        return maxManager;
//    }
//    public MaxManager initializedMax(MaxListener listener){
//        AppLovinSdk.getInstance( context ).setMediationProvider( "max" );
//        AppLovinSdk.initializeSdk( context, configuration -> {
//            if(null!=listener)listener.onSdkInitialized(configuration);
//        });
//        return maxManager;
//    }
    /**初始化Max*/
    public MaxManager initializedNewMax(String sdkKey, MaxListener listener){
        AppLovinSdkInitializationConfiguration initConfig = AppLovinSdkInitializationConfiguration.builder(sdkKey, context )
                .setMediationProvider( AppLovinMediationProvider.MAX )
                .build();
        AppLovinSdk.getInstance( context ).initialize( initConfig, sdkConfig -> {
            if(null!=listener)listener.onSdkInitialized(sdkConfig);
        });
        return maxManager;
    }

}
