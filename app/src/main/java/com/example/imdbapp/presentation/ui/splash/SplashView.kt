package com.example.imdbapp.presentation.ui.splash

import android.window.SplashScreen
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.imdbapp.BuildConfig
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.RedSunsetGradient
import com.example.imdbapp.presentation.theme.UnderTheLakeGradient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashView(
    modifier : Modifier = Modifier,
    onLoadEnded : () -> Unit
){
    val scale = remember { Animatable(0f) }
    LaunchedEffect(true){
        delay(100)
        scale.animateTo(
            1.5f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessMediumLow
            )
        )
        delay(1000)
        onLoadEnded()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(

                if (MaterialTheme.colors.isLight){
                    listOf(
                MaterialTheme.colors.primary)} else {
                    RedSunsetGradient
                }


                    )) ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
            fontFamily = FontFamily.Cursive,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            modifier = modifier.scale(scale.value)
        )
        Text(text = "Version ${BuildConfig.VERSION_NAME}",
            modifier
                .padding(vertical = 20.dp)
                .align(Alignment.BottomCenter), color = Color.White)
    }
    LaunchedEffect(key1 = "splash" ){
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

        }
    }

}