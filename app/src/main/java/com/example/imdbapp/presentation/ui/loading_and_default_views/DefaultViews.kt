package com.example.imdbapp.presentation.ui.loading_and_default_views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.components.BackButton
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.presentation.ui.util.no_connection_error

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DefaultBottomPageView(
    modifier : Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraLight, fontSize = 12.sp, color =
                    if (MaterialTheme.colors.isLight)
                        Color.DarkGray
                        else
                    Color(
                        0xFFFDD8A3
                    )
                    )) {
                        append("By")
                    }
                    withStyle(SpanStyle(MaterialTheme.colors.primary, fontSize = 22.sp, fontFamily = FontFamily.Cursive)){
                        append(" Mehrpooya.a")
                    }
                },
            )
            Image(
                painterResource(id = R.drawable.mehrpooya_icon),
                contentDescription = "",
                modifier = modifier.width(70.dp),
                contentScale = ContentScale.Fit
            )

        }

    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DefaultErrorView(
    modifier : Modifier = Modifier,
    error : String?,
    backButtonAvailable : Boolean = true,
    onBackPressed : () -> Unit,
){
    Box(modifier = modifier.fillMaxSize().padding(top = TOP_APP_PADDING), contentAlignment = Alignment.Center) {
        if (backButtonAvailable){
        BackButton(modifier.align(Alignment.TopStart), onBackPressedClick = onBackPressed)
    }
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.not_found), contentDescription = null,
                modifier = modifier.width(130.dp), contentScale = ContentScale.Fit
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(text = "An error occurred:", fontFamily = FontFamily.Monospace)
            if (error != null) {
                if (error != no_connection_error) {
                    Spacer(modifier = modifier.height(3.dp))
                    Text(
                        text = error,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        color = Color.Red
                    )
                } else {
                    Spacer(modifier = modifier.height(3.dp))
                    Text(
                        text = "No Internet connection.",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        color = Color.Red
                    )
                }
            }
        }
    }
}





@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DefaultBottomPageViewPreview(){
    IMdbAppTheme(darkTheme = true) {
        Scaffold {
            DefaultErrorView(
                error = "Unknown error",
            ){}
        }
    }
}