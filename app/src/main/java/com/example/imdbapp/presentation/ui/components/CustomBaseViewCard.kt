package com.example.imdbapp.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomBaseViewCard(modifier : Modifier = Modifier, content :
@Composable () -> Unit){
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 3.dp),
        elevation = 7.dp
    ){
        content()
    }

}
