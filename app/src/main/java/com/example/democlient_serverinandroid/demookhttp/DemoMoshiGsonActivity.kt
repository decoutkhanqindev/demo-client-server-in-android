package com.example.democlient_serverinandroid.demookhttp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.democlient_serverinandroid.R
import com.example.democlient_serverinandroid.databinding.ActivityDemoMoshiGsonBinding

class DemoMoshiGsonActivity : AppCompatActivity() {
    private val binding: ActivityDemoMoshiGsonBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDemoMoshiGsonBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}