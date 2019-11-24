package com.cottacush.android.androidbaseprojectkt.sample.models

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(AndroidJUnit4::class)
class BreedDatabaseTest {

    @Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    private lateinit var observer : Observer<List<DatabaseBreedModel>>

    private lateinit var breedDatabase : BreedDatabase
    private lateinit var breedDao: BreedDao

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        breedDatabase = Room.inMemoryDatabaseBuilder(
            context, BreedDatabase::class.java)
            .allowMainThreadQueries().build()
        breedDao = breedDatabase.breedDao
    }

    @After
    fun tearDown() {
        breedDatabase.close()
    }

    @Test
    fun insertBreed(){
        val breed = BreedUtils().createBreedTestUtils()
        breedDao.getAllBreed().observeForever(observer)
        breedDao.insertAllBreed(breed)
        verify(observer).onChanged(Collections.singletonList(breed))
    }

    @Test
    fun getBreed(){
        val breed = BreedUtils().createBreedTestUtils()
        breedDao.insertAllBreed(breed)
        breedDao.getAllBreed().observeForever(observer)
    }

    @Test
    fun dropBreedTable(){
        val breed = BreedUtils().createBreedTestUtils()
        breedDao.getAllBreed().observeForever(observer)
        breedDao.insertAllBreed(breed)
        breedDao.dropTable()
    }


}