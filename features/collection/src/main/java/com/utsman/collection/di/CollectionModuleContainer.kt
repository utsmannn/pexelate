package com.utsman.collection.di

import com.utsman.collection.navigator.FragmentContainerImpl
import com.utsman.collection.presenter.CollectionHomeViewModel
import com.utsman.collection.repository.CollectionRepository
import com.utsman.collection.repository.CollectionRepositoryImpl
import com.utsman.collection.sources.CollectionDataSources
import com.utsman.collection.sources.CollectionWebServices
import com.utsman.core.di.ModuleContainer
import com.utsman.core.network.RetrofitBuilder
import com.utsman.navigator.fragment.CollectionFragmentContainer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class CollectionModuleContainer : ModuleContainer() {

    private val collectionModule = module {
        single<CollectionWebServices> {
            RetrofitBuilder.get.build().create(CollectionWebServices::class.java)
        }
        single { CollectionDataSources(get()) }
        factory<CollectionRepository> { CollectionRepositoryImpl(get()) }

        single<CollectionFragmentContainer> { FragmentContainerImpl() }

        viewModel { CollectionHomeViewModel(get()) }
    }
}