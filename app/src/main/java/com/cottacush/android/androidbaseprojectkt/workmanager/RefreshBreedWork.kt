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
package com.cottacush.android.androidbaseprojectkt.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cottacush.android.androidbaseprojectkt.App
import com.cottacush.android.androidbaseprojectkt.sample.ExampleRepository
import retrofit2.HttpException
import javax.inject.Inject

class RefreshDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    @Inject
    lateinit var repository: ExampleRepository

    companion object {
        const val WORK_NAME = "RefreshDataWork"
    }

    init {
        (appContext as App).component.inject(this)
    }

    override suspend fun doWork(): Payload {
        return try {
            repository.refreshBreeds(40)
            Payload(Result.SUCCESS)
        } catch (exception: HttpException) {
            Payload(Result.RETRY)
        }
    }
}
