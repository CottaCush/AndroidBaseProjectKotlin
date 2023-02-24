package com.cottacush.android.androidbaseprojectkt.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cottacush.android.androidbaseprojectkt.sample.Weight
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.sample.models.DatabaseBreedModel
import com.cottacush.android.androidbaseprojectkt.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class BreedDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var breedDatabase : BreedDatabase
    @Before
    fun setUpBreedDatabase(){
        breedDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreedDatabase::class.java
        ).build()
    }

    @After
    fun clearUpDatabase () = breedDatabase.close()


    @Test
    fun insertBreedsAndGet(){
        //GIVEN - INSERT A BREED
        val breed =  DatabaseBreedModel(
            id = "id",
            description = "cat description",
            lifeSpan = "4 years",
            name = "cat name",
            origin = "nevada",
            socialNeeds = 0,
            strangerFriendly = 1,
            temperament = "angry",
            weight = Weight(imperial = "35", metric = "70"),
            wikipediaUrl = "www.imageurl.com"
        )
        breedDatabase.breedDao.insertAllBreed(breed)

        //WHEN - GET BREED

        val result = breedDatabase.breedDao.getAllBreed().getOrAwaitValue()


        // THEN - The loaded data contains the expected values.
        assertThat<List<DatabaseBreedModel>>(result, notNullValue())

    }
}