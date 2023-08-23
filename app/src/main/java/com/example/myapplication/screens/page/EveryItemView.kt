package com.example.myapplication.screens.page

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.R

class EveryItemView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_item)

        val movieName = intent.getStringExtra("movie_name")
        val movieIcon = intent.getIntExtra("movie_image", 0)
        val movieDesc = intent.getStringExtra("movie_description")
        val movieBack = intent.getIntExtra("movie_back", 0)
        updateUI(movieName, movieIcon, movieDesc, movieBack)
        endOfActivity()
    }

    private fun endOfActivity() {
        val btn = findViewById<Button>(R.id.buttonBack)
        btn.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(movieName: String?, movieIcon: Int, movieDesc: String?, movieBack: Int?) {
        val textName = findViewById<TextView>(R.id.imageDescription)
        val iconMovie = findViewById<ImageView>(R.id.image)
        val textDesc = findViewById<TextView>(R.id.textDesc)
        textName.text = movieName
        textDesc.text = movieDesc
        iconMovie.setImageResource(movieIcon)

        when (movieBack) {
            1 -> setImageFromDraw(R.drawable.marvelback, 0.5f)

            2 -> setImageFromDraw(R.drawable.spiderback, 0.7f)

            3 -> setImageFromDraw(R.drawable.amazingback, 0.5f)

            4 -> setImageFromDraw(R.drawable.tomback, 0.5f)
        }
    }

    fun setImageFromDraw(image : Int, brightnessImage : Float){
        val linearLayout = findViewById<LinearLayout>(R.id.linear)
        linearLayout.setBackgroundResource(image)
        val brightnessMatrix = ColorMatrix().apply {
            val brightness = brightnessImage // Новое значение яркости (от 0 до 1)
            set(
                floatArrayOf(
                    brightness, 0f, 0f, 0f, 0f,
                    0f, brightness, 0f, 0f, 0f,
                    0f, 0f, brightness, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        }
        val drawable = ContextCompat.getDrawable(this, image)?.mutate()
        drawable?.colorFilter = ColorMatrixColorFilter(brightnessMatrix)
        linearLayout.background = drawable
    }
}
