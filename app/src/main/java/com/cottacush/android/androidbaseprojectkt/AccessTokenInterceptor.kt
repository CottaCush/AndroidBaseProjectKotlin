package com.cottacush.android.androidbaseprojectkt

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor(
    private val authTokenDataSource: AccessTokenDataSource
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!authTokenDataSource.authToken.isNullOrEmpty()) {
            val url = chain.request().url().newBuilder()
                .addQueryParameter("access_token", authTokenDataSource.authToken).build()
            requestBuilder.url(url)
        }
        return chain.proceed(requestBuilder.build())
    }
}
