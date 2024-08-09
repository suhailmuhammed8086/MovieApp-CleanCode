package com.app.moviesapp.states

import androidx.compose.runtime.Composable

sealed class ResponseState<out T> {
    data object Idle: ResponseState<Nothing>()
    data object Loading: ResponseState<Nothing>()
    class Success<R>(val response: R?) : ResponseState<R>()
    class ValidationError(val errorCode: Int,val error: String) : ResponseState<Nothing>()
    class Failed(val error: String, val errorCode: Int) : ResponseState<Nothing>()
    data object Cancelled : ResponseState<Nothing>()

    companion object {
        @Composable
        fun <R> HandleComposeState(
            responseState: ResponseState<R>,
            onLoading: @Composable (() -> Unit)? = null,
            onSuccess: @Composable ((response: R?) -> Unit)? = null,
            onFailed: @Composable ((error: String, errorCode: Int) -> Unit)? = null,
            onCancelled: @Composable (() -> Unit)? = null,
            onValidationError:@Composable ((error: String, errorCode: Int) -> Unit)? = null,
            onIdle:@Composable (() -> Unit)? = null,
        ) {
            when (responseState) {
                Cancelled -> onCancelled?.invoke()
                is Failed -> onFailed?.invoke(responseState.error, responseState.errorCode)
                ResponseState.Idle -> onIdle?.invoke()
                Loading -> onLoading?.invoke()
                is ResponseState.Success -> onSuccess?.invoke(responseState.response)
                is ValidationError -> onValidationError?.invoke(
                    responseState.error,
                    responseState.errorCode
                )
            }
        }
    }
}