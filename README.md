# Argyle Android SDK
![jitpack](https://maven-badges.herokuapp.com/maven-central/com.argyle/argyle-link-android/badge.svg)

The Android Link SDK provides a quick and easy way to integrate [Argyle Link](https://argyle.io/docs/argyle-link) into your Android app.

For detailed instructions on how to setup your Android integration, navigate to the :blue_book: [Integration guide](https://docs.argyle.com/guides/docs/android) or checkout the :file_folder: [Link Reference](https://docs.argyle.com/guides/reference/link-reference-overview).

If you are looking to update Argyle Link to the newest version, navigate to the [upgrade guide](https://github.com/argyle-systems/argyle-link-android/blob/master/UPGRADING.md).


# Table of contents
- [Requirements](#requirements)
- [Adding it to your project](#installation)
    - [Installation](#install)
    - [Configure and Start](#configure)
- [User Tokens](#usertokens)

## Requirements <a name="requirements"></a>

- Android 6.0 (API Level 23) and above
- Kotlin `1.6.21+`
- Android Gradle Plugin `7.1.3+`
- Gradle `7.2+`

## Adding it to your project <a name="installation"></a>
Our SDK can be integrated into your application in 2 simple steps.

**Note:** starting with `4.7.3` we dropped JitPack as means of distribution in favor of [Maven Central](https://central.sonatype.dev/).

### Installation  <a name="install"></a>
Add `argyle-plugin-android-source` to your `build.gradle` dependencies.
```  
dependencies {  
    implementation 'com.argyle:argyle-plugin-android-source:4.x.x'
}  
```  
**Important:** When using tools like Proguard to obfuscate your code, to avoid unexpected runtime issues please make sure to exclude Android Link SDK package (`com.argyle.*`) from the process. You can do this by adding this line to your `proguard-rules.pro` file.
```  
-keep class com.argyle. { *; }  
```  

### Configure and Start  <a name="configure"></a>

1. Log into your [Console](https://console.argyle.com/api-keys) instance
2. Navigate to the [API Keys](https://console.argyle.com/api-keys) area under the Developer menu
3. Copy your Sandbox or Production Link API Key for use in the next step
4. [Initialise the SDK](https://github.com/argyle-systems/argyle-link-android/blob/e8e507d7169e1226804b3b744761f67c3d89f28d/app/src/main/java/com/argyleexample/MainActivity.kt#L57) with your Link API Key and then call the [startSdk](https://github.com/argyle-systems/argyle-link-android/blob/e8e507d7169e1226804b3b744761f67c3d89f28d/app/src/main/java/com/argyleexample/MainActivity.kt#L58) method.

For a more detailed look at how to integrate Link, please review the  [Example App](https://github.com/argyle-systems/argyle-link-android/blob/master/app/src/main/java/com/argyleexample/MainActivity.kt).

## User Tokens <a name="usertokens"></a>
For successful implementation you need to make sure to utilize user tokens correctly. Learn how to do it in Argyle [returning user experience guide](https://argyle.com/docs/products/returning-users-experience) before continuing onto the next step.  
