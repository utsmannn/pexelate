package com.utsman.photo.di

import com.utsman.core.di.ModuleContainer
import com.utsman.core.network.RetrofitBuilder
import com.utsman.navigator.fragment.PhotoFragmentContainer
import com.utsman.photo.navigator.FragmentContainerImpl
import com.utsman.photo.presenter.PhotoHomeViewModel
import com.utsman.photo.repository.PhotoRepository
import com.utsman.photo.repository.PhotoRepositoryImpl
import com.utsman.photo.sources.PhotoDataSources
import com.utsman.photo.sources.PhotoWebServices
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PhotoModuleContainer : ModuleContainer() {

    private val photoModule = module {
        single<PhotoWebServices> {
            RetrofitBuilder.get.build().create(PhotoWebServices::class.java)
        }
        single { PhotoDataSources(get()) }
        factory<PhotoRepository> { PhotoRepositoryImpl(get()) }

        single<PhotoFragmentContainer> { FragmentContainerImpl() }

        viewModel { PhotoHomeViewModel(get()) }
    }
}