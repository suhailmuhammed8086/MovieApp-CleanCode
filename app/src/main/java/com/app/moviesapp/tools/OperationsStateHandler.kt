package com.app.moviesapp.tools
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import retrofit2.HttpException
import java.util.concurrent.CancellationException

class OperationsStateHandler<T>(
    private val scope: CoroutineScope,
) {
    private val _state = MutableLiveData<ResponseState<T>>()
    val state: LiveData<ResponseState<T>> = _state

    private var action: (suspend()-> ResponseState<T>)? = null
    private var apiCallJob :Job? = null
    fun load(action: suspend () -> ResponseState<T>) {
        this.action = action
        apiCallJob = scope.launch(Dispatchers.IO) {
            try {
                _state.postValue(Loading)
                val response = action()
                _state.postValue(response)
            } catch (e: ValidationErrorException) {
                _state.postValue(ValidationError(e.errorCode, e.message ?: "Validation Error"))
            } catch (e: HttpException) {
                _state.postValue(Failed(e.message(), e.code()))
            } catch (e: CancellationException) {
                _state.postValue(Cancelled)
            } catch (e: Exception) {
                _state.postValue(Failed(e.message ?: "Something went wrong", 100))
            }
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
}