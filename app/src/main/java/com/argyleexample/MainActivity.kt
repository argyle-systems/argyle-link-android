package com.argyleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.argyle.ArgyleConfig

import kotlinx.android.synthetic.main.main_activity.*
import android.preference.PreferenceManager
import com.argyle.Argyle
import com.argyle.ArgyleErrorType


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        newWorkerButton?.setOnClickListener {
            openSdk()
        }

        existingButton?.setOnClickListener {
            val argyle = Argyle.instance
            val preferences = PreferenceManager.getDefaultSharedPreferences(this)
            val token = preferences.getString("workerToken", "")
            val pluginKey = "646dc138-5942-4eb6-a9ca-dd01b6d57ae9"
            val apiHost = "https://api-sandbox.develop.argyle.io/v1/"
            if (!token.equals("", ignoreCase = true)) {
                val config = ArgyleConfig.Builder()
                    //.dataPartners(arrayOf("uber", "deliv"))
                    .loginWith(pluginKey, apiHost, token)
                    .setCallbackListener(object : Argyle.ArgyleResultListener {
                        override fun onTokenExpired(handler: (String) -> Unit) {
                            val token = "token"
                            handler(token)

                        }

                        override fun onAccountConnected(accountId: String, workerId: String) {
                            Log.d("Result", "onAccountConnected: accountId: $accountId workerId: $workerId")
                        }

                        override fun onAccountRemoved(accountId: String, workerId: String) {
                            Log.d("Result", "onAccountRemoved: accountId: $accountId workerId: $workerId")
                        }

                        override fun onError(error: ArgyleErrorType) {
                            super.onError(error)
                            Log.d("Result", "onError: error: $error")
                        }

                        override fun onWorkerCreated(workerToken: String, workerId: String) {
                            Log.d("Result", "onWorkerCreated:  workerId: $workerId workerToken: $workerToken")
                        }
                    })
                    .build()
                argyle.init(config)
            } else {
                val config = ArgyleConfig.Builder()
                    //.dataPartners(arrayOf("uber", "deliv"))
                    .loginWith(pluginKey, apiHost)
                    .setCallbackListener(object : Argyle.ArgyleResultListener {
                        override fun onTokenExpired(handler: (String) -> Unit) {
                            val token = "token"
                            handler(token)
                        }

                        override fun onAccountConnected(accountId: String, workerId: String) {
                            Log.d("Result", "onAccountConnected: accountId: $accountId workerId: $workerId")
                        }

                        override fun onAccountRemoved(accountId: String, workerId: String) {
                            Log.d("Result", "onAccountRemoved: accountId: $accountId workerId: $workerId")
                        }

                        override fun onError(error: ArgyleErrorType) {
                            super.onError(error)
                            Log.d("Result", "onError: error: $error")
                        }

                        override fun onWorkerCreated(workerToken: String, workerId: String) {
                            Log.d("Result", "onWorkerCreated:  workerId: $workerId workerToken: $workerToken")
                        }
                    })

                    .build()
                argyle.init(config)
            }
            Argyle.instance.startSDK(this)
        }

    }

    private fun openSdk() {
        val apiHost = "https://api-sandbox.develop.argyle.io/v1/"
        val pluginKey = "646dc138-5942-4eb6-a9ca-dd01b6d57ae9"
        val argyle = Argyle.instance
        val config = ArgyleConfig.Builder()
            //.dataPartners(arrayOf("uber", "deliv"))
            .loginWith(pluginKey, apiHost)
            .setCallbackListener(object : Argyle.ArgyleResultListener {

                override fun onTokenExpired(handler: (String) -> Unit) {
                    val token = "token"
                    handler(token)
                }

                override fun onAccountConnected(accountId: String, workerId: String) {
                    Log.d("Result", "onAccountConnected: accountId: $accountId workerId: $workerId")
                }

                override fun onAccountRemoved(accountId: String, workerId: String) {
                    Log.d("Result", "onAccountRemoved: accountId: $accountId workerId: $workerId")
                }

                override fun onError(error: ArgyleErrorType) {
                    super.onError(error)
                    Log.d("Result", "onError: error: $error")
                }

                override fun onWorkerCreated(workerToken: String, workerId: String) {
                    val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                    val editor = preferences.edit()
                    editor.clear().commit()
                    editor.putString("workerToken", workerToken)
                    editor.putString("workerId", workerId)
                    editor.apply()
                    Log.d("Result", "onWorkerCreated:  workerId: $workerId workerToken: $workerToken")
                }
            })
            .build()
        argyle.init(config)
        Argyle.instance.startSDK(this)
    }
}
