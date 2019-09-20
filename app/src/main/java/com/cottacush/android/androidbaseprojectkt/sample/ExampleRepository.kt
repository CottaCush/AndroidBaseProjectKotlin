package com.cottacush.android.androidbaseprojectkt.sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_CODE
import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_MESSAGE
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import com.cottacush.android.androidbaseprojectkt.networkutils.getAPIResult
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.sample.models.asDomainModel
import java.lang.Exception
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val exampleApiService: ExampleApiService,
                                             breedDatabase: BreedDatabase) {


    val breed: LiveData<List<Breed>> = Transformations.map(breedDatabase.breedDao.getAllBreed()){
        it.asDomainModel()
    }

    suspend fun getBreeds(limit: Int): Result<List<Breed>> {
        return try {
            getAPIResult(exampleApiService.getCatBreeds(limit))
        } catch (e: Exception) {
            Result.Error(GENERIC_ERROR_CODE, e.message ?: GENERIC_ERROR_MESSAGE)
        }
    }
}
