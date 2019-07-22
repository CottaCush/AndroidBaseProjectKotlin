package com.cottacush.android.androidbaseprojectkt

import android.app.Application
import com.cottacush.android.androidbaseprojectkt.di.AppComponent
import timber.log.Timber


class App: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

   /*     component = Dagge.builder()
                .application(this)
                .build()*/
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}