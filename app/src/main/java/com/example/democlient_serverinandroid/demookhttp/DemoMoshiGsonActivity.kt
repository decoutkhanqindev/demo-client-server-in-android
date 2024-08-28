package com.example.democlient_serverinandroid.demookhttp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.democlient_serverinandroid.databinding.ActivityDemoMoshiGsonBinding

class DemoMoshiGsonActivity : AppCompatActivity() {
    private val binding: ActivityDemoMoshiGsonBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDemoMoshiGsonBinding.inflate(layoutInflater)
    }

    private val viewModel: DemoMoshiGsonViewModel by viewModels<DemoMoshiGsonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.convertBtn.setOnClickListener { viewModel.parse() }
        viewModel.stateLiveData.observe(this) { binding.convertText.text = it }
    }
}