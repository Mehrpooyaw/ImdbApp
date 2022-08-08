package com.example.imdbapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.IMdbAppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomDivider(modifier : Modifier = Modifier) {
    Divider(
        modifier = modifier.padding(horizontal = 10.dp,vertical = 10.dp).fillMaxWidth(),
        thickness = 0.5.dp,
        color = AlmostSilver
    )
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomDividerPreview(){
    IMdbAppTheme() {
        CustomDivider()
    }
}