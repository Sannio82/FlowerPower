package com.example.flowerpower.views.button

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.flowerpower.R
import com.example.flowerpower.ui.theme.Blue

@Composable
fun ButtonBack(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column() {
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = Blue
            )
        }
    }
}



