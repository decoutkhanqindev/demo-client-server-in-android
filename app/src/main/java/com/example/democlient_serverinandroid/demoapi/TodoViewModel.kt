package com.example.democlient_serverinandroid.demoapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val todoService: TodoService) : ViewModel() {
    private val todoMutableLiveData: MutableLiveData<TodoUiState> = MutableLiveData<TodoUiState>()
    val todoLiveData: LiveData<TodoUiState> get() = todoMutableLiveData

    fun getTodoExecute() {
        // execute() -> SYNCHRONOUS -> block main thread
        val response: Response<TodoResponse> = todoService.getTodo().execute()

        if (response.isSuccessful) {
            todoMutableLiveData.value = TodoUiState.Success(response.body()!!)
        } else {
            todoMutableLiveData.value = TodoUiState.Error(Throwable(response.message()))
        }
    }

    fun getTodoEnqueue() {
        // enqueue() -> ASYNCHRONOUS -> non block main thread
        todoMutableLiveData.value = TodoUiState.Loading

        todoService.getTodo().enqueue(object : Callback<TodoResponse> {
            override fun onResponse(p0: Call<TodoResponse>, response: Response<TodoResponse>) {
                if (response.isSuccessful) {
                    todoMutableLiveData.value = TodoUiState.Success(response.body()!!)
                    Log.d("TodoViewModel", "onResponse: SUCCESS")
                }
            }

            override fun onFailure(p0: Call<TodoResponse>, throwable: Throwable) {
                todoMutableLiveData.value = TodoUiState.Error(throwable)
                Log.d("TodoViewModel", "onFailure: ERROR")
            }
        })
    }

    fun getTodoSuspend() {
        todoMutableLiveData.value = TodoUiState.Loading

        viewModelScope.launch {
            try {
                val result: TodoResponse = withContext(Dispatchers.IO) {
                    todoService.getTodoSuspend()
                }
                todoMutableLiveData.value = TodoUiState.Success(result)
            } catch (cancel: CancellationException) {
                //  to ensure that coroutine cancellations are handled correctly and propagated up to parent coroutines.
                throw cancel
                // Rethrowing CancellationException (throw cancel) is important because it allows the cancellation signal
                // to propagate up to parent coroutines. If you don't rethrow the exception, the parent coroutine won't know that
                // the child coroutine has been canceled and can continue executing, leading to problems like resource leaks.
            } catch (throwable: Throwable) {
                todoMutableLiveData.value = TodoUiState.Error(throwable)
            }
        }
    }

    fun getTodos() {
        todoMutableLiveData.value = TodoUiState.Loading

        viewModelScope.launch {
            try {
                val response: List<TodoResponse> = withContext(Dispatchers.IO) {
                    todoService.getTodos()
                }
                todoMutableLiveData.value = TodoUiState.SuccessTodos(response)
                Log.d("TodoViewModel", "onResponse: SUCCESS")
            } catch (cancel: CancellationException) {
                throw cancel
            } catch (throwable: Throwable) {
                todoMutableLiveData.value = TodoUiState.Error(throwable)
                Log.d("TodoViewModel", "onFailure: ERROR")
            }
        }
    }
}