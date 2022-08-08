package com.example.imdbapp.presentation.ui.home.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeTitleAndSeeMoreRow(
    modifier : Modifier = Modifier,
    title : String,
    tint : Color = MaterialTheme.colors.primary,
    isSeeMoreAvailable : Boolean = true,
    onSeeMoreClick :() -> Unit,
){
    Row(
        modifier = modifier.fillMaxWidth(1f).padding(horizontal = 17.dp),
        horizontalArrangement = if (isSeeMoreAvailable) Arrangement.SpaceBetween else Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, color = tint,)
        if (isSeeMoreAvailable) {
            Text(
                "See more",
                color = MaterialTheme.colors.onBackground,
                modifier = modifier.clickable {
                    onSeeMoreClick()
                },
                style = MaterialTheme.typography.button)
        }

    }

}