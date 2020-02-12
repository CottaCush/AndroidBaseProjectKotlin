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
package com.cottacush.android.androidbaseprojectkt.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cottacush.android.androidbaseprojectkt.networkutils.LoadingStatus

abstract class BaseViewModel() : ViewModel() {

    protected val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    override fun onCleared() {
        super.onCleared()
    }
}
