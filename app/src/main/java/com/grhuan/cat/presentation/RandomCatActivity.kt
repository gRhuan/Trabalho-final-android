package com.grhuan.cat.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.grhuan.cat.data.remote.RetrofitClient
import com.grhuan.cat.data.repository.CatRepository
import com.grhuan.cat.databinding.ActivityRandomCatBinding
import com.grhuan.cat.presentation.adapter.RandomCatViewModelFactory

class RandomCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatBinding
    private val repository = CatRepository(RetrofitClient.service)

    private val viewModel: RandomCatViewModel by viewModels() {
        RandomCatViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observa o gato
        viewModel.cat.observe(this) { catResponse ->
            Glide.with(this)
                .load(catResponse.url)
                .into(binding.catImageView)
           //binding.catNameTextView.text = catResponse.name
        }

        // Observa erros
        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, "Erro: $errorMsg", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadRandomCat()

        binding.nextButton.setOnClickListener {
            viewModel.loadRandomCat()
        }
    }
}
