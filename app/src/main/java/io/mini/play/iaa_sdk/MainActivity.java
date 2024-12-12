package io.mini.play.iaa_sdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxReward;
import com.github.iaa.Test;
import com.github.iaa.applovin.MaxAds;
import com.github.iaa.applovin.MaxRewardedAdsListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activity = this;
        Test.logTest("12345");
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

    }

}