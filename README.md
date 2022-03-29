# Argyle Android SDK example project
Android Link SDK provides a way to integrate [Argyle Link](https://argyle.io/docs/argyle-link) into your Android app.

If you are looking to update Argyle Link to the newest version, navigate to [upgrade guide](https://github.com/argyle-systems/argyle-link-android/blob/master/Migration.md).

**The SDK supports API level 23 and above ([distribution stats](https://developer.android.com/about/dashboards)).**

**Note:** We recommend you to lock your app to portrait orientation.

---
- **Important:** When using tools like Proguard to obfuscate your code, make sure to exclude Android Link SDK package (`com.argyle.*`) from the process, it may cause unexpected runtime issues otherwise. You can do this by adding this line to your `proguard-rules.pro:-keep class com.argyle. { *; }`
  
Our target configuration is currently set to the following:

- `minSdkVersion = 23`
- `compileSdkVersion = 30`
- `targetSdkVersion = 30`
- `Android Support Library = 28.0.0`
- `Kotlin = 1.4.32`

### 1. Adding the SDK dependency
```
dependencies {
    implementation 'com.argyle:argyle-plugin-android-source:3.x.x'
}
```

### 2. Creating the SDK configuration
```kotlin
val config = ArgyleConfig.Builder()
    .loginWith("YOUR_LINK_KEY", "https://api-sandbox.argyle.com/v1", "USER_TOKEN")
    //.linkItems(arrayOf("lyft", "uber"))
    .setCallbackListener(object : Argyle.ArgyleResultListener {

        override fun onUserCreated(userToken: String, userId: String) {
          Log.d("Result", "onUserCreated:  userId: $userId, userToken: $userToken")
        }
    
        override fun onAccountCreated(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onAccountCreated: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onAccountConnected(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onAccountConnected: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onAccountUpdated(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onAccountUpdated: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onAccountRemoved(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onAccountRemoved: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onAccountError(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onAccountError: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onPayDistributionSuccess(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onPayDistributionSuccess: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }
    
        override fun onPayDistributionError(accountId: String, userId: String, linkItemId: String) {
          Log.d("Result", "onPayDistributionError: accountId: $accountId, userId: $userId, linkItemId: $linkItemId")
        }  
    
        override fun onError(error: ArgyleErrorType) {
          Log.d("Result", "onError: error: $error")
        }
    
        override fun onTokenExpired(handler: (String) -> Unit) {
          handler("new_token")
        }
        
        override fun onUIEvent(name: String, properties: Map<String, Any>) {
          Log.d("Result", "onUIEvent: $name, properties: $properties")
        }
    
        override fun onClose() {
          Log.d("Result", "onClose")
        }
    })
    .build()
```

### 3. Starting the flow
```kotlin
Argyle.instance.init(config);
Argyle.instance.startSDK(this)
```

#### Closing  Link programmatically

Normally, Link is closed by the user but it can also be closed by calling `Argyle.instance.close()`