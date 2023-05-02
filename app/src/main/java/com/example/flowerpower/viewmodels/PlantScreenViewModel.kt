package com.example.flowerpower.viewmodels

import android.net.Uri

data class Plant(
    val title: String,
    val description: String,
    val imageUrl: Uri = Uri.parse("")
) {
    constructor() : this("", "", Uri.parse("") )
}

