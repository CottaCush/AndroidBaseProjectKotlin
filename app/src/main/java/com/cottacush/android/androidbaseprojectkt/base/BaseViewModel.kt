package com.cottacush.android.androidbaseprojectkt.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class BaseViewModel(mainDispatcher: CoroutineDispatcher) : ViewModel() {

    private val job = Job()

    val coroutineScope = CoroutineScope(job + mainDispatcher)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
