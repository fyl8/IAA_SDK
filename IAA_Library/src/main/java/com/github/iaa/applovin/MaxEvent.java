package com.github.iaa.applovin;

import android.content.Context;
import android.os.Bundle;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAdRevenue;
import com.adjust.sdk.AdjustEvent;
import com.applovin.mediation.MaxAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Map;

public class MaxEvent {

    public static void logRevenueEvent(Context context, MaxAd maxAd){
        if(MaxManager.isInitAdjust){
            AdjustAdRevenue adjustAdRevenue = new AdjustAdRevenue(MaxConstant.MAX_SOURCE_NAME);
            adjustAdRevenue.setRevenue(maxAd.getRevenue(), "USD");
            adjustAdRevenue.setAdRevenueNetwork(maxAd.getNetworkName());
            adjustAdRevenue.setAdRevenueUnit(maxAd.getAdUnitId());
            adjustAdRevenue.setAdRevenuePlacement(maxAd.getPlacement());
            Adjust.trackAdRevenue(adjustAdRevenue);
        }
        Bundle bundle = new Bundle();
        bundle.putDouble("revenue",maxAd.getRevenue());
        bundle.putString("networkName",maxAd.getNetworkName()+"");
        bundle.putString("adUnitId",maxAd.getAdUnitId()+"");
        FirebaseAnalytics.getInstance(context).logEvent("ad_impression",bundle);
    }

    public static void logEventADorFA(Context context,String ad_key,String fa_name){
        AdjustEvent event = new AdjustEvent(ad_key);
        Adjust.trackEvent(event);
        FirebaseAnalytics.getInstance(context).logEvent(fa_name,new Bundle());
    }

    public static void logEventADorFA(Context context, String ad_key,String fa_key, Map<String,String> maps){
        Bundle bundle = new Bundle();
        AdjustEvent event = new AdjustEvent(ad_key);
        for (String key : maps.keySet()) {
            bundle.putString(key,String.valueOf(maps.get(key)));
            event.addCallbackParameter(key,String.valueOf(maps.get(key)));
        }
        Adjust.trackEvent(event);
        FirebaseAnalytics.getInstance(context).logEvent(fa_key,new Bundle());
    }


}
