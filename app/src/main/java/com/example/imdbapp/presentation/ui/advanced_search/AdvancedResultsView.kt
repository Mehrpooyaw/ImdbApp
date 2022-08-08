package com.example.imdbapp.presentation.ui.advanced_search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.advanced_search.AdvancedSearchResults
import com.example.imdbapp.network.model.advanced_search.Result
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.TitaniumGradient
import com.example.imdbapp.presentation.ui.components.BackButton
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedResultsView(
    modifier : Modifier = Modifier,
    onNavigateToMovieScreen : (String) -> Unit,
    onBackPressed : () -> Unit,
    results : AdvancedSearchResults?,
){
    var searchResults by remember { mutableStateOf(results)}

    Scaffold() {

        LazyColumn(modifier = modifier
            .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 20.dp)
            ) {
            results?.let {
                if (it.results != null) {
                    items(it.results!!) { searchResult ->
                        AdvancedSearchCard(
                            searchResult = searchResult,
                            onClick = onNavigateToMovieScreen
                        )
                    }
                }



            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchCard(
    modifier: Modifier = Modifier,
    searchResult : Result,
    onClick :(String) -> Unit
){
        val imagePainter = rememberImagePainter(data = searchResult.image)

        Button(
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(18 / 9f),
            onClick = {
                searchResult.id?.let { onClick(it) }
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 16.dp,
                pressedElevation = 20.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background
            ),
            shape = RoundedCornerShape(20),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(modifier.fillMaxSize()) {

                Row(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(
                                TitaniumGradient.reversed(),

                                tileMode = TileMode.Clamp
                            )
                        )
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = modifier
                            .padding(end = 10.dp)
                            .fillMaxHeight()
                            .aspectRatio(getRatioFromImageLink(searchResult.image) ?: 0.7f),
                        shape = RoundedCornerShape(25),
                        elevation = 10.dp,
                    ) {
                        Image(
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                            painter = imagePainter,
                            contentDescription = "advanced_search_image"
                        )

                    }
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(horizontal = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            searchResult.title?.let {
                                if (it != "") {
                                    Text(
                                        text = it,
                                        modifier =modifier.fillMaxWidth(0.8f),
                                        fontFamily = FontFamily.SansSerif,
                                        color = Color.Cyan
                                    )
                                }
                            }
                            searchResult.imDbRating?.let {
                                if (it != "") {
                                    Text(it, color = AlmostYellow)
                                }
                            }
                        }

                        searchResult.genres?.let {
                            if (it != "") {
                                Text(
                                    text = it,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start),
                                    textAlign = TextAlign.Start,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.White
                                )
                            }
                        }
                        searchResult.plot?.let {
                            if (it != "") {
                                Text(it, maxLines = 3, overflow = TextOverflow.Ellipsis, color = Color.Yellow)
                            }
                        }





                    }

                }

            }

    }
}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedResultsViewPreview(){
    IMdbAppTheme() {
        AdvancedResultsView(onNavigateToMovieScreen = {}, onBackPressed = { /*TODO*/ },
            results = FakeData.sampleAdvancedSearch
        )

    }
}