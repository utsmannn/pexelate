package com.utsman.core.state

sealed class StateEvent<T: Any> {
    class Loading<T: Any> : StateEvent<T>()
    data class Success<T: Any>(val data: T) : StateEvent<T>()
    data class Error<T: Any>(val exception: Throwable): StateEvent<T>()
}