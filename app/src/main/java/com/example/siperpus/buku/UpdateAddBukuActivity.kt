package com.example.siperpus.buku

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.siperpus.R
import com.example.siperpus.databinding.ActivityUpdateAddBukuBinding
import okhttp3.ResponseBody
import retrofit2.Call


class UpdateAddBukuActivity : AppCompatActivity(){
    private lateinit var binding:ActivityUpdateAddBukuBinding
    private var isiFileUploaded = false
    private val PICKIMAGEREQUEST = 1
    private val imageUri: Uri? = null
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAddBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val editTextJudulBuku = findViewById<EditText>(R.id.bTittle)
        val editTextNamaAuthor = findViewById<EditText>(R.id.bAuthor)
        val editTextTahunRilis = findViewById<EditText>(R.id.bDate)
        val buttonUploadFile = findViewById<ImageView>(R.id.bCover)
        buttonUploadFile.setOnClickListener {
            openFileChooser()
            isiFileUploaded = true
        }
        val buttonAdd = findViewById<Button>(R.id.btnAction_b)

        buttonAdd.setOnClickListener {
            Log.d("UpdateAddBukuActivity", "Add Button clicked")
            Toast.makeText(this, "Add Button clicked", Toast.LENGTH_SHORT).show()
            val judulBuku = editTextJudulBuku.text.toString().trim()
            val namaAuthor = editTextNamaAuthor.text.toString().trim()
            val tahunRilis = editTextTahunRilis.text.toString().trim()


            if (judulBuku.isEmpty()) {
                editTextJudulBuku.error = "Judul buku harus diisi"
                editTextJudulBuku.requestFocus()
                return@setOnClickListener
            }

            if (namaAuthor.isEmpty()) {
                editTextNamaAuthor.error = "Nama author harus diisi"
                editTextNamaAuthor.requestFocus()
                return@setOnClickListener
            }

            if (tahunRilis.isEmpty()) {
                editTextTahunRilis.error = "Tahun rilis harus diisi"
                editTextTahunRilis.requestFocus()
                return@setOnClickListener
            }
            if (imageUri == null) {
                Toast.makeText(this, "File harus diunggah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isiFileUploaded) {
                Toast.makeText(this, "File harus diunggah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addBook(judulBuku, namaAuthor, tahunRilis, imageUri!!, contentResolver)

        }

        viewModel.addBookResponse.observe(this@UpdateAddBukuActivity) { response ->
            if (response != null) {
                Log.d("UpdateAddBukuActivity", "Book added successfully: $response")
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Log.d("UpdateAddBukuActivity", "Failed to add book")
                Toast.makeText(this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show()
            }
        }
    }


        private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICKIMAGEREQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICKIMAGEREQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            var imageUri = data.data
            // Anda dapat menampilkan gambar yang dipilih di ImageButton atau ImageView
            val imageButtonUpload = findViewById<ImageView>(R.id.bCover)
            imageButtonUpload.setImageURI(imageUri)
            Log.d("UpdateAddBukuActivity", "Image Uri: $imageUri")
            Toast.makeText(this, "Image selected: $imageUri", Toast.LENGTH_SHORT).show()

        }
    }
}




