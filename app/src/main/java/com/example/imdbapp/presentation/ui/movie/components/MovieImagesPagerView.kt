package com.example.imdbapp.presentation.ui.movie.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.movie.Images
import com.example.imdbapp.network.model.movie.Poster

import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlinx.coroutines.launch

@ExperimentalSnapperApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieImagesPagerView(
    modifier : Modifier = Modifier,
    movieName : String,
    posters : List<Poster>,
    images : Images
){

    var tabState by remember { mutableStateOf(0)}
    val titles = listOf("POSTERS", "IMAGES")
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = Color.White
            ) {
                IconButton(onClick = { /*TODO*/ },modifier) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "", tint = Color.White,modifier = modifier
                        .size(32.dp)
                        .padding(5.dp))
                }
                Text("$movieName posters and images")
            }
        }
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            TabRow(
                selectedTabIndex = tabState,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = NeonGreen
            ) {
                titles.forEachIndexed { i, title ->
                    Tab(selected = tabState == i, onClick = { tabState = i }, text = {
                        Text(title)
                    })
                }
            }
            when (tabState) {
                0 -> {
                    PostersTabPageView(posters = posters)
                }
                1 -> {
                    ImagesTabPageView(images = images)
                }
            }
        }
    }


}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ImagesTabPageView(modifier : Modifier = Modifier,images: Images){
    var scale by remember {mutableStateOf(1f)}
    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        offset += offset
    }
    val pagerState = rememberPagerState()
    if (images.items != null){
        val imagesList = if (images.items.size > 6)  images.items.subList(0,6) else images.items
        Box(modifier = modifier
            .fillMaxSize()
            .background(Color.Black), contentAlignment = Alignment.Center){

            Box(modifier = modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                HorizontalPager(
                    count = imagesList.size,
                    state = pagerState,
                    reverseLayout = false,

                    ) { page ->
                    Column(modifier = modifier.fillMaxWidth()) {
                        Box(
                            modifier = modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Image(
                                painter = rememberImagePainter(
                                    data =
                                    imagesList.elementAt(page).image
                                ), contentDescription = "movie_image_pager",
                                modifier = modifier.fillMaxWidth().graphicsLayer {
                                    scaleX = scale
                                    scaleY = scale
                                }
                                    .transformable(state = state),
                                contentScale = ContentScale.Fit
                            )


                        }
                        Box(){
                            images.items.elementAt(page).title?.let {
                                Text(
                                    text =it,modifier = modifier.padding(5.dp), color = AlmostYellow, fontFamily = FontFamily.Serif) }
                        }
                    }
                }



            }
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = modifier.padding(horizontal = 20.dp, vertical = 40.dp),
                    activeColor = Color.Blue,
                    inactiveColor = Color.White)
            }
            Box() {
                ActionsRow(pagerState = pagerState)
            }
        }
    }


}




@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostersTabPageView(modifier : Modifier = Modifier,posters: List<Poster>){
    var scale by remember {mutableStateOf(1f)}
    var offset by remember {mutableStateOf(Offset.Zero)}
    var state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale *= zoomChange
        offset += offset
    }
    val pagerState = rememberPagerState()
    val postersList = if (posters.size > 6) posters.subList(0,6) else posters
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Black), contentAlignment = Alignment.Center){
        Box(modifier = modifier
            .fillMaxWidth(),
            contentAlignment = Alignment.BottomEnd
        ) {
            HorizontalPager(
                count = postersList.size,
                state = pagerState,
                reverseLayout = false,

                ) { page ->
                Box(modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(
                        if (postersList.elementAt(page).aspectRatio != null) (postersList.elementAt(
                            page
                        ).aspectRatio)!!.toFloat() else 7 / 9f
                    )
                ){
                    Image(painter = rememberImagePainter(data =
                    postersList.elementAt(page).link
                    ), contentDescription = "movie_image_poster_pager",
                        modifier = modifier.fillMaxSize().graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                            .transformable(state = state),
                        contentScale = ContentScale.FillBounds
                    )


                }
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = modifier.padding(20.dp),
                activeColor = Color.Blue,
                inactiveColor = Color.White)


        }
        Box() {
            ActionsRow(pagerState = pagerState)
        }
    }

}




@ExperimentalSnapperApi
@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieImagesPagerViewPreview(){
    val postersList = FakeData.SampleMovie.posters?.posters
    val imagesList = FakeData.SampleMovie.images
    val movieName = FakeData.SampleMovie.title

        IMdbAppTheme() {
            if (postersList != null && imagesList != null && movieName != null) {
                MovieImagesPagerView(posters = postersList, images = imagesList, movieName = movieName)
            }
        }

}


@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
internal fun ActionsRow(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    infiniteLoop: Boolean = false
) {
    Row(
        modifier
            .fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween) {
        val scope = rememberCoroutineScope()

        IconButton(
            enabled = infiniteLoop || pagerState.currentPage > 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }
        ) {
            Box(modifier = modifier
                .padding(vertical = 100.dp)
                .padding(end = 10.dp)
                .clip(CircleShape)
                .background(Color.DarkGray.copy(alpha = 0.4f))){
                Icon(Icons.Rounded.KeyboardArrowLeft, null,
                    modifier
                        .size(28.dp)
                        .padding(3.dp), tint = Color.White)
            }
        }

        IconButton(

            enabled = infiniteLoop || pagerState.currentPage < pagerState.pageCount - 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        ) {
            Box(modifier = modifier
                .padding(vertical = 100.dp)
                .padding(end = 10.dp)
                .clip(CircleShape)
                .background(Color.DarkGray.copy(alpha = 0.4f))){
                Icon(Icons.Rounded.KeyboardArrowRight, null,
                    modifier
                        .size(28.dp)
                        .padding(3.dp), tint = Color.White)

            }

        }


    }
}
