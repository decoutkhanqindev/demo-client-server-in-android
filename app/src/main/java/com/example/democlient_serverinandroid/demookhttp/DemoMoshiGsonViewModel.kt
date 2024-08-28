package com.example.democlient_serverinandroid.demookhttp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoMoshiGsonViewModel(application: Application) : AndroidViewModel(application) {
    private val moshi: Moshi by lazy { buildMoshi() }
    private val gson: Gson by lazy { buildGson() }

    private val stateMutableLiveData: MutableLiveData<String?> = MutableLiveData<String?>()
    private val stateLiveData: MutableLiveData<String?> = stateMutableLiveData

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
                Log.d("DemoMoshiGsonViewModel", "parse: FAILED")
                stateMutableLiveData.value = e.message.toString()
            }
        }
    }

    private suspend fun parseJsonInternal(): String = withContext(Dispatchers.IO) {
        TODO()
    }

    private fun buildMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private fun buildGson(): Gson {
        TODO("Not yet implemented")
    }
}