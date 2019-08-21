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
        val errorBodyString = errorBody.string()
        if (errorBody != null) {
            return Result.Error(
                getErrorCode(errorBodyString), getErrorMessage(errorBodyString)
            )
        }
    }
    // Fallback to regular status code and message
    return Result.Error("${response.code()}", response.message())
}

fun getErrorMessage(responseBody: String): String {
    return try {
        val jsonObject = JSONObject(responseBody)
        jsonObject.getString("message").replace("_".toRegex(), " ")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_MESSAGE
    }
}

fun getErrorCode(errorBody: String): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody)
        errorBodyJsonObject.getString("code")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_CODE
    }
}
