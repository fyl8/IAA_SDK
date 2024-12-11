package com.github.iaa.applovin;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxReward;

public interface MaxRewardedAdsListener {
    void onAdLoadShow(MaxAd maxAd);
    void onAdHidden(MaxAd maxAd);
    void onUserRewarded(MaxAd maxAd, MaxReward reward);
    void onAdClicked(MaxAd maxAd);
    void onAdLoadFailed(String s);
}
