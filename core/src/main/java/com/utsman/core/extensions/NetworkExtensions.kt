package com.utsman.core.extensions

import android.accounts.NetworkErrorException
import com.utsman.core.data.Equatable
import com.utsman.core.state.StateEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.IllegalStateException

suspend fun <T: Any> Response<T>.fetchState(): Flow<StateEvent<T>> = flow {
    emit(StateEvent.Loading())
    try {
        val data = body()
        val errorBody = errorBody()
        when {
            data != null -> {
                emit(StateEvent.Success(data = data))
            }
            errorBody != null -> {
                val throwable = NetworkErrorException(errorBody.string())
                emit(StateEvent.Error(throwable))
            }
            else -> {
                val throwable = IllegalStateException(message())
                emit(StateEvent.Error(throwable))
            }
        }
    } catch (e: Throwable) {
        emit(StateEvent.Error(exception = e))
    }
}

inline fun <T: Any, U: Any>StateEvent<T>.map(mapper: T.() -> U): StateEvent<U> {
    return when (this) {
        is StateEvent.Success -> {
            val data = mapper.invoke(data)
            StateEvent.Success(data)
        }
        is StateEvent.Loading -> StateEvent.Loading()
        is StateEvent.Error -> StateEvent.Error(exception)
    }
}

fun <T : Any> StateEvent<T>.getDataOrNull(): T? {
    return if (this is StateEvent.Success) {
        return this.data
    } else {
        null
    }
}

fun <T: Any> loadingStateFlowEvent() = MutableStateFlow<StateEvent<T>>(StateEvent.Loading())

fun <T : Any> StateEvent<T>.onSuccess(result: (T) -> Unit) {
    if (this is StateEvent.Success) {
        result.invoke(this.data)
    }
}

fun <T : Any> StateEvent<T>.onFailure(result: (Throwable) -> Unit) {
    if (this is StateEvent.Error) {
        result.invoke(this.exception)
    }
}

fun <T : Any> StateEvent<T>.onLoading(result: () -> Unit) {
    if (this is StateEvent.Loading) {
        result.invoke()
    }
}