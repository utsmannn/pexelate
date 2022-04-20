package com.utsman.navigator

import com.utsman.navigator.fragment.CollectionFragmentContainer
import com.utsman.navigator.fragment.PhotoFragmentContainer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object FragmentNavigator : KoinComponent {

    val PHOTO_FRAGMENT_CONTAINER: PhotoFragmentContainer by inject()
    val COLLECTION_FRAGMENT_CONTAINER: CollectionFragmentContainer by inject()
}