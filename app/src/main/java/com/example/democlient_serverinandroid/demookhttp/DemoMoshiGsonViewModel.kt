package com.example.democlient_serverinandroid.demookhttp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.cancellation.CancellationException


class DemoMoshiGsonViewModel(application: Application) : AndroidViewModel(application) {
    private val moshi: Moshi by lazy { buildMoshi() }
    private val gson: Gson by lazy { buildGson() }

    private val stateMutableLiveData = MutableLiveData<String?>(null)
    val stateLiveData: LiveData<String?> get() = stateMutableLiveData

    fun parse() {
        viewModelScope.launch {
            stateMutableLiveData.value = "Loading..."

            try {
                stateMutableLiveData.value = parseJsonInternal()
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

    @OptIn(ExperimentalStdlibApi::class)
    private suspend fun parseJsonInternal(): String = withContext(Dispatchers.IO) {
        val jsonString: String =
            getApplication<Application>().readAssetAsText(fileName = "student.json")
        val adapter: JsonAdapter<Student> = moshi.adapter<Student>()
        val student: Student? = adapter.fromJson(jsonString)
        student?.toString() ?: "<null>"
    }

    private fun buildMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private fun buildGson(): Gson = Gson()
}