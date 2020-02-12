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
package com.cottacush.android.androidbaseprojectkt.networkutils

sealed class LoadingStatus {

    object Success : LoadingStatus()
    class Loading(val message: String) : LoadingStatus()
    data class Error(val errorCode: String, val errorMessage: String) : LoadingStatus()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success"
            is Error -> "Error[$errorCode: $errorMessage]"
            is Loading -> "Loading[$message]"
        }
    }
}
