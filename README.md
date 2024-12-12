## IAASDK 集成

### Version:1.0.1

#### 1.添加依赖


    implementation 'com.github.fyl8:IAA_SDK:+'



#### 2.app 级别 gradle 下增加


    plugins {
      alias(libs.plugins.android.application)
      id 'com.google.gms.google-services'
    }


#### 3.settings.gradle文件里面增加


    repositories {
      google()
      mavenCentral()
      maven { url 'https://jitpack.io' }
      maven { url 'https://artifacts.applovin.com/android' }
      maven { url "https://cboost.jfrog.io/artifactory/chartboost-ads/" }
      maven { url "https://android-sdk.is.com" }
      maven { url "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea" }
      maven { url "https://artifact.bytedance.com/repository/pangle" }
    }


#### 4.在你的Application初始化


    public class TestApplication extends Application {
      @Override
      public void onCreate() {
          super.onCreate();
          MaxManager.getInstance(this)
                .setEnvironment(true)//设置是否是测试环境，false:关闭测试环境
                .setEnabledLog(true)//设置是否输出日志，false：关闭日志
                .initAdjust(“app_token”)//初始化Adjust，app_token找运营要
                .initializedNewMax(“sdk_key”, sdk_key找运营要
                    Log.e("IAA","初始化成功");
                });
      }
    }  


#### 5.加载激励视频广告


    MaxAds.loadMaxRewardedAd(activity,MaxConstant.MAX_REWARD_AD_ID,new MaxRewardedAdsListener() {
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

 MaxAds.loadMaxRewardedAd(activity,reward_id,MaxRewardedAdsListener)//此激励广告的收入埋点事件已经在SDK内集成，不需要再次统计

 Reward_id: 激励视频广告ID（找运营人员要）

 MaxRewardedAdsListener：回调监听

 SDK内已集成其它类型广告的SDK，请自行参考MAX官方文档：

 https://developers.applovin.com/en/max/android/ad-formats/banner-and-mrec-ads/
 

#### 6.开启调试器

    //开启中介调试器,用来测试广告集成是否成功
    MaxAds.startDebugger(activity);

        
#### 7.事件埋点

    //记录广告收入的事件方法，可在max广告的MaxRewardedAd.setRevenueListener()方法里面记录。
    MaxEvent.logRevenueEvent(Context context, MaxAd maxAd)
    例：
    rewardedAd.setRevenueListener(maxAd -> {
        MaxEvent.logRevenueEvent(activity,maxAd);
    });
    
    maxAd：max广告回调自带的参数


    //普通事件埋点统计
    MaxEvent.logEventADorFA(Context context,String ad_name,String fa_name)
    ad_name:Adjust：事件识别码，需要找运营人员索要
    fa_name:Firebase：自定义事件名称
    例：
    MaxEvent.logEventADorFA(activity,"adioxqs","ad_click");



    //带参数的事件埋点方法
    MaxEvent.logEventADorFA(Context context, String ad_name,String fa_name, Map<String,String> maps)
    ad_name:Adjust：事件识别码，需要找运营人员索要
    fa_name:Firebase：自定义事件名称
    Maps：事件所需要携带的参数
    例：
    Map<String,String> maps = new HashMap<>();
    maps.put("click_time",String.valueOf(System.currentTimeMillis()));
    maps.put("click_number","2");
    maps.put("click_id","1087901");
    MaxEvent.logEventADorFA(activity,"adioxqs","ad_click",maps);





