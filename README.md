# Argyle Android SDK example project
Android Link SDK provides a way to integrate [Argyle Link](https://argyle.io/docs/argyle-link) into your Android app.

If you are looking to update Argyle Link to the newest version, navigate to [upgrade guide](https://github.com/argyle-systems/argyle-link-android/blob/master/UPGRADING.md).

**The SDK supports API level 23 and above ([distribution stats](https://developer.android.com/about/dashboards)).**

**Note:** We recommend you to lock your app to portrait orientation.

---
- **Important:** When using tools like Proguard to obfuscate your code, make sure to exclude Android Link SDK package (`com.argyle.*`) from the process, it may cause unexpected runtime issues otherwise. You can do this by adding this line to your `proguard-rules.pro:-keep class com.argyle. { *; }`
  
Our target configuration is currently set to the following:

- `minSdkVersion = 23`
- `Kotlin = 1.4.32`

## 1. Add the SDK dependency
```
dependencies {
    implementation 'com.argyle:argyle-plugin-android-source:4.x.x'
}
```

## 2. Configure and integrate Link
### 1. Access your Link API Key
1. Log into your [Console](https://console.argyle.com/api-keys) instance
2. Navigate to the [API Keys](https://console.argyle.com/api-keys) area under the Developer menu
3. Copy your Sandbox or Production Link API Key for use in the next step

### 2. Utilize user tokens
For successful implementation you need to make sure to utilize user tokens correctly. Learn how to do it in Argyle [returning user experience guide](https://argyle.com/docs/products/returning-users-experience#suggested-flow-for-user-token-usage) before continuing onto the next step.

### 3. Integrate Link
See [Example](https://github.com/argyle-systems/argyle-link-android/blob/master/app/src/main/java/com/argyleexample/MainActivity.kt) for sample implementation.
