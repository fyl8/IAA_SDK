package io.mini.play.iaa_sdk;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxReward;
import com.github.iaa.Test;
import com.github.iaa.applovin.MaxManager;
import com.github.iaa.applovin.MaxRewardedAdsListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Test.logTest("12345");
        findViewById(R.id.btn_load).setOnClickListener((v)->{
            MaxManager.loadMaxRewardedAd(this, new MaxRewardedAdsListener() {
                @Override
                public void onAdLoadShow(MaxAd maxAd) {

                }
                @Override
                public void onAdHidden(MaxAd maxAd) {

                }
                @Override
                public void onUserRewarded(MaxAd maxAd, MaxReward reward) {

                }
                @Override
                public void onAdClicked(MaxAd maxAd) {

                }
                @Override
                public void onAdLoadFailed(String s) {

                }
            });
        });

    }

}