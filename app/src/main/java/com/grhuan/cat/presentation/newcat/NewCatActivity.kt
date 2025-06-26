package com.grhuan.cat.presentation.newcat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.grhuan.cat.databinding.ActivityNewCatBinding
import com.grhuan.cat.presentation.activity.MenuActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewCatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCatBinding

    private val viewModel: NewCatViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewCatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.cat.observe(this) { catResponse ->
            Glide.with(this)
                .load(catResponse.url)
                .into(binding.ivCat)
        }

        viewModel.toastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadingState.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.catImageView.visibility = View.INVISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.catImageView.visibility = View.VISIBLE
            }
        }

        viewModel.newCat()

        binding.nextButton.setOnClickListener {
            viewModel.newCat()
        }

        binding.saveButton.setOnClickListener {
            viewModel.saveCat()
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}