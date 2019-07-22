package com.cottacush.android.androidbaseprojectkt.sample

import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import com.cottacush.android.androidbaseprojectkt.networkutils.getAPIResult
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val exampleApiService: ExampleApiService){

    suspend fun getBreeds(limit: Int) : Result<List<Breed>> =
        getAPIResult(exampleApiService.getCatBreeds(limit))

}