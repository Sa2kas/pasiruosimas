package com.example.egzaminui

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_grid.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

/*lateinit var currentPhotoPath: String
lateinit var photoPath: String
val REQUEST_TAKE_PHOTO = 1
val REQUEST_IMAGE_CAPTURE= 1*/
lateinit var bmp: Bitmap

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val imageView = findViewById<ImageView>(R.id.imageView)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)


        take_btn.setOnClickListener {

            //takePicture()

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        upload_btn.setOnClickListener {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)

        }

        save_btn.setOnClickListener {
            //saveToGallery()
            //dispatchTakePictureIntent()
            //galleryAddPic()

            if (bmp != null) {
                saveMediaToStorage(bmp)
                //Toast.makeText(this , "veikia" , Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this , "gaidys" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123){
            bmp = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bmp)
        }
        else if (requestCode == 456) {
            imageView.setImageURI(data?.data)
        }
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {*/
        // getting the contentResolver
        this.contentResolver?.also { resolver ->

            // Content resolver will process the contentvalues
            val contentValues = ContentValues().apply {

                // putting file information in content values
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            // Inserting the contentValues to
            // contentResolver and getting the Uri
            val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            // Opening an outputstream with the Uri that we got
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }
        /*} else {
            // These for devices running on android < Q
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }*/

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this , "Captured View and saved to Gallery" , Toast.LENGTH_SHORT).show()
        }
    }

    /*private fun galleryAddPic() {

        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }*/

    //saugojimui
   /* @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", *//* prefix *//*
                ".jpg", *//* suffix *//*
                storageDir *//* directory *//*
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }*/
    //saugojimui
    /*private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File

                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.example.android.fileprovider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }*/

    /*private fun takePicture() {
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
    }*/

    /*private fun saveToGallery() {
        val bitmapDrawable = imageView.drawable as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap
        var outputStream: FileOutputStream? = null
        val file = Environment.getExternalStorageDirectory()
        val dir = File(file.absolutePath)
        dir.mkdirs()
        val filename = String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, filename)
        try {
            outputStream = FileOutputStream(outFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        try {
            outputStream?.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            outputStream?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

}