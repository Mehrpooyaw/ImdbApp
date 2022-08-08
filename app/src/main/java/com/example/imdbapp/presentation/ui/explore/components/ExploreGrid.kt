package com.example.imdbapp.presentation.ui.explore.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.imdbapp.domain.model.TopMovie
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.presentation.theme.AlmostDarkGray
import com.example.imdbapp.presentation.theme.AlmostYellow


@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ExploreGrid(
    modifier : Modifier = Modifier,
    list : List<ResultDomain>,
    onNavigateToMovieScreen: (String) -> Unit
    ) {
    Column() {
        if (!list.isNullOrEmpty()) {
            list.let { theList ->
                //TODO : shuffled() should be outside of composable

                Text(
                    text = "Explore shuffled movies ...",
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Thin,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.padding(start = 15.dp, top = 20.dp, bottom = 15.dp)
                )

                LazyVerticalGrid3x3(
                    theList.subList(0, 9),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )

                LazyVerticalGrid1x2(
                    list = theList.subList(9, 12),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )

                LazyVerticalGrid1x3(
                    list = theList.subList(12, 15),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )

                theList[15]?.let {
                    ExploreCellGridItem(
                        item = it,
                        onNavigateToMovieScreen = onNavigateToMovieScreen
                    )
                }

                LazyVerticalGrid2x2(
                    list = theList.subList(16, 20),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )

                theList[20]?.let {
                    ExploreCellGridItem(
                        item = it,
                        onNavigateToMovieScreen = onNavigateToMovieScreen
                    )
                }

                LazyVerticalGrid3x3(
                    list = theList.subList(21, 30),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )

                LazyVerticalGrid2x1(
                    list = theList.subList(30, 33),
                    onNavigateToMovieScreen = onNavigateToMovieScreen
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LazyVerticalGrid1x2(list : List<ResultDomain>, modifier: Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Box(modifier.fillMaxWidth(2/3f)) {
            list[0]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
        }
        Column(modifier.fillMaxWidth()) {
            list[1]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
            list[2]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
        }
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LazyVerticalGrid2x2(list : List<ResultDomain>, modifier: Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit){
    Column() {
        Row() {
            list.slice(0..1).forEach {
                Box(
                    modifier = modifier.weight(1f)
                ){
                    if (it != null) {
                        ExploreCellGridItem(item = it, onNavigateToMovieScreen = onNavigateToMovieScreen )
                    }
                }
            }
        }
        Row() {
            list.slice(2..3).forEach {
                Box(
                    modifier = modifier.weight(1f)
                ){
                    if (it != null) {
                        ExploreCellGridItem(item = it, onNavigateToMovieScreen = onNavigateToMovieScreen )
                    }
                }
            }
        }
    }
}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LazyVerticalGrid2x1(list : List<ResultDomain>, modifier: Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Column(modifier.fillMaxWidth(1/3f)) {
            list[0]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
            list[1]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
        }
        Box(modifier.fillMaxWidth()) {
            list[2]?.let { ExploreCellGridItem(item = it,onNavigateToMovieScreen = onNavigateToMovieScreen) }
        }

    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LazyVerticalGrid1x3(list: List<ResultDomain>, modifier: Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit){
    Row() {
        list.subList(0,3).forEach {
            Box(
                modifier = modifier.weight(1f)
            ){
                if (it != null) {
                    ExploreCellGridItem(item = it, onNavigateToMovieScreen = onNavigateToMovieScreen )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun LazyVerticalGrid3x3(list: List<ResultDomain>, modifier: Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit){
    if (!list.isNullOrEmpty()) {
        Column() {
            Row() {
                list.subList(0, 3).forEach {
                    Box(
                        modifier = modifier.weight(1f)
                    ) {
                        if (it != null) {
                            ExploreCellGridItem(
                                item = it,
                                onNavigateToMovieScreen = onNavigateToMovieScreen
                            )
                        }
                    }
                }
            }
            Row() {
                list.subList(3, 6).forEach {
                    Box(
                        modifier = modifier.weight(1f)
                    ) {
                        if (it != null) {
                            ExploreCellGridItem(
                                item = it,
                                onNavigateToMovieScreen = onNavigateToMovieScreen
                            )
                        }
                    }
                }
            }
            Row() {
                list.subList(6, 9).forEach {
                    Box(
                        modifier = modifier.weight(1f)
                    ) {
                        if (it != null) {
                            ExploreCellGridItem(
                                item = it,
                                onNavigateToMovieScreen = onNavigateToMovieScreen
                            )
                        }
                    }
                }
            }
        }
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ExploreCellGridItem(item : ResultDomain, modifier : Modifier = Modifier,onNavigateToMovieScreen: (String) -> Unit,){
    val imageContentDescription = "explore_item_image"
    val painter = rememberImagePainter(data = item.image)
    Button(
        modifier = modifier
            .padding(3.dp)
            .fillMaxWidth()
            .aspectRatio(0.7f),
        shape = RoundedCornerShape(7),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 12.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AlmostDarkGray
        ),
        onClick = {
            item.id?.let { onNavigateToMovieScreen(it) }
        },
        enabled = item.id != null,
    ) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painter,
                contentDescription = imageContentDescription,
                contentScale = ContentScale.FillBounds,
                modifier = modifier.fillMaxSize()
            )

            item.imDbRating?.let {
                Row(
                    modifier
                        .clip(RoundedCornerShape(topStartPercent = 50, topEndPercent = 50))
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(horizontal = 10.dp)
                        .padding(top = 7.dp, bottom = 3.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = "",
                        modifier = modifier
                            .size(18.dp)
                            .padding(end = 3.dp),
                        tint = AlmostYellow
                    )
                    Text(it, color = AlmostYellow, fontSize = 13.sp)

                }
            }
        }
    }
}







