package com.example.flowerpower.viewmodels

import com.example.flowerpower.R

data class Plant(
    val title: String,
    val description: String,
    val image: Int,
)

val plantList = listOf(
    Plant("Kaktus", "Vattna varje fredag", R.drawable.lemon_tree),
    Plant("Lime", "Vattna varje Måndag", R.drawable.lemon_tree),
    Plant("Gurka", "Vattna varje Torsdag", R.drawable.lemon_tree),
)

