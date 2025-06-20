package com.grhuan.cat.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import com.grhuan.cat.data.local.CatDatabase
import com.grhuan.cat.data.model.CatResponse
import com.grhuan.cat.data.remote.RetrofitClient
import com.grhuan.cat.data.repository.LocalCatPepository
import com.grhuan.cat.data.repository.RemoteCatRepository
import com.grhuan.cat.databinding.ActivityRandomCatBinding
import com.grhuan.cat.presentation.viewmodel.RandomCatViewModel
import com.grhuan.cat.presentation.viewmodel.RandomCatViewModelFactory

class RandomCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomCatBinding

    // Crie o banco de dados
    private val catDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CatDatabase::class.java,
            "cat-db"
        ).build()
    }
    private val catDao by lazy { catDatabase.catDao() }
    private val localCatRepository by lazy { LocalCatPepository(catDao) }

    private val remoteCatRepository = RemoteCatRepository(RetrofitClient.service)
    private val viewModel: RandomCatViewModel by viewModels() {
        RandomCatViewModelFactory(remoteCatRepository, localCatRepository)
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
        }

        // Observa erros
        viewModel.error.observe(this) { errorMsg ->
            Toast.makeText(this, "Erro: $errorMsg", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadRandomCat()

        binding.nextButton.setOnClickListener {
            Log.d("RandomCatActivity", "Botão NEXT clicado")
            viewModel.loadRandomCat()
        }

        binding.saveButton.setOnClickListener {
            viewModel.saveCat()
            Toast.makeText(this, "Gato salvo!", Toast.LENGTH_SHORT).show()
        }

        binding.imagBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}