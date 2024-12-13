package io.mini.play.iaa_sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxReward;
import com.github.iaa.applovin.MaxAds;
import com.github.iaa.applovin.MaxEvent;
import com.github.iaa.applovin.MaxRewardedAdsListener;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activity = this;
        //开启中介调试器,用来测试广告集成是否成功
//        MaxAds.startDebugger(activity);

        findViewById(R.id.btn_load).setOnClickListener((v)->{
            MaxAds.loadMaxRewardedAd(activity, TestApplication.MAX_REWARD_AD_ID, new MaxRewardedAdsListener() {
                @Override
                public void onAdLoadShow(MaxAd maxAd) {
                    Log.i("IAA","广告展示");
                }
                @Override
                public void onAdHidden(MaxAd maxAd) {
                    Log.i("IAA","广告隐藏");
                }
                @Override
                public void onUserRewarded(MaxAd maxAd, MaxReward reward) {
                    Log.i("IAA","观看完视频广告获得奖励");
                }
                @Override
                public void onAdClicked(MaxAd maxAd) {
                    Log.i("IAA","广告点击");
                }
                @Override
                public void onAdLoadFailed(String s) {
                    Log.i("IAA","广告加载失败："+s);
                }
            });
        });


        logEventFA(activity,"app_start");
        logEventFA(activity,"start_loading");
        logEventFA(activity,"finish_loading");
        logEventFA(activity,"click_startgame");
        logEventFA(activity,"enter_lv_x","游戏ID_1","关卡2");
        logEventFA(activity,"enter_lv_x","游戏ID_1","关卡3");
        logEventFA(activity,"enter_lv_x","游戏ID_1","关卡3");
        logEventFA(activity,"app_end");
    }

    /**firebase普通事件，不带参数的埋点方法*/
    public static void logEventFA(Context context, String eventName){
        FirebaseAnalytics.getInstance(context).logEvent(eventName,new Bundle());
    }
    /**firebase普通事件，带参数的埋点方法*/
    public static void logEventFA(Context context,String eventName,String gameID,String levelNumber){
        Bundle bundle = new Bundle();
        bundle.putString("game_id",gameID);
        bundle.putString("level_number",levelNumber);
        FirebaseAnalytics.getInstance(context).logEvent(eventName,bundle);
    }


}