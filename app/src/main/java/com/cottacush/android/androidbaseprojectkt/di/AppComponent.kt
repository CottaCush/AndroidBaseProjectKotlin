package com.cottacush.android.androidbaseprojectkt.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreDataModule::class, ViewModelModule::class
    ]
)

interface AppComponent {
}
