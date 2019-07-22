package com.cottacush.android.androidbaseprojectkt.di

import androidx.lifecycle.ViewModel
import com.cottacush.android.androidbaseprojectkt.sample.catlist.CatsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatsListViewModel::class)
    abstract fun bindContactSourcesViewModel(viewModel: CatsListViewModel): ViewModel

    //TODO Add other view models.
}
