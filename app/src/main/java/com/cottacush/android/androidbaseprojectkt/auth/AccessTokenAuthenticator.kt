/**
 * Copyright (c) 2019 Cotta & Cush Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

            if (response.request.url.queryParameter(AUTH_KEY) != null) {

                val requestBuilder = response.request.newBuilder()
                val urlBuilder = response.request.url.newBuilder().removeAllQueryParameters(AUTH_KEY)

                if (newToken != token) {
                    return requestBuilder.url(urlBuilder.addQueryParameter(AUTH_KEY, newToken).build()).build()
                }

                val updatedToken = tokenProvider.refreshToken() ?: return null

                return requestBuilder.url(urlBuilder.addQueryParameter(AUTH_KEY, updatedToken).build()).build()
            }
        }
        return null
    }
}
