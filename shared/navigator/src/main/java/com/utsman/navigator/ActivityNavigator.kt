package com.utsman.navigator

import com.utsman.navigator.activity.DetailActivityContainer
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object ActivityNavigator : KoinComponent {

    val DETAIL_ACTIVITY_CONTAINER: DetailActivityContainer by inject()
}