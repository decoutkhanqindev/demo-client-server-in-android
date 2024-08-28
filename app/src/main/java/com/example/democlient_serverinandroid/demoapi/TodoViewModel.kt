package com.example.democlient_serverinandroid.demoapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(private val todoService: TodoService) : ViewModel() {
    private val todoMutableLiveData: MutableLiveData<TodoUiState> = MutableLiveData<TodoUiState>()
    val todoLiveData: LiveData<TodoUiState> get() = todoMutableLiveData

    // execute() -> SYNCHRONOUS -> block main thread -> UI thread
    fun getTodoExecute() {
        val response: Response<TodoResponse> = todoService.getTodo().execute()

        if (response.isSuccessful) {
            todoMutableLiveData.value = TodoUiState.Success(response.body()!!)
        } else {
            todoMutableLiveData.value = TodoUiState.Error(Throwable(response.message()))
        }
    }

    // enqueue() -> ASYNCHRONOUS -> non block main thread -> UI thread
    fun getTodoEnqueue() {
        todoMutableLiveData.value = TodoUiState.Loading

        todoService.getTodo().enqueue(object : Callback<TodoResponse> {
            override fun onResponse(p0: Call<TodoResponse>, response: Response<TodoResponse>) {
                if (response.isSuccessful) {
                    todoMutableLiveData.value = TodoUiState.Success(response.body()!!)
                }
            }

            override fun onFailure(p0: Call<TodoResponse>, throwable: Throwable) {
                todoMutableLiveData.value = TodoUiState.Error(throwable)
            }
        })
    }
}