package com.example.siperpus.buku

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.siperpus.R
import dagger.hilt.android.AndroidEntryPoint


class UpdateAddBukuActivity : AppCompatActivity(){
    var isiFileUploaded = false
    private val PICK_IMAGE_REQUEST = 1
    private val imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add_buku)

        val editTextJudulBuku = findViewById<EditText>(R.id.bTittle)
        val editTextNamaAuthor = findViewById<EditText>(R.id.bAuthor)
        val editTextTahunRilis = findViewById<EditText>(R.id.bDate)
        val buttonUploadFile = findViewById<ImageButton>(R.id.bCover)
        buttonUploadFile.setOnClickListener{
            openFileChooser()
            isiFileUploaded=true}
        val buttonAdd = findViewById<Button>(R.id.btnAction_b)

        buttonAdd.setOnClickListener{
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
            if (imageUri==null){
                Toast.makeText(this,"File YONU",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isiFileUploaded) {
                Toast.makeText(this, "File harus diunggah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Jika semua field sudah diisi dan file sudah diunggah
            Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        }

    }
    private fun openFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            // Anda dapat menampilkan gambar yang dipilih di ImageButton atau ImageView
            val imageButtonUpload = findViewById<ImageButton>(R.id.bCover)
            imageButtonUpload.setImageURI(imageUri)
        }
    }

}