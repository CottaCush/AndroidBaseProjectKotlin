package com.cottacush.android.androidbaseprojectkt.sample.catlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cottacush.android.androidbaseprojectkt.base.BaseViewModel
import com.cottacush.android.androidbaseprojectkt.networkutils.LoadingStatus
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.cottacush.android.androidbaseprojectkt.networkutils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class BreedListViewModel @Inject constructor(private val exampleRepository: ExampleRepository) :
    BaseViewModel(Dispatchers.Main) {

    private val _navigateToSelectedBreed = MutableLiveData<Breed>()

    val navigateToSelectedBreed: LiveData<Breed>
        get() = _navigateToSelectedBreed


    private val _catsBreedList = MutableLiveData<List<Breed>>()

    val catsBreedList: LiveData<List<Breed>>
        get() = _catsBreedList


    fun getCatsBreedList() {
        coroutineScope.launch {
            _loadingStatus.value = LoadingStatus.Loading("Loading the cat breeds, Please wait... ")
            when (val result = exampleRepository.getBreeds(20)) {
                is Result.Success -> {
                    _catsBreedList.value = result.data
                    _loadingStatus.postValue(LoadingStatus.Success)
                }
                is Result.Error -> _loadingStatus.value = LoadingStatus.Error(result.errorCode, result.errorMessage)
            }
        }
    }

    fun displayCatBreedDetails(breed: Breed) {
        _navigateToSelectedBreed.value = breed
    }

    fun displayCatBreedDetailsComplete() {
        _navigateToSelectedBreed.value = null
    }

}
