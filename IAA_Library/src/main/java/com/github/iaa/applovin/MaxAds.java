package com.github.iaa.applovin;

import android.app.Activity;
import android.util.Log;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import java.util.HashMap;
import java.util.Map;

public class MaxAds {

    public static void loadMaxRewardedAd(Activity activity, String reward_id,MaxRewardedAdsListener listener){
        MaxRewardedAd rewardedAd = MaxRewardedAd.getInstance(reward_id, activity);
        rewardedAd.setRevenueListener(maxAd -> {
            double revenue = maxAd.getRevenue();
            String adUnitId = maxAd.getAdUnitId(); // The MAX Ad Unit ID
            Log.d("IAA","UNIT_ID： "+adUnitId+", 收入："+revenue);
            MaxEvent.logRevenueEvent(activity,maxAd);
        });
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onUserRewarded(MaxAd maxAd, MaxReward reward) {
                Log.d("IAA","onUserRewarded-------Rewarded user: " + reward.getAmount() + " " + reward.getLabel());
                if(null!=listener)listener.onUserRewarded(maxAd,reward);
            }
            @Override
            public void onAdLoaded(MaxAd maxAd) {
                Log.d("IAA","onAdLoaded");
                if (rewardedAd.isReady()) {
                    rewardedAd.showAd(activity);
                }else{
                    if(null!=listener)listener.onAdLoadFailed("Ad not ready, please try again later");
                }
            }
            @Override
            public void onAdDisplayed(MaxAd maxAd) {
                Log.d("IAA","onAdDisplayed");
                if(null!=listener)listener.onAdLoadShow(maxAd);
            }
            @Override
            public void onAdHidden(MaxAd maxAd) {
                Log.d("IAA","onAdHidden");
                if(null!=listener)listener.onAdHidden(maxAd);
            }
            @Override
            public void onAdClicked(MaxAd maxAd) {
                Log.d("IAA","onAdClicked");
                Map<String,String> maps = new HashMap<>();
                maps.put("click_time",String.valueOf(System.currentTimeMillis()));
                maps.put("click_number","2");
                maps.put("click_id","1087901");
                MaxEvent.logEventADorFA(activity,"adioxqs","ad_click",maps);
                if(null!=listener)listener.onAdClicked(maxAd);
            }
            @Override
            public void onAdLoadFailed(String s, MaxError maxError) {
                Log.d("IAA","onAdLoadFailed:"+maxCodeByMsg(maxError.getCode())+",maxError:"+maxError.getCode());
                if(null!=listener)listener.onAdLoadFailed("onAdLoadFailed:"+s+",maxError:"+maxError.getCode());
            }
            @Override
            public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
                if(null!=listener)listener.onAdLoadFailed("onAdDisplayFailed:"+maxError.getMessage()+",maxError:"+maxError.getCode());
            }
        });
        rewardedAd.loadAd();
    }
    public static String maxCodeByMsg(int code){
        switch (code){
            case -1: return "系统处于意外状态。此错误代码表示无法归类为其他已定义错误之一的错误。有关更多详细信息，请参阅错误对象中的消息字段";
            case 204: return "目前没有适合您设备的广告（无填充）。MAX 没有从任何中介网络返回此应用/设备的合格广告。";
            case -5001: return "由于网络无法填充，广告无法加载。MAX 从中介网络返回了符合条件的广告，但所有广告均无法加载。有关更多详细信息，请参阅错误对象中的 adLoadFailureInfo 字段。";
            case -1000: return "由于一般网络错误，广告请求失败。有关更多详细信息，请参阅错误对象中的消息字段。";
            case -1001: return "由于互联网连接速度较慢，广告请求超时。";
            case -1009: return "由于设备未连接到互联网，广告请求失败。";
            case -23: return "您试图展示一个全屏广告（插页式广告或激励广告），而另一个全屏广告仍在展示。";
            case -24: return "您正尝试在加载之前显示全屏广告。";
            case -5201: return "SDK内部状态无效。发生这种情况的方式有很多种。";
            case -5205: return "适配器在尝试显示时尚未准备好广告。";
            case -5209: return "未指定的内部错误。";
            case -5601: return "SDK 无法加载广告，因为它找不到顶部的 Activity。";
            case 5602: return "SDK 无法展示广告，因为用户启用了“不保留活动”开发者设置。";
            default: return "未知错误："+code;
        }
    }

}
