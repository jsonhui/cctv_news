# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\java\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html
# Add any project specific keep options here:
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#基本不混淆---begin---
-injars      libs   #配置当前需要导入的Jar文件路径
-outjars     bin/classes-processed.jar #jar文件的输出文件名称
-dontpreverify #不进行预先校验
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation* #保证使用注解注释的方法不被混淆
#保证某一个具体的类不被混淆
#-keep public class 包名.类名
#-keep 表示某些文件不被混淆
-keep class com.cctvnews.www.** { *; }
-keep public class * extends android.support.v4.app.Fragment#保证所有的Fragment的子类不被混淆
-keep public class * extends android.support.v7.app.AppCompatActivity#保证所有的Activity的子类不被混淆
-keep public class * extends android.app.Activity #保证所有的Activity的子类不被混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * implements java.io.Serializable
#保证View的子类的构造方法和所有set方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#基本不混淆---end---
#友盟统计不混淆---begin---
#不混淆构造
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
#不混淆R文件
-keep public class com.cctvnews.www.R$*{
public static final int *;
}
#不混淆枚举
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#友盟统计不混淆---end---
#友盟推送不混淆---begin----
-dontwarn com.ut.mini.**
-dontwarn okio.**
-dontwarn com.xiaomi.**
-dontwarn com.squareup.wire.**
-dontwarn android.support.v4.**

-keepattributes *Annotation*

-keep class android.support.v4.** { *; }
-keep interface android.support.v4.app.** { *; }

-keep class okio.** {*;}
-keep class com.squareup.wire.** {*;}

-keep class com.umeng.message.protobuffer.* {
	 public <fields>;
         public <methods>;
}

-keep class com.umeng.message.* {
	 public <fields>;
         public <methods>;
}

-keep class org.android.agoo.impl.* {
	 public <fields>;
         public <methods>;
}

-keep class org.android.agoo.service.* {*;}

-keep class org.android.spdy.**{*;}

-keep public class **.R$*{
    public static final int *;
}
#如果compileSdkVersion为23，请添加以下混淆代码
-dontwarn org.apache.http.**
-dontwarn android.webkit.**
-keep class org.apache.http.** { *; }
-keep class org.apache.commons.codec.** { *; }
-keep class org.apache.commons.logging.** { *; }
-keep class android.net.compatibility.** { *; }
-keep class android.net.http.** { *; }
#友盟推送不混淆---end---
#pulltofresh不混淆-----begin-------
-dontwarn android.util.**
-keep class android.util.**{*;}
#pulltofresh不混淆-----end-------
#支付宝不混淆-----degin-----
-dontwarn android.net.**
-keep class android.net.SSLCertificateSocketFactory{*;}
-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}
#支付宝不混淆------end---------
#毕加索不混淆------begin--------
-dontwarn com.squareup.okhttp.**
-keep public class com.squareuo.** { *; }