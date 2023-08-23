package com.example.myapplication.screens.anime

data class AnimeCatalog(
    val name: String,
    val genre: List<String>,
    val intro: Int,
    val duration: String ,
    val descriptionMovie : String,
    val backgroundMode : Int?
)