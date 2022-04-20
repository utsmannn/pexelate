package com.utsman.core

import com.utsman.core.state.StateEvent
import kotlinx.coroutines.flow.StateFlow

typealias StateFlowEvent<T> = StateFlow<StateEvent<T>>
typealias QueryMapData = Map<String, Any>