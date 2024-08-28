package com.example.democlient_serverinandroid.demoapi

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.democlient_serverinandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: TodoViewModel by viewModels<TodoViewModel>(factoryProducer = {
        viewModelFactory {
            addInitializer(clazz = TodoViewModel::class) {
                TodoViewModel(todoService = ServiceLocator.todoService)
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.callApiBtn.setOnClickListener {
            viewModel.postNewPost()
        }

        viewModel.todoLiveData.observe(this) { response: TodoUiState ->
            when (response) {
                is TodoUiState.Loading -> binding.responseText.text = "Loading...."

                is TodoUiState.SuccessGetTodo -> binding.responseText.text =
                    response.todo.title.toString()

                is TodoUiState.SuccessGetTodos -> binding.responseText.text =
                    response.todos.joinToString { it.title.toString() }

                is TodoUiState.SuccessPostNewPost -> binding.responseText.text =
                    response.newPostResponse.toString()

                is TodoUiState.Error -> binding.responseText.text =
                    "Error: ${response.throwable.message}"
            }
        }
    }
}