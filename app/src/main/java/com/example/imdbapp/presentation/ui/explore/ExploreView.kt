package com.example.imdbapp.presentation.ui.explore

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.explore.components.ExploreGrid
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView
import com.example.imdbapp.presentation.ui.scaffold.CustomScaffold
import com.example.imdbapp.presentation.ui.util.ImageQuality
import com.example.imdbapp.presentation.ui.util.changeImageQuality
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.absoluteValue

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreView(
    modifier : Modifier = Modifier,
    exploreList : List<ResultDomain>?,
    topHorizontalRow : List<ResultDomain>?,
    onNavigateToMovieScreen : (String) -> Unit,
    isRefreshing : Boolean,
    refreshScreen : () -> Unit
) {
    if (!topHorizontalRow.isNullOrEmpty()) {
        topHorizontalRow.let {
            val scrollState = rememberScrollState()
            val pagerState: PagerState = rememberPagerState(topHorizontalRow.size / 2)

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh = refreshScreen
                , indicatorPadding = PaddingValues(top = 50.dp)
            ) {


                Box(
                    modifier = modifier.fillMaxSize()
                ) {

                    Box(
                        modifier = modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .aspectRatio(0.77f),
                    ) {

                        Image(
                            painter = rememberImagePainter(
                                data = topHorizontalRow[pagerState.currentPage].image
                            ),
                            contentDescription = "movie_horizontal_pager_list_background",
                            modifier = modifier
                                .fillMaxHeight()
                                .aspectRatio(0.77f)
                                .graphicsLayer
                                {
                                    lerp(
                                        start = 1f,
                                        stop = 0f,
                                        fraction = scrollState.value
                                            .coerceIn(0, 600)
                                            .toFloat() / 600
                                    ).also {
                                        alpha = it
                                    }


                                },
                            contentScale = ContentScale.FillBounds
                        )

                        Spacer(
                            modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.Transparent,

                                            MaterialTheme.colors.background,
                                        )
                                    )
                                )
                        )
                    }
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        Spacer(
                            modifier
                                .fillMaxWidth()
                                .aspectRatio(1.03f)
                        )
                        HorizontalPager(
                            count = topHorizontalRow.size,
                            contentPadding = PaddingValues(horizontal = 90.dp),
                            modifier = Modifier.fillMaxWidth(),
                            itemSpacing = 0.dp,
                            state = pagerState,
                            verticalAlignment = Alignment.Bottom
                        ) { page ->

                            val painter = rememberImagePainter(
                                data = changeImageQuality(
                                    topHorizontalRow[page]?.image,
                                    quality = ImageQuality.Medium.str
                                )

                            )

                            Box(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                            ) {
                                Card(
                                    modifier = modifier
                                        .align(Alignment.BottomCenter)
                                        .graphicsLayer {
                                            val pageOffset =
                                                calculateCurrentOffsetForPage(page).absoluteValue

                                            rotationX = lerp(
                                                start = 0f,
                                                stop = 60f,
                                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                            )
                                            lerp(
                                                start = 0.6f,
                                                stop = 1f,
                                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                            ).also { scale ->
                                                scaleX = scale
                                                scaleY = scale
                                            }
                                            clip = true
                                            lerp(
                                                start = 50,
                                                stop = 1,
                                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                            ).also { rounded ->
                                                shape = RoundedCornerShape(rounded)

                                            }

                                        }
                                        .fillMaxWidth(0.8f)
                                        .aspectRatio(1.1f),

                                    shape = RoundedCornerShape(25),
                                    backgroundColor = DarkNeonGreen.copy(alpha = 0.5f)
                                ) {
                                    Box(
                                        modifier = modifier
                                            .fillMaxSize()
                                            .background(
                                                Brush.verticalGradient(

                                                    GlassyGradient

                                                )
                                            )
                                    ) {
                                        Image(
                                            painter = painter,
                                            contentDescription = "movie_item_square_card",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = modifier
                                                .graphicsLayer {
                                                    val pageOffset =
                                                        calculateCurrentOffsetForPage(page).absoluteValue
                                                    lerp(
                                                        start = 0f,
                                                        stop = 1f,
                                                        fraction = 1f - pageOffset
                                                            .coerceIn(0f, 1f)
                                                            .also {
                                                                alpha = it
                                                            })
                                                }
                                                .fillMaxSize()
                                        )

                                    }
                                }
                                if (topHorizontalRow[page] != null) {
                                    Button(
                                        modifier = modifier
                                            .graphicsLayer {
                                                val pageOffset =
                                                    calculateCurrentOffsetForPage(page).absoluteValue
                                                lerp(
                                                    start = 0f,
                                                    stop = 1f,
                                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                                ).also {
                                                    scaleX = it
                                                    scaleY = it
                                                    alpha = it
                                                }
                                                lerp(
                                                    start = 200f,
                                                    stop = 0f,
                                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                                ).also {
                                                    translationY = it

                                                }
                                            }
                                            .padding(top = 30.dp)
                                            .align(Alignment.TopCenter)
                                            .fillMaxHeight(0.7f)
                                            .aspectRatio(0.77f),
                                        shape = RoundedCornerShape(7),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = AlmostDarkGray
                                        ),
                                        contentPadding = PaddingValues(0.dp),
                                        onClick = {
                                            topHorizontalRow[page]?.id?.let {
                                                onNavigateToMovieScreen(
                                                    it
                                                )
                                            }
                                        },
                                        enabled = topHorizontalRow[page]?.id != null,
                                    ) {
                                        Image(
                                            painter = painter,
                                            contentDescription = "movie_item_square_card",
                                            contentScale = ContentScale.FillBounds,
                                            modifier = modifier.fillMaxSize()
                                        )
                                    }
                                }

                            }

                        }
                        Box(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                activeColor = Color.Yellow,
                                inactiveColor = Color.LightGray,
                                indicatorWidth = 20.dp,
                                indicatorHeight = 5.dp,
                                spacing = 7.dp
                            )
                        }
                        CustomTitleText(
                            text = "Oscar winner movies",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 3.dp)
                                .padding(top = 15.dp)

                        )


                        exploreList.let {
                            Divider(
                                modifier = modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 25.dp), thickness = 1.dp, color = AlmostSilver
                            )

                            if (!it.isNullOrEmpty()) {
                                ExploreGrid(
                                    list = it,
                                    onNavigateToMovieScreen = onNavigateToMovieScreen
                                )
                            }
                            Divider(modifier = modifier.padding(vertical = 10.dp))
                            DefaultBottomPageView()

                            Spacer(modifier = modifier.height(74.dp))

                        }
                    }
                }
            }
        }

    }
}




@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExplorePreview(){
    val comingsoon = FakeData.SampleComingSoonData
    val top250 = FakeData.SampleTop250
    val tabs = remember {BaseSections.values()}
    val navController = rememberNavController()
    IMdbAppTheme() {
        CustomScaffold(tabs = tabs, navController = navController ) {
            ExploreView(topHorizontalRow = listOf(),exploreList = listOf(), isRefreshing = true, onNavigateToMovieScreen = {}){}
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExplorePreviewDark(){
    val comingsoon = FakeData.SampleComingSoonData
    val top250 = FakeData.SampleTop250
    IMdbAppTheme(darkTheme = true) {
        Scaffold() {
            ExploreView(topHorizontalRow = listOf(),exploreList = listOf(), isRefreshing = true, onNavigateToMovieScreen = {}){}
        }
    }
}