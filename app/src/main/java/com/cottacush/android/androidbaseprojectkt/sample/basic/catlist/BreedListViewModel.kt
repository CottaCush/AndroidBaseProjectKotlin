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
package com.cottacush.android.androidbaseprojectkt.sample.basic.catlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cottacush.android.androidbaseprojectkt.Utils
import com.cottacush.android.androidbaseprojectkt.base.BaseViewModel
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreedListViewModel @Inject constructor(private val exampleRepository: ExampleRepository) :
    BaseViewModel() {
    val breeds = exampleRepository.breeds

    private val _navigateToSelectedBreed = MutableLiveData<Breed>()

    val navigateToSelectedBreed: LiveData<Breed>
        get() = _navigateToSelectedBreed

    init {
        viewModelScope.launch {
            // TODO don't refresh every time. schedule refresh with workManager...
            // only when there is network. The refresh strategy could also be more complex.
            exampleRepository.refreshBreeds(Utils.LIMIT)
        }
    }

    fun displayCatBreedDetails(breed: Breed) {
        _navigateToSelectedBreed.value = breed
    }

    fun displayCatBreedDetailsComplete() {
        _navigateToSelectedBreed.value = null
    }
}
