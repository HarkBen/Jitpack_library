# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# proguard 4 个功能
# 1.压缩(Shrink):侦测并移除代码中无用的类、字段、方法、和特性(Attribute)。
# 2.优化(OPtimize):对字节码进行优化，移除无用指令。
# 3.混淆(Obfuscate):使用a、b、c、d这样简短而无意义的名称，对类、字段和方法进行重命名。
# 4.预检(Preveirfy): 在java平台上对处理后的代码进行预检。


# 一、公共 混淆规则--------------------------------------------------------------------------------------


# tips:AndroidMainfest中的类不混淆，所以四大组件和Application的子类和Framework层下所有的类默认不会进行混淆。
# 自定义的View默认也不会被混淆；所以像网上贴的很多排除自定义View，或四大组件被混淆的规则在Android Studio中是无需加入的


# 1.指定混淆时采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不改变
-optimizations !code/simplification/artithmetic,!field/*,!class/merging/*

# 2.代码混淆压缩比，在0~7之间，默认为5,一般不下需要修改
-optimizationpasses 5

# 3.混淆时不使用大小写混合，混淆后的类名为小写
# windows下的同学还是加入这个选项吧(windows大小写不敏感)
-dontusemixedcaseclassnames

# 4.指定不去忽略非公共的库的类 默认跳过，有些情况下编写的代码与类库中的类在同一个包下，并且持有包中内容的引用，此时就需要加入此条声明
-dontskipnonpubliclibraryclasses

# 5.指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

# 6.不做预检验，preverify是proguard的四个步骤之一  Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

# 7.保护代码中的Annotation不被混淆
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*

# 8.避免混淆泛型
# 这在JSON实体映射时非常重要，比如fastJson
-keepattributes Signature

# 9.抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# 10.对R文件下的所有类及其方法，都不能被混淆
-keepclassmembers class **.R$* {
    *;
}

# 11.对于带有回调函数onXXEvent的，不能混淆
-keepclassmembers class * {
    void *(**On*Event);
}

# 12.保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 13.使用enum类型时需要注意避免以下两个方法混淆，因为enum类的特殊性，以下两个方法会被反射调用，见第二条规则。
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 14.Parcelable的子类和Creator静态成员变量不混淆，否则会产生Android.os.BadParcelableException异常
-keep class * implements Android.os.Parcelable{
    public static final Android.os.Parcelable$Creator *;
}

# 15.针对个别官方特殊库的保存 比如supprotV4
-dontwarn android.support.v4.**
-dontwarn **CompatHoneycomb
-dontwarn **CompatHoneycombMR2
-dontwarn **CompatCreatorHoneycombMR2
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

# 16.都使用了retrofit 以及 okhttp RxJava RxAndroid
-dontwarn javax.annotation.**
-dontwarn javax.inject.**

# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* { long producerIndex; long consumerIndex; }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef { rx.internal.util.atomic.LinkedQueueNode producerNode; }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef { rx.internal.util.atomic.LinkedQueueNode consumerNode; }

# 17.都是用了 greenDao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao { public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

# 18. 忽略 LambdaMetafactory
-dontwarn java.lang.invoke.**









