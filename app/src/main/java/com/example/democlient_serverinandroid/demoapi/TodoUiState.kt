package com.example.democlient_serverinandroid.demoapi

sealed interface TodoUiState {
    // state of api
    data object Loading : TodoUiState
    data class Success(val todo: TodoResponse) : TodoUiState
    data class Error(val throwable: Throwable) : TodoUiState
}