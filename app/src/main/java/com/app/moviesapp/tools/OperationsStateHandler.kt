package com.app.moviesapp.tools
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.moviesapp.data.ValidationErrorException
import com.app.moviesapp.states.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OperationsStateHandler<T>(
    private val scope: CoroutineScope
) {
    private val _state = MutableLiveData<ResponseState<T>>()
    val state: LiveData<ResponseState<T>> = _state

    private var action: (suspend()-> ResponseState<T>)? = null
    fun load(action: suspend () -> ResponseState<T>) {
        this.action = action
        scope.launch {
            try {
                _state.postValue(ResponseState.Loading)
                val response = action()
                _state.postValue(response)
            } catch (e: ValidationErrorException) {
                _state.postValue(ResponseState.ValidationError(e.errorCode, e.message ?: "Something went wrong"))
            } catch (e: HttpException) {
                e.printStackTrace()
                ResponseState.Failed(e.message(), e.code())
            } catch (e: Exception) {
                e.printStackTrace()
                _state.postValue(ResponseState.Failed(e.message ?: "Something went wrong", 100))
            }
        }
    }

    fun retry() {
        if (action != null) {
            load(action!!)
        }
    }
}