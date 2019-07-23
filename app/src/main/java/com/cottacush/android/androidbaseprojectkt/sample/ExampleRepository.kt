package com.cottacush.android.androidbaseprojectkt.sample

import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_CODE
import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_MESSAGE
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import com.cottacush.android.androidbaseprojectkt.networkutils.getAPIResult
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val exampleApiService: ExampleApiService) {

    suspend fun getBreeds(limit: Int): Result<List<Breed>> {
        return try {
            getAPIResult(exampleApiService.getCatBreeds(limit))
        } catch (e: Exception) {
            Result.Error(GENERIC_ERROR_CODE, e.message ?: GENERIC_ERROR_MESSAGE)
        }
    }
}