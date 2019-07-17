package com.cottacush.android.androidbaseprojectkt.auth;

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    companion object{
        const val ACCESS_TOKEN_KEY = "access_token"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = tokenProvider.token() ?: return null

        synchronized(this) {
            val newToken = tokenProvider.token()

            // Check if the request made was previously made as an authenticated request.
            if (response.request().url().queryParameter(ACCESS_TOKEN_KEY) != null) {

                val requestBuilder = response.request().newBuilder()
                val urlBuilder = response.request().url().newBuilder().removeAllQueryParameters(ACCESS_TOKEN_KEY)

                // If the token has changed since the request was made, use the new token.
                if (newToken != token) {
                    return requestBuilder.url(urlBuilder.addQueryParameter(ACCESS_TOKEN_KEY, newToken).build()).build()
                }

                val updatedToken = tokenProvider.refreshToken() ?: return null

                // Retry the request with the new token.
                return requestBuilder.url(urlBuilder.addQueryParameter(ACCESS_TOKEN_KEY, updatedToken).build()).build()
            }
        }
        return null
    }
}