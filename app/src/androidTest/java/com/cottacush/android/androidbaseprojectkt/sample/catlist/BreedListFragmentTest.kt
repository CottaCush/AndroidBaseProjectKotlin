package com.cottacush.android.androidbaseprojectkt.sample.catlist

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.cottacush.android.androidbaseprojectkt.R
import com.cottacush.android.androidbaseprojectkt.sample.breeddetails.BreedDetailsFragment
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.sample.models.DatabaseBreedModel
import com.cottacush.android.androidbaseprojectkt.sample.models.asDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class BreedListFragmentTest {

    private val database = mock(BreedDatabase::class.java)
    private val dataBaseBreed = mock(DatabaseBreedModel::class.java)
    private val list = listOf(dataBaseBreed)
    private val model = list.asDomainModel()[0]

    @Test
    fun clickBreed_navigateToDetailFragment() {
        runBlockingTest {
            database.breedDao.insertAllBreed(dataBaseBreed)
        }
        val navController = mock(NavController::class.java)

        val scenerio = launchFragmentInContainer<BreedDetailsFragment>(Bundle(), R.style.AppTheme)

        scenerio.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }
        onView(withId(R.id.breedsRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(
                        withText(model.description)
                    ), click()
                )
            )

        verify(navController).navigate(
            BreedListFragmentDirections.actionCatsListFragmentToBreedDetailsFragment(
                model
            )
        )
    }

    @After
    fun tearDown() {
        database?.run {
            clearAllTables()
            close()
        }
    }
}