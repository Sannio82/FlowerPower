package com.example.flowerpower.viewmodels

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import com.example.flowerpower.R
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Plant(
    val title: String,
    val description: String,
    val image: Int,
    var timer: CountDownTimer? = null,
    val wateringFrequencyDays: Int,
    var lastWateredDate: LocalDate? = null
)

val plantList = listOf(
    Plant("Kaktus", "Vattna varje fredag", R.drawable.screenshot, null, 7),
    Plant("Lime", "Vattna varje Måndag", R.drawable.lemon_tree, null, 15),
    Plant("Gurka", "Vattna varje Torsdag", R.drawable.lemon_tree, null, 16),
)

