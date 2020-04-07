/**
 * Copyright (c) 2019 Cotta & Cush Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cottacush.android.androidbaseprojectkt.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import com.cottacush.android.androidbaseprojectkt.mock
import com.cottacush.android.androidbaseprojectkt.sample.basic.catlist.BreedListViewModel
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class BreedListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = mock(ExampleRepository::class.java)
    private var viewModel: BreedListViewModel? = null
    private val observer: Observer<Breed> = mock()
    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = BreedListViewModel(repository)
        viewModel!!.navigateToSelectedBreed.observeForever(observer)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun testNull() {
        Assert.assertNull(viewModel?.breeds)
    }

    @Test
    fun testFetchBreedShouldReturnBreed() {
        val newBreed = mock<Breed>()
        runBlocking {
            launch(Dispatchers.Main) {
                viewModel!!.displayCatBreedDetails(newBreed)
            }
        }
        val captor = ArgumentCaptor.forClass(Breed::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals(newBreed, value)
        }
    }

    @Test
    fun testFetchBreedComplete() {
        runBlocking {
            launch(Dispatchers.Main) {
                viewModel!!.displayCatBreedDetailsComplete()
            }
        }
        assertNull(viewModel!!.navigateToSelectedBreed.value)
    }
}
