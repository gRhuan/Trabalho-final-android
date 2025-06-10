package com.grhuan.cat.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.grhuan.cat.databinding.ActivityRandomCatBinding

class RandomCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRandomCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}