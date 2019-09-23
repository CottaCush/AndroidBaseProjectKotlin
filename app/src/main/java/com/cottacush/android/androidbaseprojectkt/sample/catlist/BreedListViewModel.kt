package com.cottacush.android.androidbaseprojectkt.sample.catlist

import android.provider.SyncStateContract
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


    private val _navigateToSelectedBreed = MutableLiveData<Breed>()


    val navigateToSelectedBreed: LiveData<Breed>
        get() = _navigateToSelectedBreed

//    private val _catsBreedList = MutableLiveData<List<Breed>>()
//
//    val catsBreedList: List<Breed>
//        get() = _catsBreedList

    init {
        viewModelScope.launch {
            exampleRepository.getBreeds(Utils.LIMIT)
        }
    }

    fun getCatsBreedList() {
      val catList  = exampleRepository.breed

    }

    fun displayCatBreedDetails(breed: Breed) {
        _navigateToSelectedBreed.value = breed
    }

    fun displayCatBreedDetailsComplete() {
        _navigateToSelectedBreed.value = null
    }
}
