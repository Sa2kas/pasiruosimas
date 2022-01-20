package com.example.egzaminui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_grid.*
import java.io.File
import java.io.IOException

lateinit var photoPath: String
val REQUEST_TAKE_PHOTO = 1

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        take_btn.setOnClickListener {

            takePicture()
        }

        upload_btn.setOnClickListener {


        }

        save_btn.setOnClickListener {

        }
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        
        if(intent.resolveActivity(packageManager) != null){
            
            var photoFile: File? = null
            try{
                photoFile = createImageFile()
            }catch (e:IOException){}
            if(photoFile != null){

                val photoUri = FileProvider.getUriForFile(
                    this,
                    "com.example.android.fileprovider",
                    photoFile
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent, REQUEST_TAKE_PHOTO)
            }
        }
    }

    private fun createImageFile(): File? {
        val fileName = "MyPicture"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            fileName,
            ".jpg",
            storageDir
        )
        photoPath = image.absolutePath

        return image
    }
}