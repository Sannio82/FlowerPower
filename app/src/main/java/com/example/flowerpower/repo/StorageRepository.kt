package com.example.flowerpower.repo

import android.content.Context
import android.widget.Toast
import com.example.flowerpower.viewmodels.Plant
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

fun addDataToFirebase(
    plantName: String,
    plantDescription: String,
    context: Context
) {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val dbPlants: CollectionReference = db.collection("plants")
    val plants = Plant(plantName, description = plantDescription)

    dbPlants.add(plants).addOnSuccessListener {
        Toast.makeText(
            context,
            "Your plant has been added to Firebase Firestore",
            Toast.LENGTH_SHORT
        ).show()

    }.addOnFailureListener { e ->
        Toast.makeText(context, "Fail to add plant \n$e", Toast.LENGTH_SHORT).show()
    }
}