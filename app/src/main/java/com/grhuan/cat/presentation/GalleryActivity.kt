package com.grhuan.cat.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.grhuan.cat.data.local.CatDatabase
import com.grhuan.cat.data.repository.LocalCatPepository
import com.grhuan.cat.databinding.ActivityGalleryBinding
import com.grhuan.cat.presentation.adapter.CatAdapter
import com.grhuan.cat.presentation.viewmodel.GalleryViewModel
import com.grhuan.cat.presentation.viewmodel.GalleryViewModelFactory

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var adapter: CatAdapter

    private val catDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            CatDatabase::class.java,
            "cat-db"
        ).build()
    }
    private val catDao by lazy { catDatabase.catDao() }
    private val localCatRepository by lazy { LocalCatPepository(catDao) }

    private val viewModel: GalleryViewModel by viewModels {
        GalleryViewModelFactory(localCatRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CatAdapter()

        binding.rvCats.adapter = adapter
        binding.rvCats.layoutManager = LinearLayoutManager(this)

        viewModel.cats.observe(this) { listaDeGatos ->
            adapter.submitList(listaDeGatos)
        }

        viewModel.localCats()
    }
}