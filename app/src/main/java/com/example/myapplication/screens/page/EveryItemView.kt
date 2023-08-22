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

    fun endOfActivity() {
        val btn = findViewById<Button>(R.id.buttonBack)
        btn.setOnClickListener {
            finish()
        }
    }

    fun updateUI(movieName: String?, movieIcon: Int, movieDesc: String?, movieBack: Int?) {
        val textName = findViewById<TextView>(R.id.imageDescription)
        val iconMovie = findViewById<ImageView>(R.id.image)
        val textDesc = findViewById<TextView>(R.id.textDesc)
        textName.text = movieName
        textDesc.text = movieDesc
        iconMovie.setImageResource(movieIcon)
        updateBackground(movieBack)
    }
    fun updateBackground(movieBack : Int?) {
        val linearLayout = findViewById<LinearLayout>(R.id.linear)
        if (movieBack == 1) {
            val brightnessMatrix = ColorMatrix().apply {
                val brightness = 0.5f // Новое значение яркости (от 0 до 1)
                set(
                    floatArrayOf(
                        brightness, 0f, 0f, 0f, 0f,
                        0f, brightness, 0f, 0f, 0f,
                        0f, 0f, brightness, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
            }
            // Применяем фильтр к изображению
            val drawable = ContextCompat.getDrawable(this, R.drawable.marvelback)?.mutate()
            drawable?.colorFilter = ColorMatrixColorFilter(brightnessMatrix)
            linearLayout.background = drawable
        }
    }
}
