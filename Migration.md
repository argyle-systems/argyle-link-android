# Migrating to Link 4
## Learn how to switch over to the newest Link version.
If you are looking to integrate Argyle Link for the first time navigate to [integration guide](https://github.com/argyle-systems/argyle-link-android#readme).

- [Android SDK upgrade guide](#android-sdk-upgrade-guide)
- [Migrating from Link 2](#migrating-from-link-2)
- [Migrating from Link 3](#migrating-from-link-3)

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
## Migrating from Link 3
### Updated configuration attribute

If you're using a previous version of Link and would like to migrate to the new version, please find below the single changed configuration attribute.

---

**pluginKey: renamed**

The property was renamed to `linkKey`.

---

## Updated error code

A name of the error code returned via `onError` callback is also changed to reflect the update of configuration attribute.

---

[Link Initialization error](https://argyle.com/docs/developer-tools/link-initialization-errors#) **invalid_plugin_key: renamed**

The error was renamed to `invalid_link_key`.

---
## Migrating from Link 2

If you're using Link version 2 and would like to migrate to the newest version, please find below a list of all configuration attributes that have been removed or changed.

### Updated configuration attributes

---
**pluginKey: renamed**

The property was renamed to `linkKey`.

---
**payDistributionConfig and payDistributionUpdateFlow: changed**

Where previously it was enough to set the `payDistributionConfig` to trigger the pay distribution update flow, it is now required that you also explicitly enable the flow by using the `payDistributionUpdateFlow` configuration parameter.

---
**dataPartners: renamed**

The property was renamed to `linkItems`.

---
**addIncomeSourceLinkVisible: removed**

`addIncomeSourceLinkVisible` is no longer available. The "Can't find your company?" button is enabled whenever the `onCantFindLinkItemClicked` callback is defined. The button title can be changed by using the `cantFindLinkItemTitle` attribute.

---
**onAddIncomeSourceLinkClicked: renamed**

`onAddIncomeSourceLinkClicked` callback is no longer available. Instead, please use `onCantFindLinkItemClicked`.

---
**addIncomeSourceExitButtonTitle, addIncomeSourceLinkTitle: removed**

The `addIncomeSourceExitButtonTitle` and `addIncomeSourceLinkTitle` attributes are no longer available. The flow of adding non-covered Link items was removed.

---
**showIntroScreen, introTitle, introSubtitle: removed**

The `showIntroScreen`, `introTitle`, and `introSubtitle` attributes are no longer available. The intro screen has been redesign to immediately show the search bar and thus the intro screen is now shown in most Link integrations. You can customize the search placeholder in the intro screen by using the `introSearchPlaceholder` configuration attribute. The intro screen will be hidden when using the `linkItems` attribute to provide a single Link item ID and deeplink a user straight into the company login screen.

---
**showPlatformsToggle: renamed**

The `showPlatformsToggle` attribute is no longer available. The "Platforms" tab has been redesigned. Please use the `showCategories` parameter to control if the user can see the new All/Employer/Gig/Platform tabs.

---
**Callbacks: onAccountCreated, onAccountConnected, onAccountUpdated, onAccountRemoved: changed**

Callbacks remain the same except that the `dataPartnerId` parameter is now renamed to `linkItemId`.

---
### New configuration attributes


Please find below a list of all new configuration attributes available since Link 3.

---

**linkItems** `array of strings` `optional`

Use this parameter to limit the number of Link items that your users can connect to. Provide an array of [Link item](https://argyle.com/docs/developer-tools/api-reference#companies-and-platforms-link-items) IDs you want Argyle Link to display in the company selection screen. The order in which you list the IDs will define the order in which they are displayed in the UI.

Defaults to `[]`, which shows all available Link items.

If you provide a single Link item ID, the company selection screen will be skipped and the user will be navigated directly to the Link item login screen.

---
**introSearchPlaceholder** `string` `optional`
Placeholder text in the search box in the intro screen of Link.

Defaults to "Find and link your employer".

---
**showCategories** `bool` `optional`

Show/hide category tabs (e.g. All, Employer, Gig, Payroll).

Defaults to `true`.

---
**searchScreenTitle** `string` `optional`

The main title in the search screen.

Defaults to "Search for your income source".

---
**searchScreenSubtitle** `string` `optional`

The subtitle in the search screen.

Defaults to "Find your employer, gig platform, or payroll provider."

---
**cantFindLinkItemTitle** `string` `optional`

The title for the **Can't find your employer?** button when no Link item is available.

Defaults to "Can't find your employer?".

---
**onCantFindLinkItemClicked** `func` `optional`

An optional callback triggered after the **Can't find your employer?** button is clicked.

When clicked, this button redirects the users to a [questionnaire](https://argyle.com/docs/developer-tools/api-reference#user-management-forms) to provide their employer name and associated payroll platform (if available).

After the users submit the questionnaire, there are two possible scenarios for the user:

- If the document upload feature is enabled, the users will be asked to upload their income verification documents. Refer to [Document upload guide](https://argyle.com/docs/products/document-upload) for further information.
- If the document upload feature is disabled, the user will quit the Argyle flow.
  The **Can't find your employer?** button is shown by default. It can be disabled via the [Link Customizer](https://console.argyle.com/customizer).

Link will close by default if this callback is defined and triggered on the SDK.

The callback function will receive an object containing a search query that was entered by the user.

---
**showBackToSearchButton** `bool` `optional`

If set to `false`, hides the back to search button in the success screen.

The default value is `true`.

---
**payDistributionUpdateFlow** `bool` `optional`

If `payDistributionUpdateFlow` is set to `true` and the `payDistributionConfig` parameter is provided, the pay distribution update flow will be initiated right after an account gets connected.

If this is set to `true` but the `payDistributionConfig` is not set, Link will not initialize properly.

Defaults to `false`.

---
**onPayDistributionSuccess** `func` `optional`

A callback function invoked after a successful pay distribution update flow.

The function will be passed an object containing `accountId`, `userId`, and `linkItemId`.

---
**onPayDistributionError** `func` `optional`

A callback function invoked after an error occurs during the pay distribution update flow.

The function will be passed an object containing `accountId`, `userId`, and `linkItemId`.

---

### Updated error code

A name of the error code returned via `onError` callback is also changed to reflect the update of configuration attribute.

---

[Link Initialization error](https://argyle.com/docs/developer-tools/link-initialization-errors#) **invalid_plugin_key: renamed**

The error was renamed to `invalid_link_key`.

---
