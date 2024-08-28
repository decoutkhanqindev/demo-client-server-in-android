package com.example.democlient_serverinandroid.demoapi

sealed interface TodoUiState {
    // state of api
    data object Loading : TodoUiState
    data class SuccessGetTodo(val todo: TodoResponse) : TodoUiState
    data class SuccessGetTodos(val todos: List<TodoResponse>) : TodoUiState
    data class SuccessPostNewPost(val newPostResponse: PostResponse) : TodoUiState
    data class Error(val throwable: Throwable) : TodoUiState
}