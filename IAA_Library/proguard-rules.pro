-dontshrink
-keep class com.adjust.sdk.** { *; }
-keep class com.github.iaa.applovin.** { *; }
-keep class com.google.android.gms.common.ConnectionResult {
   int SUCCESS;
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {
   com.google.android.gms.ads.identifier.AdvertisingIdClient$Info getAdvertisingIdInfo(android.content.Context);
}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {
   java.lang.String getId();
   boolean isLimitAdTrackingEnabled();
}
-keep public class com.android.installreferrer.** { *; }
-keep public class com.adjust.sdk.** { *; }

-keep class com.bytedance.sdk.** { *; }
-keep class com.chartboost.** { *; }

##inmobi
-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
-keep class com.moat.** {*;}
-dontwarn com.moat.**
-keep class com.integralads.avid.library.** {*;}
-keep class androidx.browser.customtabs.CustomTabsService { *; }
-keep class androidx.recyclerview.widget.RecyclerView { *; }

##IronSource
-keepclassmembers class com.ironsource.sdk.controller.IronSourceWebView$JSInterface {
    public *;
}
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}
-keep class com.ironsource.adapters.** { *;
}
-dontwarn com.ironsource.mediationsdk.**
-dontwarn com.ironsource.adapters.**
-dontwarn com.moat.**
-keep class com.moat.** { public protected private *; }
-keep class com.ironsource.mediationsdk.** { *; }

##unity ads
-keepattributes SourceFile,LineNumberTable
-keepattributes JavascriptInterface
-keep class android.webkit.JavascriptInterface {
   *;
}
-keep class com.unity3d.ads.** {
   *;
}
-keep class com.unity3d.services.** {
   *;
}
-dontwarn com.google.ar.core.**

