package com.example.imdbapp.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun IconAndVerticalKeyValueWithRoundedShape(
    modifier : Modifier = Modifier, key : String,
    value : String, iconId : Int, tintGradient : List<Color>){
    Card(
        elevation = 10.dp,
        border = BorderStroke(0.5.dp, brush = Brush.linearGradient(
            if (MaterialTheme.colors.isLight)
            listOf(
               Color.Transparent,
               Color.Transparent,
            )
        else
            listOf(
                MaterialTheme.colors.background, MaterialTheme.colors.surface, Color.DarkGray

            )
        )),
        shape = RoundedCornerShape(50),
        backgroundColor = MaterialTheme.colors.background,
    ) {
        Row(
            modifier = modifier
                .width(150.dp)
                .height(40.dp)
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colors.surface, MaterialTheme.colors.background
                        )
                    )
                )
                .padding(end = 5.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                elevation = 1.dp,
                modifier = modifier.size(36.dp),
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(tintGradient)
                        )
                ) {
                    Icon(
                        painter = painterResource(id = iconId),
                        modifier = modifier.size(22.dp),
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
            Column(modifier = modifier.padding(horizontal = 6.dp)) {
                Text(text = key, fontSize = 12.sp, color = if (MaterialTheme.colors.isLight) Color.DarkGray else Color.LightGray)
                Text(
                    text = value,
                    fontSize = 14.sp,
                    color = tintGradient[1],
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }


        }
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerIconKeyValPreview(){
    IconAndVerticalKeyValueWithRoundedShape(key = "runtime", value = "1h 30min", iconId = R.drawable.icon_timer,tintGradient = TGreenGradient)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HorizontalKeyValue(modifier : Modifier = Modifier, key : String, value : String,tint : Color = Color.Yellow){
    Row(modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.CenterVertically){
        Text(text = "$key : ", fontFamily = FontFamily.SansSerif, color = Color.Gray)
        Text(value, fontFamily = FontFamily.Monospace,color = tint, fontWeight = FontWeight.SemiBold)
    }



}
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HorKeyValPreview(){
    val movie = FakeData.SampleMovie
    HorizontalKeyValue(key = "Runtime", value = movie.runtimeStr?:"not found")
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerticalKeyValue(modifier : Modifier = Modifier, key : String, value : String){
    Row(modifier.padding(vertical = 5.dp, horizontal = 5.dp)
        ,verticalAlignment = Alignment.CenterVertically) {
        Spacer(
            modifier
                .background(NeonGreen)
                .size(width = 1.dp, height = 45.dp))
        Spacer(modifier.width(5.dp))
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = key.uppercase(),
                fontSize = 13.sp,
                fontFamily = FontFamily.Monospace,
                color = Color.Gray
            )
            Text(
                value,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace,
            )
        }
    }


}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerticalKeyValueWithDivider(modifier : Modifier = Modifier, key : String, value : String){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = key,
            fontSize = 13.sp,
            fontFamily = FontFamily.Monospace,
            color = Color(0xFF8CCE87)
        )
        Text(
            text = value,
            modifier = modifier.padding(top = 4.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
        )

        Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Divider(
                modifier
                    .padding(vertical = 7.dp)
                    .fillMaxWidth(0.9f), color = Color(0xFFBAFAB5))
        }
    }



}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerticalKeyValueCard(modifier : Modifier = Modifier, key : String, value : String){
    Card(
        modifier = modifier
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(1.dp,Brush.linearGradient(
            listOf(Color.Gray.copy(alpha = 0.4f), Color.White.copy(alpha = 0.5f, blue = 0.7f))
        )),
        backgroundColor = MaterialTheme.colors.surface

    ) {
        Box(modifier.fillMaxWidth().background(Brush.horizontalGradient(
            listOf(Color.Gray.copy(alpha = 0.02f),
            NeonBlue.copy(alpha = 0.2f)
                )
        ))){
            Column(
                modifier = modifier
                    .padding(9.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = key,
                    fontSize = 13.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White.copy(blue = 0.7f)
                )
                Text(
                    text = value,
                    modifier = modifier.padding(top = 4.dp),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                )

            }
        }


    }

}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerKeyValPreview(){
    val movie = FakeData.SampleMovie
    IMdbAppTheme() {
    Scaffold() {
        Column() {
            repeat(5){
                VerticalKeyValueCard(key = "TagLine", value = movie.tagline?:"not found")

            }
        }
    }

    }

}