package com.example.flowerpower.viewmodels

import android.net.Uri

data class Plant(
    val id: String,
    val title: String,
    val description: String,
    var imageUrl: Uri?
)


