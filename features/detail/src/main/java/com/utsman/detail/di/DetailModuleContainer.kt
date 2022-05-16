package com.utsman.detail.di

import com.utsman.core.di.ModuleContainer
import com.utsman.detail.navigator.ActivityContainerImpl
import com.utsman.navigator.activity.DetailActivityContainer
import org.koin.dsl.module

class DetailModuleContainer : ModuleContainer() {

    private val detailModule = module {
        single<DetailActivityContainer> { ActivityContainerImpl() }
    }
}