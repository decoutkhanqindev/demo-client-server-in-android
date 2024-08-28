package com.example.democlient_serverinandroid.demoapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
}