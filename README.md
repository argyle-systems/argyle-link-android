# Argyle Android SDK

## Table of contents

* [Overview](#overview)
* [Getting started](#getting-started)
* [Customising SDK](#customising-sdk)

## Overview

This SDK provides a drop-in set of screens and tools for Android applications Argyle lets your customers share with you their 0-specific data from the workforce platforms, such as Uber, Postmates, Doordash and others. 

It works in the following way:

- Customer logs in to their workforce accounts on your app.
- Behind the scenes, Argyle pulls data across the platforms.
- You can use Argyle's API to retrieve customer's earnings, length of work, ratings and other data.

## Important note

We recommend you to lock your app to a portrait orientation.

## Getting started


The SDK supports API level 18 and above ([distribution stats](https://developer.android.com/about/dashboards/index.html)).

Our configuration is currently set to the following:

- `minSdkVersion = 19`
- `compileSdkVersion = 28`
- `targetSdkVersion = 28`
- `Android Support Library = 28.0.0`
- `Kotlin = 1.3+`

### 1. Adding the SDK dependency

Starting on version `1.1.10` you can integrate it:


```app-gradle
dependencies {
    implementation 'com.argyle:app:1.1.10'
}
```

### 2. Creating the SDK configuration

Create an `ArgyleConfig` using your pluginKey, along with the apiHost, or token, token parameter is optional.

Kotlin

``` kotlin
    val config = ArgyleConfig.Builder()
                    .loginWith("pluginKey","apiHost", "token") //token required just in JAVA. 
                    .dataPartners(arrayOf("uber", "deliv")) // Your selected data partners.
                    .setCallbackListener(object : Argyle.ArgyleResultListener {
                        override fun onTokenExpired(handler: (String) -> Unit) {
                            val token = "token" //Get new token
                            handler(token)
                        }

                        override fun onAccountConnected(accountId: String, userId: String) {
                            Log.d("Result", "onAccountConnected: accountId: $accountId userId: $userId")
                        }

                        override fun onAccountRemoved(accountId: String, userId: String) {
                            Log.d("Result", "onAccountRemoved: accountId: $accountId userId: $userId")
                        }

                        override fun onError(error: ArgyleErrorType) {
                            super.onError(error)
                            Log.d("Result", "onError: error: $error")
                        }

                        override fun onUserCreated(userToken: String, userId: String) {
                            Log.d("Result", "onUserCreated:  userId: $userId userToken: $userToken")
                        }
                    })
                    .build()
  
                    
```

### 3. Starting the flow


Kotlin

``` kotlin
// start the flow.
        Argyle.instance.init(config);
        Argyle.instance.startSDK(this)
```


Congratulations! You have successfully started the flow.
