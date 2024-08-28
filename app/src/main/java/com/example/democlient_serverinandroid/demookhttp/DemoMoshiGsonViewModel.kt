package com.example.democlient_serverinandroid.demookhttp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.democlient_serverinandroid.demoapi.ServiceLocator
import com.example.democlient_serverinandroid.demoapi.TodoService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException

class DemoMoshiGsonViewModel(application: Application) : AndroidViewModel(application) {
    private val moshi: Moshi by lazy { buildMoshi() }
    private val gson: Gson by lazy { buildGson() }

    private val todoService: TodoService by lazy {
        ServiceLocator.todoService
    }

    private val stateMutableLiveData = MutableLiveData<String?>(null)
    val stateLiveData: LiveData<String?> get() = stateMutableLiveData

    fun parse() {
        viewModelScope.launch {
            stateMutableLiveData.value = "Loading..."

            try {
                // stateMutableLiveData.value = parseJsonInternal()
                stateMutableLiveData.value = todoService.getTodos().toString()
                Log.d("DemoMoshiGsonViewModel", "parse: SUCCESS ")
            } catch (cancel: CancellationException) {
                stateMutableLiveData.value = null
                throw cancel
            } catch (e: Exception) {
                Log.d("DemoMoshiGsonViewModel", "parse: ${e.message}")
                stateMutableLiveData.value = e.message.orEmpty()
            }
        }
    }

    private suspend fun parseJsonInternal(): String = withContext(Dispatchers.IO) {
        val jsonString: String =
            getApplication<Application>().readAssetAsText(fileName = "students_list_invalid_null.json")
        // moshi
        // val adapter: JsonAdapter<List<Student>> = moshi.adapter<List<Student>>()
        // val students: List<Student>? = adapter.fromJson(jsonString)

        // gson
        // val student: Student? = gson.fromJson(jsonString, Student::class.java)
        val students: List<Student>? = gson.fromJson(
            jsonString, object : TypeToken<List<Student>>() {}.type
        )
        students?.toString() ?: "<null>"
    }

    private fun buildMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private fun buildGson(): Gson = Gson()
}