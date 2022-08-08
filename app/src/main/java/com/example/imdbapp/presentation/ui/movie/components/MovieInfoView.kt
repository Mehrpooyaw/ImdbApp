package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.Movie
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.components.VerticalKeyValueCard
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieInfoView(modifier : Modifier = Modifier,movie : Movie) {
    val isExpanded = remember { mutableStateOf(false)}
    Column(modifier = modifier
        .fillMaxWidth()
        .animateContentSize()
        .padding(horizontal = 5.dp, vertical = 5.dp)){
        CustomTitleText(text = "FULLCAST",modifier.padding(bottom = 5.dp))
        if (!isExpanded.value){
            movie.fullTitle?.let {
                if (it != ""){

                    VerticalKeyValueCard(key = "Full title", value = it) }}
            
            movie.genres?.let {
                if (it != ""){VerticalKeyValueCard(key = "Genres", value = it) }}
            
            movie.directors?.let {
                if (it != ""){VerticalKeyValueCard(key = "Directors", value = it) }}
        }
        else {
            movie.fullTitle?.let {
                if (it != ""){VerticalKeyValueCard(key = "Full title", value = it) }}
            
            movie.genres?.let {
                if (it != ""){VerticalKeyValueCard(key = "Genres", value = it) }}
            
            movie.directors?.let {
                if (it != ""){VerticalKeyValueCard(key = "Directors", value = it) }}
            
            movie.writers?.let {
                if (it != ""){VerticalKeyValueCard(key = "Writers", value = it) }}
            
            movie.stars?.let {
                if (it != ""){VerticalKeyValueCard(key = "Stars", value = it) }}
            
            movie.tagline?.let {
                if (it != ""){VerticalKeyValueCard(key = "Tagline", value = it) }}
            
            movie.countries?.let {
                if (it != ""){VerticalKeyValueCard(key = "Countries", value = it) }}
            
            movie.languages?.let {
                if (it != ""){VerticalKeyValueCard(key = "Languages", value = it) }}
            
            movie.companies?.let {
                if (it != ""){VerticalKeyValueCard(key = "Companies", value = it) }}
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(
                    modifier = modifier.padding(vertical = 10.dp, horizontal = 5.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(25),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = AlmostYellow,
                        contentColor = Color.White
                    ),
                    onClick = {
                        //TODO : OnClick
                    }) {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Text("Other FULLCASTS",color = Color.DarkGray)
                        Spacer(modifier.width(3.dp))
                        Icon(painter = painterResource(id = R.drawable.ic_baseline_read_more_24), contentDescription = "",tint = Color.DarkGray)
                    }
                }
            }
        }
        Box(
            modifier
                .fillMaxWidth()
                .padding(top = 10.dp), Alignment.Center) {
            Button(
                onClick = { isExpanded.value = !isExpanded.value },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White
                ),
                modifier = modifier.height(26.dp),
                contentPadding = PaddingValues(0.dp)

            ) {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = modifier
                    .fillMaxHeight()
                    .background(
                        Brush.linearGradient(listOf(NeonBlue, NeonGreen))
                    )
                    .padding(horizontal = 8.dp)) {
                    Text(if(isExpanded.value)"Less" else "More",)

                    Icon(
                        if (isExpanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                        contentDescription = "",
                        modifier = modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@Preview()
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieInfoViewPreview(){
    val sample = FakeData.SampleMovie
    Scaffold() {


        MovieInfoView(
            movie = sample
        )
    }
}


