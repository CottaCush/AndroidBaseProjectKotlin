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
package com.cottacush.android.androidbaseprojectkt.sample.apis

import com.cottacush.android.androidbaseprojectkt.utils.PrefsUtils
import com.cottacush.android.androidbaseprojectkt.auth.AccessTokenProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenProviderImpl @Inject constructor(
    val exampleAPIAuthService: ExampleAPIAuthService,
    val prefsUtils: PrefsUtils
) : AccessTokenProvider {

    /*The access token is provided here as a constant string for sample purpose. Normally, you'd want to make a synchronous
    network call to get the token with exampleAPIAuthService in the refreshToken() method and save it with prefsUtils.
    then access the token from prefsUtils inside the token() method.*/
    // TODO implement a the AccessTokenProviderImpl as described above

    private val apiKey = "1fcac9b1-9150-4fdd-80ef-15232ff441d5"

    override fun token(): String? {
        return apiKey
    }

    override fun refreshToken(): String? {
        return apiKey
    }
}
