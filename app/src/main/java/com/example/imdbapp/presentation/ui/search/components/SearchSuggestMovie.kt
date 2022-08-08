package com.example.imdbapp.presentation.ui.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.presentation.theme.AlmostDarkGray
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.theme.NeonPink
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.*
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import kotlin.math.absoluteValue

@ExperimentalCoilApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchSuggestMovie(
    modifier : Modifier = Modifier,
    items : List<Item>,
    onClick : (String) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = items.size / 2)

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 70.dp),
        itemSpacing = 30.dp,
        count = items.size,
        state = pagerState,
        verticalAlignment = Alignment.Top
    ) { page ->

        val item = items[page]

        val isImageLoaded = remember { mutableStateOf(false)}
        val painter = rememberImagePainter(data = item.image,)
        isImageLoaded.value = painter.state is ImagePainter.State.Success

        Column() {
            Button(
                modifier = modifier

                    .graphicsLayer {
                        val offset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.9f,
                            stop = 1f,
                            fraction = 1f - offset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                        }
                        lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - offset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleY = scale
                        }
                        lerp(
                            start = 120f,
                            stop = 0f,
                            fraction = 1f - offset.coerceIn(0f, 1f)
                        ).also { transY ->
                            translationY = transY
                        }
                        lerp(
                            start = 5f,
                            stop = 0f,
                            fraction = 1 - offset.coerceIn(0f, 360f)
                        ).also { rotation ->
                            rotationX = rotation


                        }
                    }
                    .fillMaxWidth(1f)
                    .aspectRatio(0.73f)
                    .placeholder(
                        visible = !isImageLoaded.value, color = Color.LightGray,
                        shape = RoundedCornerShape(5),
                        highlight = PlaceholderHighlight.fade()
                    ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = AlmostDarkGray
                ),
                shape = RoundedCornerShape(5),
                contentPadding = PaddingValues(0.dp),
                onClick = {
                    item.id?.let { onClick(it) }
                },
                enabled = item.id != null,
            ) {
                Image(
                    painter = painter,
                    contentDescription = "search_feature_movies_item",
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Spacer(modifier = modifier.height(40.dp))

        }


    }
}





@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchFeatureMoviesPreview(){
    val movies = FakeData.SampleTop250.items
    IMdbAppTheme() {
        Scaffold {
            if (movies != null) {
                SearchSuggestMovie(
                    items = movies

                ){}
            }
        }
    }
}