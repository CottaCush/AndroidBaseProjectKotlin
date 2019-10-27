package com.cottacush.android.androidbaseprojectkt.repostiory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import com.cottacush.android.androidbaseprojectkt.sample.NetworkBreedModel
import com.cottacush.android.androidbaseprojectkt.sample.apis.ExampleApiService
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDao
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.sample.models.DatabaseBreedModel

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.Response

@RunWith(JUnit4::class)
class ExampleRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var repository: ExampleRepository
    private val mockService = mock(ExampleApiService::class.java)
    private val breedDao = mock(BreedDao::class.java)
    private val success = mock(Result.Success::class.java)
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    val database = mock(BreedDatabase::class.java)

    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
        `when`(database.breedDao).thenReturn(breedDao)
        `when`(database.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = ExampleRepository(mockService, database)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testRefreshBreeds() {
        val data = MutableLiveData<List<DatabaseBreedModel>>()
        `when`(breedDao.getAllBreed()).thenReturn(data)
        val list: List<NetworkBreedModel> = emptyList()
        val call = Response.success(list)
        runBlocking {
            launch(Dispatchers.Main) {
                `when`(mockService.getCatBreeds(40)).thenReturn(call)
                repository.refreshBreeds(40)
            }
        }
        verify(breedDao, times(1)).insertAllBreed()
    }

    @Test
    fun testRefreshBreedsFailure() {
        val data = MutableLiveData<List<DatabaseBreedModel>>()
        `when`(breedDao.getAllBreed()).thenReturn(data)
        val response = mock(ResponseBody::class.java)
        val call = Response.error<List<NetworkBreedModel>>(400, response)
        runBlocking {
            launch(Dispatchers.Main) {
                `when`(mockService.getCatBreeds(40)).thenReturn(call)
                repository.refreshBreeds(40)
            }
        }
        verify(breedDao, times(0)).insertAllBreed()
    }
}
