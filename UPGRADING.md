# Android SDK version upgrade guide

## Learn how to upgrade to the newest Link version.
If you are looking to integrate Argyle Link for the first time navigate to [integration guide](https://github.com/argyle-systems/argyle-link-android#readme).

- [Android SDK upgrade guide](#android-sdk-upgrade-guide)
- [Migrating to Link 4](#migrating-to-link-4)

---
## Android SDK upgrade guide
### Review the changes in the latest version
Before you upgrade to a new version of the Link SDK, you should review the [Releases page](https://github.com/argyle-systems/argyle-link-android/releases). This will help you to understand any changes that you will need to make in your code as the result of an upgrade.

We strongly recommend upgrading the SDK as soon as there is a new version available. Doing so will help you to provide the best Argyle Link experience in your application.

---
### Upgrade to a new SDK version
**The minimum supported Android version is Android 6.0 (API level 23).**

- In your app module gradle file (typically found at `app/build.gradle`) locate the Argyle dependency within your dependencies block and increment the version number according to the latest version number displayed in the [Releases page](https://github.com/argyle-systems/argyle-link-android/releases).

```
implementation 'com.argyle:argyle-plugin-android-source:<insert latest version number>'
```

- To verify the change initiate a gradle sync and confirm no errors.

- If you are using pro-guard, make sure you have the most up-to-date Proguard rules which can be found in our [integration guide](https://github.com/argyle-systems/argyle-link-android#readme).

---
# Migrating to Link 4

If you're using a previous version of Link and would like to migrate to the new version, please  review updates described below, make necessary changes and then follow the usual [Android Link SDK version upgrade instruction](#upgrade-to-a-new-sdk-version) defined above.

---
### Updated configuration attributes

Please find below a list of all configuration attributes that have been removed or changed.

---

**introSearchPlaceholder: removed**

Became redundant due to [Intro screen](https://argyle.com/docs/products/link-4#intro-screen) redesign.

---
**searchScreenTitle: removed**

Became redundant due to [Search screen](https://argyle.com/docs/products/link-4#search-screen) redesign.

---
**searchScreenSubtitle: removed**

Became redundant due to [Search screen](https://argyle.com/docs/products/link-4#search-screen) redesign.

---
**dataPartners: removed**

The property has been deprecated since introduction of Link 3 in favor of `linkItems`.

---

### Updated error code

[Link Initialization error](https://argyle.com/docs/developer-tools/link-initialization-errors#) **invalid_plugin_key: renamed**

The error was renamed to `invalid_link_key`.

---
