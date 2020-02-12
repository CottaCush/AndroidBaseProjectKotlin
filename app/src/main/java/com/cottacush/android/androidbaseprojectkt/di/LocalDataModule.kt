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
package com.cottacush.android.androidbaseprojectkt.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.cottacush.android.androidbaseprojectkt.sample.models.BreedDatabase
import com.cottacush.android.androidbaseprojectkt.utils.PrefsUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun providePrefsUtils(prefs: SharedPreferences, gson: Gson): PrefsUtils =
        PrefsUtils(prefs, gson)

    @Provides
    @Singleton
    fun provideGlobalSharedPreference(app: Application): SharedPreferences =
        app.getSharedPreferences("global_shared_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideBreedDatabase(app: Application): BreedDatabase = Room.databaseBuilder(app,
        BreedDatabase::class.java,
        "breeds-database.db"
    ).build()
}
