package com.github.iaa.applovin;

import android.os.Bundle;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAdRevenue;
import com.adjust.sdk.AdjustEvent;
import com.applovin.mediation.MaxAd;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MaxEvent {

    public static void logRevenueEvent(MaxAd maxAd){
        AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue(MaxConstant.MAX_SOURCE_NAME);
        adjustAdRevenue.setRevenue(maxAd.getRevenue(), "USD");
        adjustAdRevenue.setAdRevenueNetwork(maxAd.getNetworkName());
        adjustAdRevenue.setAdRevenueUnit(maxAd.getAdUnitId());
        adjustAdRevenue.setAdRevenuePlacement(maxAd.getPlacement());
        Adjust.trackAdRevenue(adjustAdRevenue);

        Bundle bundle = new Bundle();
        bundle.putDouble("revenue",maxAd.getRevenue());
        bundle.putString("networkName",maxAd.getNetworkName()+"");
        bundle.putString("adUnitId",maxAd.getAdUnitId()+"");
        FirebaseAnalytics.getInstance(MaxManager.getApplication()).logEvent("ad_impression",bundle);
    }

    public static void logEvent(String key){
        // 咱们也定义一个事件来记录收入
//        AdjustEvent event = new AdjustEvent(key);
//        event.setRevenue(revenueAmt, currency);
//        Adjust.trackEvent(event);

        FirebaseAnalytics.getInstance(MaxManager.getApplication()).logEvent(key,new Bundle());
    }

}
