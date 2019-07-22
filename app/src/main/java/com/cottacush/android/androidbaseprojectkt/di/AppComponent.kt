package com.cottacush.android.androidbaseprojectkt.di

import android.app.Application
import com.cottacush.android.androidbaseprojectkt.sample.catlist.BreedListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [LocalDataModule::class, APIServiceModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: BreedListFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}
