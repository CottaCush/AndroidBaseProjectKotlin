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
package com.cottacush.android.androidbaseprojectkt.networkutils

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

const val GENERIC_ERROR_MESSAGE = "An error occurred, Please try again"
const val GENERIC_ERROR_CODE = "-1"

fun <T : Any> getAPIResult(response: Response<T>): Result<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            return Result.Success(body)
        }
    }
    // This else branch is specific to cotta-cush APIs
    else {
        val errorBody = response.errorBody()
        if (errorBody != null) {
            return Result.Error(
                getErrorCode(errorBody), getErrorMessage(errorBody)
            )
        }
    }
    // Fallback to regular status code and message
    return Result.Error("${response.code()}", response.message())
}

fun getErrorMessage(responseBody: ResponseBody): String {
    return try {
        val jsonObject = JSONObject(responseBody.string())
        jsonObject.getString("message").replace("_".toRegex(), " ")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_MESSAGE
    }
}

fun getErrorCode(errorBody: ResponseBody): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody.string())
        errorBodyJsonObject.getString("code")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_CODE
    }
}
