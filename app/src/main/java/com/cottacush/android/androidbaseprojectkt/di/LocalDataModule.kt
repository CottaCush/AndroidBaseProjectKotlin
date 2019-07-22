package com.cottacush.android.androidbaseprojectkt.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
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
    fun provideGlobalSharedPreference(app: Application) =
            app.getSharedPreferences("global_shared_prefs", Context.MODE_PRIVATE)

}