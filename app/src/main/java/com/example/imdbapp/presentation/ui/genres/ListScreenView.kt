package com.example.imdbapp.presentation.ui.genres

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.presentation.theme.AlmostSilver
import com.example.imdbapp.presentation.theme.GlassyGradient
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.TitaniumGradient
import com.example.imdbapp.presentation.ui.components.BackButton
import com.example.imdbapp.presentation.ui.top_movies.TopMovieCard
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListScreenView(
    modifier : Modifier = Modifier,
    title : String?,
    imageId : Int?,
    gradient : List<Color> = TitaniumGradient,
    movies : List<ResultDomain>?,
    onClick : (String) -> Unit,
    onBackPressed : () -> Unit,

) {
    val scrollState = rememberScrollState()
    if (movies != null) {
        LazyColumn(
            modifier = modifier
                .padding(top = TOP_APP_PADDING)
                .fillMaxSize(),
        ) {
            item {
                Card(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10),
                    backgroundColor = MaterialTheme.colors.background,
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .aspectRatio(21 / 9f)

                ) {


                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    listOf(
                                        gradient[0].copy(alpha = 0.5f),
                                        gradient[0].copy(alpha = 0.3f),
                                        gradient[0].copy(alpha = 0.1f),
                                        gradient[1].copy(alpha = 0.1f),
                                        gradient[1].copy(alpha = 0.3f),
                                        gradient[1].copy(alpha = 0.5f),
                                    )
                                )
                            )
                            .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Row(
                            modifier = modifier
                                .padding(vertical = 5.dp)
                                .padding(bottom = 3.dp)
                                ,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BackButton(onBackPressedClick = onBackPressed)
                            if (title != null) {
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.h6,
                                    fontFamily = FontFamily.Monospace,
                                    color = AlmostSilver
                                )
                            }
                            Spacer(modifier = modifier.width(1.dp))
                        }
                        Box(
                            modifier = modifier
                                .padding(5.dp)
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            imageId?.let {
                                Image(
                                    painter = painterResource(id = imageId),
                                    contentDescription = "",
                                    modifier = modifier.fillMaxSize(0.6f)
                                )
                            }

                        }
                    }

                }
            }
            items(movies) { m ->
                TopMovieCard(searchResult = m, onClick = onClick)
            }
        }


    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun ListScreenViewPreviewDark(){
    IMdbAppTheme(darkTheme = true) {
        Scaffold() {
            ListScreenView(
                title = "war",imageId = R.drawable.icon_category_war, movies = emptyList(), gradient = GlassyGradient, onBackPressed = {}, onClick = {

                }, )
        }
    }
}