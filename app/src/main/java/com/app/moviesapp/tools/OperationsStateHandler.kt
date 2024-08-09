package com.app.moviesapp.tools
import com.app.moviesapp.data.NoNetworkException
import com.app.moviesapp.data.ValidationErrorException
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.states.ResponseState.Cancelled
import com.app.moviesapp.states.ResponseState.Failed
import com.app.moviesapp.states.ResponseState.Loading
import com.app.moviesapp.states.ResponseState.ValidationError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.concurrent.CancellationException

class OperationsStateHandler<T>(
    private val scope: CoroutineScope,
    private var stateUpdateCallback:suspend (state: ResponseState<T>)->Unit
) {
//    private val _state = MutableLiveData<ResponseState<T>>()
//    val state: LiveData<ResponseState<T>> = _state

    private var action: (suspend()-> ResponseState<T>)? = null
    private var apiCallJob :Job? = null
    fun load(action: suspend () -> ResponseState<T>) {
        this.action = action
        apiCallJob = scope.launch(Dispatchers.IO) {
            stateUpdateCallback(Loading)
            try {
                val response = action()
                updateState(response)
            } catch (e: ValidationErrorException) {
                updateState(ValidationError(e.errorCode, e.message ?: MSG_VALIDATION_ERROR))
            } catch (e: HttpException) {
                updateState(Failed(e.message?:MSG_SOMETHING_WENT_WRONG, e.code()))
            } catch (e: CancellationException) {
                updateState(Cancelled)
            }catch (e: NoNetworkException){
                updateState(Failed(MSG_NO_NETWORK, NoNetworkException.NO_NETWORK_CONNECTION_ERROR_CODE))
            } catch (e: Exception) {
                updateState(Failed(e.message ?: MSG_SOMETHING_WENT_WRONG, 100))
            }
        }
    }

    private suspend fun updateState(state: ResponseState<T>){
        withContext(Dispatchers.Main) {
            stateUpdateCallback(state)
        }
    }

    fun retry() {
        if (action != null) {
            load(action!!)
        }
    }

    fun cancel() {
        apiCallJob?.cancel(CancellationException())
        apiCallJob = null
    }



    companion object {
        const val MSG_SOMETHING_WENT_WRONG = "Something went wrong"
        const val MSG_VALIDATION_ERROR = "Validation error"
        const val MSG_NO_NETWORK = "No internet connection"
    }
}