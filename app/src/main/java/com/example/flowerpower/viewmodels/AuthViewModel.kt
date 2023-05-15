package com.example.flowerpower.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.flowerpower.repo.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {

    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()
    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun showToastMessage(context: Context?, message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun performLogin(context: Context, username: String, password: String) {

        FirebaseAuthRepo.login(
            firebaseAuth,
            username,
            password,
            onSuccess = {
                //_userLoginStatus.value = UserLoginStatus.Successful
                showToastMessage(context,"Login successful")
            },
            onFailure = {
               // _userLoginStatus.value = UserLoginStatus.Failure
                showToastMessage(context,"Unable to log in")
            }
        )
    }

    fun createAccount(context: Context, username: String, password: String) {
        FirebaseAuthRepo.signUp(
            firebaseAuth, username, password,
            onSuccess = {
               // _userLoginStatus.value = UserLoginStatus.Successful
                showToastMessage(context,"Account created successfully")
            },
            onFailure = { exception ->
                if(exception is FirebaseAuthUserCollisionException) {
                   // _userLoginStatus.value = UserLoginStatus.Failure
                    showToastMessage(context, "An account with this email already exists")
                } else {
                    showToastMessage(context,"Unable to create account")
              //  _userLoginStatus.value = UserLoginStatus.Failure

                }
            }
        )
    }
}

sealed class UserLoginStatus {
    object Successful: UserLoginStatus()
    object Failure : UserLoginStatus()
}