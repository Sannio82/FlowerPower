package com.example.flowerpower.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.flowerpower.R
import com.example.flowerpower.repo.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel: ViewModel() {

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun showToastMessage(context: Context?, messageResId: Int?) {
        val message = messageResId?.let { context?.getString(it) }
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun performLogin(context: Context, username: String, password: String) {

        FirebaseAuthRepo.login(
            firebaseAuth,
            username,
            password,
            onSuccess = {
                showToastMessage(context, R.string.log_in_successful)
            },
            onFailure = {
                showToastMessage(context,R.string.unable_log_in)
            }
        )
    }
    fun signOut(context: Context) {
        FirebaseAuthRepo.signOut(firebaseAuth) {
            showToastMessage(context, R.string.log_out_successful)
        }
    }


    fun createAccount(context: Context, username: String, password: String) {
        FirebaseAuthRepo.signUp(
            firebaseAuth, username, password,
            onSuccess = {
                showToastMessage(context,R.string.account_success)
            },
            onFailure = { exception ->
                if(exception is FirebaseAuthUserCollisionException) {
                    showToastMessage(context, R.string.account_exists)
                } else {
                    showToastMessage(context,R.string.unable_create)
                }
            }
        )
    }
}