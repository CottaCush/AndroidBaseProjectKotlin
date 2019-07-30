package com.cottacush.android.androidbaseprojectkt.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cottacush.android.androidbaseprojectkt.networkutils.LoadingStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class BaseViewModel(startingDispatcher: CoroutineDispatcher) : ViewModel() {

    private val job = Job()

    protected val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    val coroutineScope = CoroutineScope(job + startingDispatcher)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
