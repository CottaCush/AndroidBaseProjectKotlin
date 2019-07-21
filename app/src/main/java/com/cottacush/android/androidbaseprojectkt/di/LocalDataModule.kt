package com.cottacush.android.androidbaseprojectkt.di

import android.app.Application
import com.cottacush.android.androidbaseprojectkt.utils.PrefsUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun providePrefsUtils(app: Application, gson: Gson): PrefsUtils =
        PrefsUtils(app, gson)

}