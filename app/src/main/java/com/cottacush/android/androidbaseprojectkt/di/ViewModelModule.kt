package com.cottacush.android.androidbaseprojectkt.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cottacush.android.androidbaseprojectkt.sample.advanced.breedlist.AdvancedBreedListViewModel
import com.cottacush.android.androidbaseprojectkt.sample.basic.catlist.BreedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ExampleAppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BreedListViewModel::class)
    abstract fun bindBreedListViewModel(viewModel: BreedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdvancedBreedListViewModel::class)
    abstract fun bindAdvancedBreedListViewModel(viewModel: AdvancedBreedListViewModel): ViewModel

    // TODO Add other ViewModels.
}
