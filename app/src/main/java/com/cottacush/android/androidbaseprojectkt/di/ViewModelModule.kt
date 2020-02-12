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
