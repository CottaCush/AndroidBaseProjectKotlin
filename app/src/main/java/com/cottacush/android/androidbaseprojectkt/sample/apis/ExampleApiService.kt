package com.cottacush.android.androidbaseprojectkt.sample.apis

import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import retrofit2.http.GET

interface ExampleApiService {
    companion object {
        const val ENDPOINT = "https://api.thecatapi.com/v1"
    }

    @GET("breeds")
    suspend fun getCatBreeds(): List<Breed>
}