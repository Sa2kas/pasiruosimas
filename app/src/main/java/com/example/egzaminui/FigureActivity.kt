package com.example.egzaminui

import android.content.ContentValues
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import kotlinx.android.synthetic.main.activity_figure.*
import java.io.OutputStream
import java.util.*


class FigureActivity : AppCompatActivity() {
    lateinit var canvas: Canvas
    //juodam backui
    //lateinit var bit: Bitmap
    //lateinit var fView: ImageView //nereik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figure)
        //var fView = findViewById<ImageView>(R.id.figureView)
        //var bit = fView.drawToBitmap(Bitmap.Config.ARGB_8888) //nereik
        //backo spalvai
        figureView.setBackgroundDrawable(this.resources.getDrawable(R.drawable.background))
        val bit = Bitmap.createBitmap(
            figureView.layoutParams.width,
            figureView.layoutParams.height,
            Bitmap.Config.ARGB_8888
        )

        canvas = Canvas(bit)


        clear_btn.setOnClickListener {
            //isvalo viska kas nupiesta su pirstu
            //figureView.resetPaths()

            //isvalo canvas'a nuo figuru (uzspalvina visa canvas)
            //spalva black nes canvas yra juodas
            canvas.drawColor(Color.BLACK)
        }

        //rotate yra .xml failas kuriame aprasytas sukimasis
        //galima animuoti ir be failo
        var rotation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        rotation.fillAfter = true
        figureView.startAnimation(rotation)

        figureView.setOnClickListener {
            //sitas padaro random true/false
            //nuo true/false priklauso ar figura bus pilnavidure
            val random = Random()
            val r = random.nextBoolean()

            //padaro random spalva kaskart palietus fugureView
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            //nupiesia ant virsaus kvadrata su random spalva ir su uzpildu arba be
            drawRectangle(color, r)

        }

        draw_btn.setOnClickListener {
            drawTriangle()
        }

        stop_btn.setOnClickListener {
            //sustabdo judejima figuros ir pastato i pradine pozicija
            //ir po to nebeveikia figuros spalvos keitimas palieciant
            figureView.clearAnimation()
        }

        save_draw_btn.setOnClickListener {

            var f = findViewById<ImageView>(R.id.figureView)
            var bitmap = f.drawToBitmap(Bitmap.Config.ARGB_8888)
            saveMediaToStorage(bitmap)
        }
        figureView.setImageDrawable(BitmapDrawable(resources, bit))
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null
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
            val imageUri: Uri? = resolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )

            // Opening an outputstream with the Uri that we got
            fos = imageUri?.let { resolver.openOutputStream(it) }
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Picture saved to Gallery", Toast.LENGTH_SHORT).show()
        }


    }

    fun drawTriangle(){
        //        !!!
        //nustato piestuko spalva
        //figureView.setBrushColor(getColor(R.color.teal_200))

        //nustato piestuko stori
        //figureView.setWidth(10f)

        var left = 0
        var top = 0
        var right = figureView.width
        var bottom = figureView.height
        var paint = Paint()
            paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.LTGRAY
        paint.strokeWidth = 10f

        val point1_draw = Point(right / 2, 0)
        val point2_draw = Point(0, bottom)
        val point3_draw = Point(right, bottom)
        val path = Path()
        path.moveTo(point1_draw.x.toFloat(), point1_draw.y.toFloat())
        path.lineTo(point2_draw.x.toFloat(), point2_draw.y.toFloat())
        path.lineTo(point3_draw.x.toFloat(), point3_draw.y.toFloat())
        path.lineTo(point1_draw.x.toFloat(), point1_draw.y.toFloat())
        path.close()
        canvas.drawPath(path, paint)
    }

    fun drawRectangle(clr: Int, random: Boolean){
        var left = 0
        var top = 0
        var right = figureView.width / 2
        var bottom = figureView.height / 2
        var paint = Paint()
        if (random){
            paint.style = Paint.Style.STROKE
        } else{
            paint.style = Paint.Style.FILL_AND_STROKE
        }
        paint.color = clr
        paint.strokeWidth = 10f
        canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)


    }

}