package com.cottacush.android.androidbaseprojectkt.sample

import androidx.lifecycle.Transformations
import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_CODE
import com.cottacush.android.androidbaseprojectkt.networkutils.GENERIC_ERROR_MESSAGE
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import com.cottacush.android.androidbaseprojectkt.networkutils.getAPIResult
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.sample.models.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val exampleApiService: ExampleApiService,
    private val breedDatabase: BreedDatabase
) {

    val breeds =  Transformations.map(breedDatabase.breedDao.getAllBreed()) {
        Timber.d("Gotten it: ${it.asDomainModel()}")
        it.asDomainModel()
    }

    suspend fun refreshBreeds(limit: Int) {
        withContext(Dispatchers.IO) {
            try {
                when (val result = getAPIResult(exampleApiService.getCatBreeds(limit))) {
                    is Result.Success -> {
                        Timber.d("Success Called")
                        breedDatabase.breedDao.dropTable()
                        Timber.d("About to insert: ${result.data.asDataBaseModel()}" )
                        breedDatabase.breedDao.insertAllBreed(*result.data.asDataBaseModel())

                    }
                    is Result.Error -> {
                        Timber.d("Error called")
                        Result.Error(GENERIC_ERROR_CODE, result.errorMessage)
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                Result.Error(GENERIC_ERROR_CODE, e.message ?: GENERIC_ERROR_MESSAGE)
            }
        }
    }
}
