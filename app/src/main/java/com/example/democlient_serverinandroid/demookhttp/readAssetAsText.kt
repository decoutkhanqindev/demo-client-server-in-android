package com.example.democlient_serverinandroid.demookhttp

import android.content.Context
import androidx.annotation.WorkerThread

@WorkerThread // background thread
fun Context.readAssetAsText(fileName: String): String =
    assets.open(fileName).bufferedReader().use { it.readText() }