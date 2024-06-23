package com.example.siperpus.member

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


class UpdateAddMemberActivity : AppCompatActivity(){
    var isiFileUploaded = false
    private val PICK_IMAGE_REQUEST = 1
    private val imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add_member)

        val namaLengkap = findViewById<EditText>(R.id.mFullname)
        val Jkel = findViewById<EditText>(R.id.mGender)
        val alamat = findViewById<EditText>(R.id.mAddress)
        val buttonUploadFile = findViewById<ImageButton>(R.id.mPicture_pict)
        buttonUploadFile.setOnClickListener{
            openFileChooser()
            isiFileUploaded=true}
        val buttonAdd = findViewById<Button>(R.id.btnAction_m)

        buttonAdd.setOnClickListener{
            val Nama = namaLengkap.text.toString().trim()
            val Gender = Jkel.text.toString().trim()
            val Alamat = alamat.text.toString().trim()


            if (Nama.isEmpty()) {
                namaLengkap.error = "Nama harus diisi"
                namaLengkap.requestFocus()
                return@setOnClickListener
            }

            if (Gender.isEmpty()) {
                Jkel.error = "Jenis Kelamin harus diisi"
                Jkel.requestFocus()
                return@setOnClickListener
            }

            if (Alamat.isEmpty()) {
                alamat.error = "Alamat harus diisi"
                alamat.requestFocus()
                return@setOnClickListener
            }
            if (imageUri==null){
                Toast.makeText(this,"Data Berhasil Diunggah", Toast.LENGTH_SHORT).show()
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
            val imageButtonUpload = findViewById<ImageButton>(R.id.mPicture_pict)
            imageButtonUpload.setImageURI(imageUri)
        }
    }
}