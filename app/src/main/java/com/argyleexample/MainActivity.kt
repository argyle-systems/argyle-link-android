package com.argyleexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.argyle.ArgyleLink
import com.argyle.LinkConfig
import com.argyleexample.databinding.MainActivityBinding
import com.argyleexample.network.LinkService
import com.argyleexample.network.request.UserTokenRequest
import kotlinx.coroutines.launch

private const val PREF_USER_ID = "userId"

class MainActivity : AppCompatActivity() {

    private val linkService = LinkService.create()
    private val preferences by lazy { PreferenceManager.getDefaultSharedPreferences(this) }
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newUserFlowButton.setOnClickListener {
            createNewUserAndStartLink()
        }

        binding.existingUserFlowButton.setOnClickListener {
            val userId = preferences.getString(PREF_USER_ID, null)
            if (userId == null) {
                toast("Create a user first")
                return@setOnClickListener
            }
            createUserTokenAndStartLink(userId)
        }
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun createNewUserAndStartLink() {
        lifecycleScope.launch {
            val user = linkService.createUser()
            preferences.edit().putString(PREF_USER_ID, user.id).apply()
            startLink(user.token)
        }
    }

    private fun createUserTokenAndStartLink(userId: String) {
        lifecycleScope.launch {
            val token = linkService.createUserToken(UserTokenRequest(userId)).access
            startLink(token)
        }
    }

    private fun startLink(userToken: String) {
        val linkConfig = LinkConfig(
            sandbox = LinkConstants.SANDBOX,
            linkKey = LinkConstants.LINK_KEY,
            userToken = userToken
        ).apply {
            // accountId = "USER_ACCOUNT_ID" // Specify to take the user directly to the account
            // customizationId = "00000000"  // Specify to use customization https://docs.argyle.com/guides/docs/customize
            // ddsConfig = "YOUR_DDS_CONFIG" // Specify to use direct deposit switching https://docs.argyle.com/guides/docs/direct-deposit-switching
            // items = listOf("item_000001000", "item_000001022")  // Specify to limit search to the specified items only

            onTokenExpired = { handler ->
                val newToken = "YOUR_NEW_TOKEN"
                handler(newToken)
            }
            onAccountCreated = { toast("onAccountCreated") }
            onAccountConnected = { toast("onAccountConnected") }
            onAccountRemoved = { toast("onAccountRemoved") }
            onAccountError = { toast("onAccountError") }
            onCantFindItemClicked = { toast("onCantFindItemClicked") }
            onClose = { toast("onClose") }
            onDDSError = { toast("onDDSError") }
            onDDSSuccess = { toast("onDDSSuccess") }
            onDocumentsSubmitted = { toast("onDocumentsSubmitted") }
            onError = { toast("onError") }
            onExitIntroClicked = { toast("onExitIntroClicked") }
            onFormSubmitted = { toast("onFormSubmitted") }
            onUIEvent = { toast("onUIEvent") }
        }
        ArgyleLink.start(this, linkConfig)
    }
}
