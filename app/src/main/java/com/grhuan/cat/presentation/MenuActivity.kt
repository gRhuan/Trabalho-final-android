package com.grhuan.cat.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grhuan.cat.databinding.ActivityMenuBinding
import com.grhuan.cat.presentation.RandomCatActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRandom.setOnClickListener {
            val intent = Intent(this, RandomCatActivity::class.java)
            startActivity(intent)
        }

        binding.buttonFavorites.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }
    }
}