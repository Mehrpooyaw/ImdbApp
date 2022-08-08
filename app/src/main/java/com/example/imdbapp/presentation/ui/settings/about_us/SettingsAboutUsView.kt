package com.example.imdbapp.presentation.ui.settings.about_us

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.TitaniumGradient
import com.example.imdbapp.presentation.ui.components.BackButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsAboutUsView(
    modifier : Modifier = Modifier,

) {
    Scaffold() {
        Column( 
            modifier = modifier
                .fillMaxSize()
                .background(Brush.linearGradient(TitaniumGradient))
                .verticalScroll(rememberScrollState())
        ) {
            BackButton(
                tint = AlmostSilver
            ){}
            Card(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f),
                elevation = 10.dp,
                shape = RoundedCornerShape(5),
                backgroundColor = TitaniumGradient[0]
            ){

            }
            Divider()
            Column(modifier.fillMaxSize()) {
                
            }
            
        }
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsAboutUsViewPreview() {
    IMdbAppTheme {
        SettingsAboutUsView()
    }
}