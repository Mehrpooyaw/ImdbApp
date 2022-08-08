package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.imdbapp.network.model.movie.Actor
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink
import com.example.imdbapp.util.FakeData
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MiniActorsRow(modifier : Modifier = Modifier
                  , actorsList: List<Actor>,
                  onPersonClicked : (String?) -> Unit,
                  onNavigateToActorsScreen : (List<Actor>) -> Unit,
                  scrollState: ScrollState
                  ){
    val lazyState = rememberLazyListState()
    Column(modifier = modifier
        .fillMaxWidth()
        .height(200.dp)){

        LazyRow(
            state = lazyState,
            contentPadding = PaddingValues(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp)
        ){
            items(if (actorsList.size >4) actorsList.subList(0,4)else actorsList){
                MiniActorView(
                    actor = it,
                    cardModifier =
                    modifier
                        .fillMaxHeight()
                        .aspectRatio(1f),
                    cardElevation =12.dp,
                    onPersonClicked = onPersonClicked,
                    state = scrollState
                    )
            }
            if (actorsList.size >4) {
                item {
                    val context = LocalContext.current
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        modifier = modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .padding(5.dp),
                        onClick = {
                                  Toast.makeText(context,"Not implemented yet.",Toast.LENGTH_SHORT).show()
                        }
                        ,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.DarkGray
                        ),
                        contentPadding = PaddingValues(0.dp)

                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                                .background(
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF7b4397).copy(alpha = 0.8f),
                                            Color(0xFFdc2430).copy(alpha = 0.8f)
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "More ${actorsList.size - 5} Actors",
                                color = Color.Yellow,
                                fontWeight = FontWeight.SemiBold
                            )
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
fun MiniActorsRowPreview() {
    val sample = FakeData.SampleMovie
    Scaffold() {

        sample.actorList?.let { MiniActorsRow(actorsList = it, onPersonClicked = {}, onNavigateToActorsScreen = {}, scrollState = rememberScrollState()) }
    }
}

@ExperimentalFoundationApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActorsListView(movieName : String,
                   actorsList : List<Actor>,
                   modifier : Modifier = Modifier,
                   onPersonClicked: (String?) -> Unit){
    Scaffold() {


        Column(
            modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                movieName.uppercase(),
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
            Text(
                "ACTORS",
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Spacer(modifier.height(5.dp))
            Divider(
                thickness = 1.dp,
                color = Color.LightGray,
                modifier = modifier.padding(bottom = 5.dp)
            )
            Box(
                modifier
                    .fillMaxWidth(), contentAlignment = Alignment.CenterEnd
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    items(actorsList) { actor ->
                        MiniActorView(
                            actor = actor,
                            onPersonClicked = onPersonClicked
                        )
                    }
                }
            }
        }
    }
}








@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MiniActorView(actor: Actor,
                  modifier : Modifier = Modifier,
                  cardElevation : Dp = 50.dp,
                  onPersonClicked: (String?) -> Unit,
                  state : ScrollState? = null,
                  cardModifier: Modifier =
                      Modifier
                          .fillMaxHeight()
                          .aspectRatio(1f)
){
    val isImageLoaded = remember { mutableStateOf(false) }
    val image = rememberImagePainter(data = actor.image,)
    isImageLoaded.value = image.state is ImagePainter.State.Success

    var imageRatio = getRatioFromImageLink(actor.image)
    if (imageRatio == null) {
        imageRatio = 0.72f
    }
    val isRatioOneOrHigher= imageRatio >= 1

    Button(
        onClick = {
                  onPersonClicked(actor.id)
        },
        shape = RoundedCornerShape(10.dp),
        modifier = cardModifier

            .graphicsLayer {
                if (state != null) {
                    lerp(
                        start = 0.9f,
                        stop = 1f,
                        fraction = (state.value
                            .coerceIn(0, 1000)
                            .toFloat() ) / 1000
                    ).also {
                        scaleX = it
                        scaleY = it
                    }
                    lerp(
                        start = 200f,
                        stop = 1f,
                        fraction = (state.value
                            .coerceIn(0, 1000)
                            .toFloat() ) / 1000
                    ).also {
                        translationX = it
                    }
                }
            }
            .padding(5.dp),
        contentPadding = PaddingValues(0.dp)
    ){
        Box(modifier = modifier
            .fillMaxSize()) {
            Box(
                modifier = modifier.fillMaxSize()
            ){
                Image(painter = image, contentDescription = "actor_image", modifier = modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Color.Black.copy(alpha = 0.85f)
                        )
                )
            }
            Card(
                modifier = if (isRatioOneOrHigher)
                    modifier
                        .fillMaxWidth()
                        .aspectRatio(imageRatio)
                        .placeholder(
                            visible = !isImageLoaded.value,
                            shape = RoundedCornerShape(0.dp),
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.fade()
                        )

                    else
                    modifier
                        .fillMaxHeight()
                        .aspectRatio(imageRatio)
                        .placeholder(
                            visible = !isImageLoaded.value,
                            shape = RoundedCornerShape(0.dp),
                            color = Color.LightGray,
                            highlight = PlaceholderHighlight.fade()
                        ),
                elevation = 10.dp,
                shape = RectangleShape
            ) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = if (isRatioOneOrHigher)
                        Alignment.TopCenter
                    else
                        Alignment.CenterStart
                ) {
                    Image(
                        painter = image,
                        contentDescription = "actor_image",
                        contentScale = ContentScale.FillBounds,
                        modifier = modifier.fillMaxSize()
                    )



                }
            }
            Box(modifier = if (isRatioOneOrHigher) modifier.fillMaxSize() else modifier
                .rotate(90f)
                .fillMaxSize(),
                contentAlignment = if (isRatioOneOrHigher) Alignment.BottomCenter else Alignment.TopCenter
            ) {


                Column(
                    horizontalAlignment = Alignment.Start, modifier = modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Top
                ) {

                    Spacer(modifier.height(2.dp))
                    Text(
                        actor.name ?: "",
                        color = Color.Yellow,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier.height(2.dp))
                    Text(
                        "as ${actor.asCharacter}",
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier.height(7.dp))
                }

            }
        }
    }




}

@ExperimentalFoundationApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ActorsListViewPreview(){
    val sampleMovie = FakeData.SampleMovie
    IMdbAppTheme() {

        sampleMovie.actorList?.let {
            ActorsListView(
                movieName = sampleMovie.title ?: "",
                actorsList = it
            ){}
        }
    }
}