package com.example.imdbapp.presentation.ui.categories.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.ui.components.CustomTitleText


enum class Genres(val title : String,  val imageId : Int ) {
    Animation( title = "Animation", imageId = R.drawable.icon_category_animation),
    Horror(title = "Horror", imageId = R.drawable.icon_category_horror_colored),
    Comedy(title = "Comedy", imageId = R.drawable.icon_category_comedy_colored),
    Musical(title = "Musical", imageId = R.drawable.icon_category_musical),
    Scifi(title = "Sci-fi", imageId = R.drawable.icon_category_scifi_colored),
    Action(title = "Action", imageId = R.drawable.icon_category_action_colored),
    War(title = "War", imageId = R.drawable.icon_category_war),
    Adventure(title = "Adventure", imageId = R.drawable.icon_category_advanture),
    Romance(title = "Romance", imageId = R.drawable.icon_category_romance)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryGenresList(
    modifier : Modifier = Modifier,
    onGenreClicked : (Genres) -> Unit,
    onNavigateToAdvancedSearchScreen : () -> Unit

){
    Column(
        modifier
            .padding(horizontal = 3.dp)
            .fillMaxWidth()) {
        CustomTitleText(text = "Genres List",modifier = modifier.padding(horizontal = 10.dp, vertical = 15.dp))
        Row() {
            Genres.values().toList().subList(0,3).forEach {
                CategoryGenreListItem(
                    buttonModifier = modifier.weight(1f),
                    title = it.title,
                    imageId = it.imageId,
                    genre = it,
                    onGenreClicked = onGenreClicked
                )
            }
        }
        Row() {
            Genres.values().toList().subList(3,6).forEach {
                CategoryGenreListItem(
                    buttonModifier = modifier.weight(1f),
                    title = it.title,
                    imageId = it.imageId,
                    genre = it,
                    onGenreClicked = onGenreClicked
                )
            }
        }
        Row() {
            Genres.values().toList().subList(6,9).forEach {
                CategoryGenreListItem(
                    buttonModifier = modifier.weight(1f),
                    title = it.title,
                    imageId = it.imageId,
                    genre = it,
                    onGenreClicked = onGenreClicked
                )
            }
        }



        Column(
            modifier = modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text("More genres?",style = MaterialTheme.typography.h6)
            Row(verticalAlignment = Alignment.CenterVertically) {


                Text(
                    text = "Search all genres in ",
                    style = MaterialTheme.typography.subtitle1
                )
                TextButton(onClick = onNavigateToAdvancedSearchScreen,
                contentPadding = PaddingValues(horizontal = 2.dp, vertical = 0.dp)) {
                    Text("ADVANCED SEARCH .")
                }
            }
        }
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryGenreListItem(
    modifier : Modifier = Modifier,
    buttonModifier : Modifier = Modifier,
    title : String,
    imageId : Int,
    genre : Genres,
    onGenreClicked: (Genres) -> Unit
){

    Button(
        modifier = buttonModifier
            .padding(5.dp)
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        ),
        shape = RoundedCornerShape(7),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            onGenreClicked(genre)

        }
    ){
        Column(
            modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(
                            Color(0xff606c88).copy(alpha = 0.5f),
                            Color(0xff606c88).copy(alpha = 0.2f),
                            Color(0xff606c88).copy(alpha = 0.1f),
                            Color(0xff606c88).copy(alpha = 0.2f),
                            Color(0xff606c88).copy(alpha = 0.5f),


                            )
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(painter = painterResource(id = imageId), contentDescription = "",
            modifier.fillMaxSize(0.5f))
            Spacer(modifier = modifier.height(7.dp))
            Text(
                text = title.uppercase(),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.onBackground
            )
        }
    }

}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CategoryGenresListPreview(
    ){
    Scaffold {
        CategoryGenresList(onNavigateToAdvancedSearchScreen = {}, onGenreClicked = {})
    }


}