package com.cottacush.android.androidbaseprojectkt.auth

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    companion object {
        const val AUTH_KEY = "access_token" // change to api_key, appid, auth_key etc, as required.
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = tokenProvider.token() ?: return null

        synchronized(this) {
            val newToken = tokenProvider.token()

            // Check if the request made was previously made as an authenticated request.
            if (response.request.url.queryParameter(AUTH_KEY) != null) {

                val requestBuilder = response.request.newBuilder()
                val urlBuilder = response.request.url.newBuilder().removeAllQueryParameters(AUTH_KEY)

                // If the token has changed since the request was made, use the new token.
                if (newToken != token) {
                    return requestBuilder.url(urlBuilder.addQueryParameter(AUTH_KEY, newToken).build()).build()
                }

                val updatedToken = tokenProvider.refreshToken() ?: return null

                // Retry the request with the new token.
                return requestBuilder.url(urlBuilder.addQueryParameter(AUTH_KEY, updatedToken).build()).build()
            }
        }
        return null
    }
}
