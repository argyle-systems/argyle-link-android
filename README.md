# Argyle Android SDK

![jitpack](https://maven-badges.herokuapp.com/maven-central/com.argyle/argyle-link-android/badge.svg)

Argyle’s Android Link SDK provides a way to integrate [Link](https://argyle.com/docs/link/overview) into your Android app.

**Requirements:**

```
android {
    compileSdk 34
    defaultConfig {
        minSdk 26
        ...
    }
    ...
}
```

## Installing the SDK
1. Add the following line within the dependencies of your app `build.gradle` [configuration file](https://developer.android.com/build/dependencies#add-dependencies-without-catalogs):
```groovy
dependencies {  
    implementation 'com.argyle:argyle-link-android:5.+'
}
```
2. [Sync your Android project](https://developer.android.com/build#sync-files) to import the build configuration changes

## Implementing Link


1. Log-in to Console and retrieve a copy of your [Link key](https://console.argyle.com/link-key)
2. Create a user token:
- **New users**
    1. Create a new user by sending a **POST** to the [users endpoint](https://docs.argyle.com/guides/reference/create-a-user) of the Argyle API
    2. The response payload will include an `id` and `user_token`
    3. Save the `id` for quickly creating user tokens for this user in the future
    4. Initialize Link by passing the `user_token` as the value for the `userToken` parameter
- **Returning users**
    1. Send a **POST** request to the [user-tokens endpoint](https://docs.argyle.com/guides/reference/create-a-user-token) of the Argyle API
        - Include the `id` of the user in the request body as a JSON object in the format `{"user": "<id>"}`
    2. A `user_token` will be included in the response payload
    3. Initialize Link by passing the `user_token` as the value for the `userToken` parameter
1. Initialize Link using the Link key and user token. 

<aside>
ℹ️ Make sure the Link key matches the environment of the `sandbox` parameter.

</aside>

Example Link initialization for Android:

```kotlin
val config = LinkConfig(
    linkKey = "YOUR_LINK_KEY",
    userToken = "USER_TOKEN",
    sandbox = true // Set to false for production environment.
)
// (Optional) Limit Link search to these Items:
config.items = listOf("item_000001422", "item_000025742")
// (Optional) Callback examples:
config.onAccountConnected = { data ->
    Log.d("Result", "onAccountConnected $data")
}
config.onAccountError = { data ->
    Log.d("Result", "onAccountError $data")
}
config.onDDSSuccess = { data ->
    Log.d("Result", "onDDSSuccess $data")
}
config.onDDSError = { data ->
    Log.d("Result", "onDDSError $data")
}
config.onTokenExpired = { handler ->
    // generate your new token here
    // handler(newToken)
}

ArgyleLink.start(context, config)
```
