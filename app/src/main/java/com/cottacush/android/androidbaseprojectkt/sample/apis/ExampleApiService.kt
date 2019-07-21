package com.cottacush.android.androidbaseprojectkt.sample.apis

import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import retrofit2.http.GET
import retrofit2.http.Path

interface ExampleApiService {

    @GET("breeds")
    suspend fun getCatBreeds(@Path("id") id: Long): List<Breed>

    companion object {
        const val ENDPOINT = "https://api.thecatapi.com/v1"
    }
}