package com.grhuan.cat.presentation.gallery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grhuan.cat.databinding.ActivityGalleryBinding
import com.grhuan.cat.presentation.activity.MenuActivity
import com.grhuan.cat.presentation.gallery.adapter.CatAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryBinding
    private lateinit var adapter: CatAdapter

    private val viewModel: GalleryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CatAdapter { catId ->
            AlertDialog.Builder(this)
                .setTitle("Deseja excluir ?")
                .setPositiveButton("Sim") { dialog, _ ->
                    viewModel.removeCat(catId)
                    dialog.dismiss()
                }
                .setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.rvCats.adapter = adapter
        binding.rvCats.layoutManager = LinearLayoutManager(this)

        viewModel.cats.observe(this) { listCats ->
            adapter.submitList(listCats)
        }

        viewModel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        viewModel.getCats()
    }
}