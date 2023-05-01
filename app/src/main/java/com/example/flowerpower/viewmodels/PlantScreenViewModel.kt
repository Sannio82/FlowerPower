package com.example.flowerpower.viewmodels

data class Plant(
    val title: String,
    val description: String,
) {
    constructor() : this("", "")
}

val plantList = listOf(
    Plant("Kaktus", "Vattna varje fredag"),
    Plant("Lime", "Vattna varje Måndag"),
    Plant("Gurka", "Vattna varje Torsdag"),
)

/*Plant("Kaktus", "Vattna varje fredag", R.drawable.lemon_tree),
Plant("Lime", "Vattna varje Måndag", R.drawable.lemon_tree),
Plant("Gurka", "Vattna varje Torsdag", R.drawable.lemon_tree),*/

