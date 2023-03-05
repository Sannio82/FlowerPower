package com.example.flowerpower.viewmodel

import androidx.lifecycle.ViewModel

class WelcomeScreenViewModel: ViewModel ()

fun handleActions(action: WelcomeScreenActions) {
    when (action) {
        WelcomeScreenActions.ButtonClicked -> navigate()
    }
}

private fun navigate() {

}