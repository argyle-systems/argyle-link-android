package com.argyleexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.argyle.ArgyleLink
import com.argyle.LinkConfig
import com.argyleexample.databinding.MainActivityBinding

// TODO: Replace with real values
private const val USER_TOKEN = "YOUR_USER_TOKEN" // Should be fetched and provided by your own backend API https://docs.argyle.com/guides/reference/users
private const val SANDBOX = true

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            startLink()
        }
    }

    private fun startLink() {
        val linkConfig = LinkConfig(
            sandbox = SANDBOX,
            userToken = USER_TOKEN
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
            onFormSubmitted = { toast("onFormSubmitted") }
            onUIEvent = { toast("onUIEvent") }
        }
        ArgyleLink.start(this, linkConfig)
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
