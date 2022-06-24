package com.argyleexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.argyle.Argyle
import com.argyle.ArgyleConfig
import com.argyle.ArgyleErrorType
import com.argyleexample.databinding.MainActivityBinding

private const val LINK_KEY = "[YOUR LINK KEY]"
private const val SANDBOX_API_HOST = "https://api-sandbox.argyle.com/v1/"
private const val PRODUCTION_API_HOST = "https://api.argyle.com/v1/"

//  https://docs.argyle.com/guides/docs/pay-distribution-overview
private const val YOUR_PD_CONFIG = "YOUR_PD_CONFIG"

private const val PREF_USER_TOKEN = "userToken"
private const val PREF_USER_ID = "userId"

class MainActivity : AppCompatActivity(), Argyle.ArgyleResultListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newWorkerButton.setOnClickListener {
            startLink(returningUserToken = null)
        }

        binding.existingButton.setOnClickListener {
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val token = preferences.getString(PREF_USER_TOKEN, null)

            if (token == null) {
                Toast.makeText(this@MainActivity, "Create a user first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            startLink(returningUserToken = token)
        }
    }

    private fun startLink(returningUserToken: String?) {
        val linkConfig = ArgyleConfig.Builder().loginWith(LINK_KEY, SANDBOX_API_HOST)
            .setCallbackListener(this).build()

        returningUserToken?.let {
            linkConfig.userToken = it
        } ?: run { linkConfig.userToken = null }

        Argyle.instance.init(linkConfig)
        Argyle.instance.startSDK(this)
    }

    override fun onTokenExpired(handler: (String) -> Unit) {
        val token = "token"
        handler(token)
    }

    override fun onAccountCreated(accountId: String, userId: String, linkItemId: String) {
        Toast.makeText(this@MainActivity, "onAccountCreated", Toast.LENGTH_SHORT).show()
    }

    override fun onAccountConnected(accountId: String, userId: String, linkItemId: String) {
        Toast.makeText(this@MainActivity, "onAccountConnected", Toast.LENGTH_SHORT).show()
    }

    override fun onAccountUpdated(accountId: String, userId: String, linkItemId: String) {
        Toast.makeText(this@MainActivity, "onAccountUpdated", Toast.LENGTH_SHORT).show()
    }

    override fun onAccountRemoved(accountId: String, userId: String, linkItemId: String) {
        Toast.makeText(this@MainActivity, "onAccountRemoved", Toast.LENGTH_SHORT).show()
    }

    override fun onAccountError(accountId: String, userId: String, linkItemId: String) {
        Toast.makeText(this@MainActivity, "onAccountError", Toast.LENGTH_SHORT).show()
    }

    override fun onError(error: ArgyleErrorType) {
        Toast.makeText(this@MainActivity, "onError", Toast.LENGTH_SHORT).show()
    }

    override fun onUserCreated(userToken: String, userId: String) {
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
            putString(PREF_USER_TOKEN, userToken)
            putString(PREF_USER_ID, userId)
            apply()
            Toast.makeText(this@MainActivity, "onUserCreated", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClose() {
        Toast.makeText(this@MainActivity, "onClose", Toast.LENGTH_SHORT).show()
    }

    override fun onPayDistributionError(
        accountId: String,
        userId: String,
        linkItemId: String
    ) {
        Toast.makeText(this@MainActivity, "onPayDistributionError", Toast.LENGTH_SHORT).show()
    }

    override fun onPayDistributionSuccess(
        accountId: String,
        userId: String,
        linkItemId: String
    ) {
        Toast.makeText(this@MainActivity, "onPayDistributionSuccess", Toast.LENGTH_SHORT).show()
    }

    override fun onUIEvent(name: String, properties: Map<String, Any>) {
        Toast.makeText(this@MainActivity, "onUIEvent", Toast.LENGTH_SHORT).show()
    }

}
