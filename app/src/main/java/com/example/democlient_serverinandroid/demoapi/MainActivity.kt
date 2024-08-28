package com.example.democlient_serverinandroid.demoapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.democlient_serverinandroid.R
import com.example.democlient_serverinandroid.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}