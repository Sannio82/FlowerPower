package com.example.flowerpower.viewmodels

import com.example.flowerpower.R

data class Plant(
    val title: String,
    val description: String,
    val image: Int,
   // val timer: CountDownTimer
)

val plantList = listOf(
    Plant("Kaktus", "Vattna varje fredag", R.drawable.screenshot),
    Plant("Lime", "Vattna varje Måndag", R.drawable.screenshot),
    Plant("Gurka", "Vattna varje Torsdag", R.drawable.screenshot),
)
