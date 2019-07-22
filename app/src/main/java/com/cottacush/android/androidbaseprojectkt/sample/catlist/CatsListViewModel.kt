package com.cottacush.android.androidbaseprojectkt.sample.catlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cottacush.android.androidbaseprojectkt.base.BaseViewModel
import com.cottacush.android.androidbaseprojectkt.base.LoadingStatus
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import com.cottacush.android.androidbaseprojectkt.sample.models.Breed
import com.r4sh33d.duplicatecontactsremover.model.ContactsAccount
import com.r4sh33d.duplicatecontactsremover.util.ContactsHelper
import com.r4sh33d.duplicatecontactsremover.util.DuplicateCriteria
import com.r4sh33d.duplicatecontactsremover.util.LoadingStatus
import kotlinx.coroutines.*
import javax.inject.Inject


class CatsListViewModel @Inject constructor(private val exampleRepository: ExampleRepository) :
        BaseViewModel(Dispatchers.Main) {

    private val _navigateToSelectedBreed = MutableLiveData<Breed>()

    val navigateToSelectedBreed:  LiveData<Breed>
        get() = _navigateToSelectedBreed

    private val _status = MutableLiveData<LoadingStatus>()

    val status: LiveData<LoadingStatus>
        get() = _status

    private val _catsBreedList = MutableLiveData<List<Breed>>()

    val catsBreedList: LiveData<List<Breed>>
        get() = _catsBreedList


    fun getContactsAccountsList(duplicateCriteria: DuplicateCriteria) {
        coroutineScope.launch {
            _status.value = LoadingStatus.LOADING
            _catsBreedList.value = getContactsWithAccounts(duplicateCriteria)
            _status.value = if (catsBreedList.value!!.isNotEmpty()) LoadingStatus.DONE
            else LoadingStatus.EMPTY
        }
    }

    private suspend fun getContactsWithAccounts(duplicateCriteria: DuplicateCriteria): List<ContactsAccount> {
        return withContext(Dispatchers.IO) {
            contactsHelper.getContactsWithAccounts(duplicateCriteria)
        }
    }

    fun displayContactAccountDetails(contactsAccount: ContactsAccount) {
        _navigateToSelectedBreed.value = contactsAccount
    }

    fun displayContactAccountDetailsComplete() {
        _navigateToSelectedBreed.value = null
    }

}