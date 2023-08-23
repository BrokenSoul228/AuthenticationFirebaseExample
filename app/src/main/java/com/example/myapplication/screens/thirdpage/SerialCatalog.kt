package com.example.myapplication.screens.thirdpage

data class SerialCatalog(
    val name: String,
    val genre: List<String>,
    val intro: Int,
    val duration: String ,
    val descriptionMovie : String,
    val backgroundMode : Int?
)