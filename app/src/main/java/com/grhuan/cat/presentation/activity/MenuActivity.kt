package com.grhuan.cat.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.grhuan.cat.databinding.ActivityMenuBinding
import com.grhuan.cat.presentation.gallery.GalleryActivity
import com.grhuan.cat.presentation.newcat.NewCatActivity
import com.grhuan.cat.utils.PreferencesUtils
import org.koin.android.ext.android.inject

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    private val preferencesUtils : PreferencesUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNewCat.setOnClickListener {
            val intent = Intent(this, NewCatActivity::class.java)
            startActivity(intent)
        }

        binding.btGallery.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        binding.ivGear.setOnClickListener {
            showApiKeyDialog()
        }
    }

    fun showApiKeyDialog() {
        val editText = EditText(this)
        editText.hint = "Digite sua API Key"

        AlertDialog.Builder(this)
            .setTitle("Definir API Key")
            .setView(editText)
            .setPositiveButton("Salvar") { dialog, _ ->
                val apiKey = editText.text.toString()
                if (apiKey.isNotBlank()) {
                    preferencesUtils.saveKeyApi(apiKey)
                    Toast.makeText(this, "Chave salva!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Digite uma chave válida.", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}