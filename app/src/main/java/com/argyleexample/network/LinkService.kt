package com.argyleexample.network

import android.util.Base64
import com.argyleexample.LinkConstants
import com.argyleexample.network.request.UserTokenRequest
import com.argyleexample.network.response.UserResponse
import com.argyleexample.network.response.UserTokenResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface LinkService {

    @POST("users")
    suspend fun createUser(): UserResponse

    @POST("user-tokens")
    suspend fun createUserToken(@Body request: UserTokenRequest): UserTokenResponse

    companion object {
        private const val BASE_URL_SANDBOX = "https://api-sandbox.argyle.com/v1/"
        private const val BASE_URL_LIVE = "https://api.argyle.com/v1/"

        fun create(): LinkService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                    request.apply {
                        header("Authorization", generateAuthorizationHeader())
                        header("Accept", "application/json")
                        header("Content-Type", "application/json")
                    }
                    chain.proceed(request.build())
                }
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(if (LinkConstants.SANDBOX) BASE_URL_SANDBOX else BASE_URL_LIVE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(LinkService::class.java)
        }

        private fun generateAuthorizationHeader() =
            Base64.encodeToString(
                "${LinkConstants.API_KEY_ID}:${LinkConstants.API_KEY_SECRET}".toByteArray(),
                Base64.NO_WRAP
            ).let { "Basic $it" }
    }
}