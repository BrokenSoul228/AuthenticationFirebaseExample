package com.example.myapplication.screens.movie

data class MovieCatalog(
    val name: String,
    val genre: List<String>,
    val intro: Int,
    val duration: String ,
    val descriptionMovie : String,
    val backgroundMode : Int?
)