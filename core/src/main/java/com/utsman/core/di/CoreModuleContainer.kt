package com.utsman.core.di

import com.utsman.core.network.RetrofitBuilder
import org.koin.dsl.module

class CoreModuleContainer : ModuleContainer() {

    private val retrofitBuilderModule = module {
        single { RetrofitBuilder() }
    }
}