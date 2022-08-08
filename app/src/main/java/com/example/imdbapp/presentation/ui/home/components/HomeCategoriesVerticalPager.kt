package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.domain.model.advanced_search.AdvancedSearchResults
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.BlurTransformation
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.network.model.advanced_search.Result
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.util.ImageQuality
import com.example.imdbapp.presentation.ui.util.changeImageQuality
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.*


@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoriesVerticalPager(
    modifier : Modifier = Modifier,
    results : List<ResultDomain>,
    loading : Boolean,
    onClick :(String) -> Unit
) {
    val pagerState = rememberPagerState(results.size/2)
    Box(modifier.fillMaxSize()){
        VerticalPager(
            count = results.size,
            modifier= modifier.padding(horizontal = 50.dp).fillMaxWidth()
        , contentPadding = PaddingValues(vertical = 160.dp),
            itemSpacing = 0.dp,
            horizontalAlignment = Alignment.CenterHorizontally,
            state =pagerState

            ) { page ->
            val item = results[page]
            val painter = rememberImagePainter(data = changeImageQuality(url = item.image, quality = ImageQuality.Medium.str))
            Button(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 20.dp)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .graphicsLayer {
                        val pagerOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                        ).also {
                            scaleY = it
                        }
                        lerp(
                            start = 0.7f,
                            stop = 1f,
                            fraction = 1f - pagerOffset.coerceIn(0f, 1f)
                        ).also {
                            scaleX = it
                        }
                        shape = RoundedCornerShape(10)
                        clip = true

                    },
                contentPadding = PaddingValues(0.dp)

                    ,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.background
                ), onClick = {
                    item.id?.let { onClick(it) }
                }
            ) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                alpha = lerp(
                                    start = 1f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )

                            },
                        painter = painter,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "home_category_vertical_pager_card_background"
                    )
                    Spacer(
                        modifier =
                        modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                alpha = lerp(
                                    start = 0.0f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )

                            }
                            .fillMaxSize().background(Brush.linearGradient(listOf(
                            Color.Gray.copy(alpha = 0.7f),
                            Color.Gray.copy(alpha = 0.5f),
                            Color.Gray.copy(alpha = 0.9f),
                            Color.Gray.copy(alpha = 0.5f),
                            Color.Gray.copy(alpha = 0.7f),
                        )))

                        ,)


                    Card(
                        modifier = modifier
                            .padding(vertical = 5.dp)
                            .fillMaxHeight()
                            .aspectRatio(0.77f)
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                lerp(
                                    start = 0.0f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f,1f)
                                ).also {
                                    scaleX = it
                                    scaleY = it
                                }
                            },
                        elevation = 5.dp,
                        shape = RoundedCornerShape(17)
                    ) {


                    Image(painter = painter,
                        contentDescription = "home_category_vertical_pager_card_image",
                        modifier =
                        modifier
                            .fillMaxSize()
                            ,
                        contentScale = ContentScale.FillBounds
                    )
                    }

                }

            }
        }
        VerticalPagerIndicator(
            pagerState =pagerState,
            modifier = modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterStart),
            indicatorShape = RoundedCornerShape(30),
            inactiveColor = Color.LightGray,
            activeColor = MaterialTheme.colors.primary
            )
        AnimatedVisibility(
            visible = loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()

                    .clickable { },
                contentAlignment = Alignment.Center
            ) {
                Spacer(
                    modifier = modifier
                        .blur(70.dp)
                        .fillMaxSize()

                )
                Card(
                    modifier = modifier
                        .fillMaxWidth(0.3f)
                        .aspectRatio(1f),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20)
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    RedSunsetGradient

                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {

                        CircularProgressIndicator(
                            modifier = modifier.size(70.dp),
                            strokeWidth = 5.dp,
                            color = AlmostSilver
                        )
                    }
                }
            }
        }
    }

}



