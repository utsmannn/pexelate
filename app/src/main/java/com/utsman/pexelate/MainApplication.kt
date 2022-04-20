package com.utsman.pexelate

import android.app.Application
import com.utsman.collection.di.CollectionModuleContainer
import com.utsman.core.di.CoreModuleContainer
import com.utsman.photo.di.PhotoModuleContainer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val coreModule = CoreModuleContainer()
        val photoModule = PhotoModuleContainer()
        val collectionModule = CollectionModuleContainer()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModule.modules() +
                        photoModule.modules() +
                        collectionModule.modules()
            )
        }
    }
}