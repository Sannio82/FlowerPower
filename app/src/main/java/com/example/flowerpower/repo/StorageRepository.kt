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
        imageUri: Uri?,
        context: Context
    ) {
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbPlants: CollectionReference = db.collection("plants")
        val plantId = UUID.randomUUID().toString()

        val plant = Plant(plantId, plantName, plantDescription, null)

        if (imageUri != null) {
            val storageRef = FirebaseStorage.getInstance().getReference("images/$plantId")
            storageRef.putFile(imageUri)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        plant.imageUrl = uri
                        savePlantData(dbPlants, plantId, plant, context)
                    }
                        .addOnFailureListener { e ->
                            showToast(context, "Failed to get image URL: $e")
                        }
                }
                .addOnFailureListener { e ->
                    showToast(context, "Failed to upload image: $e")
                }
        } else {
            savePlantData(dbPlants, plantId, plant, context)
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun savePlantData(
        dbPlants: CollectionReference,
        plantId: String,
        plant: Plant,
        context: Context
    ) {
        dbPlants.document(plantId).set(plant)
            .addOnSuccessListener {
                showToast(
                    context,
                    "Your plant has been added successfully!"
                )
            }
            .addOnFailureListener { e ->
                showToast(
                    context,
                    "Failed to add plant: $e"
                )
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
                        document.getString("id") ?: "",
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
    fun deleteData(plantId: String, context: Context) {
        val db = FirebaseFirestore.getInstance()
        val plantRef = db.collection("plants").document(plantId)

        plantRef.delete()
            .addOnSuccessListener {
                showToast(context,"Plant deleted successfully")
            }
            .addOnFailureListener{e ->
                showToast(context, "Failed to delete plant: $e")
            }
    }
}



