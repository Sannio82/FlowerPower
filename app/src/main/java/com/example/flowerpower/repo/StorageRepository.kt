package com.example.flowerpower.repo

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import com.example.flowerpower.viewmodels.Plant
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

object StorageRepository {
    fun addDataToFirebase(
        plantName: String,
        plantDescription: String,
        imageUri: Uri,
        context: Context
    ) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbPlants: CollectionReference = db.collection("plants")
        val plantId = UUID.randomUUID().toString()

        val storageRef = FirebaseStorage.getInstance().getReference("images/$plantId")
        storageRef.putFile(imageUri)
            .addOnSuccessListener { uploadTask ->
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    val plant = Plant(plantName, plantDescription, uri)
                    dbPlants.document(plantId).set(plant)
                        .addOnSuccessListener {
                            Toast.makeText(
                                context,
                                "Your plant and image has been added successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Failed to add plant \n$e", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Failed to upload image \n$e", Toast.LENGTH_SHORT).show()
            }
    }

    fun readDataFromFirestore(context: Context, callback: (List<Plant>) -> Unit) {

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbPlants = db.collection("plants")

        dbPlants.get()
            .addOnSuccessListener { querySnapshot ->
                val plants = mutableStateListOf<Plant>()
                for (document in querySnapshot) {
                    val imageUrl = document.getString("imageUrl")
                    val uri = if (!imageUrl.isNullOrEmpty()) Uri.parse(imageUrl) else Uri.EMPTY
                    val plant = Plant(
                        document.getString("title") ?: "",
                        document.getString("description") ?: "",
                        uri
                    )
                    plants.add(plant)
                }
                callback(plants)
            }
            .addOnFailureListener {
                Toast.makeText(context, "Fail to get data", Toast.LENGTH_SHORT).show()
            }
    }
}





