package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.advanced_search.ResultDomain
import com.example.imdbapp.network.model.advanced_search.Result
import com.example.imdbapp.presentation.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryAndSearchRowAndCircleCategories(
    modifier : Modifier = Modifier,
    rowModifier : Modifier = Modifier,
    onNavigateToAdvancedSearchScreen : () -> Unit,
    onNavigateToCategoriesScreen : () -> Unit,
    onNavigateToEachCategory : (HomeCategoryItems) -> Unit,
    homeCategoryVerticalPager :
@Composable ()-> Unit,
    onNavigateToMovieScreen : (String) -> Unit,

){
    val isCollapsed = remember {mutableStateOf(true)}
    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
        .padding(bottom = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)

        ) {
            Box(
                modifier = modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(7.dp),
                contentAlignment = Alignment.Center
            ) {
                HomeCategoryAndSearchRowItem(
                    modifier = modifier,
                    imageId = R.drawable.icon_home_search,
                    title = "Advanced Search".uppercase(),
                    tintGradient = if (MaterialTheme.colors.isLight) listOf(
                        DarkNeonGreen.copy(alpha = 0.5f),
                        DarkNeonGreen.copy(alpha = 0.4f),
                        DarkNeonGreen.copy(alpha = 0.5f),
                        DarkNeonGreen.copy(alpha = 0.7f),
                        DarkNeonGreen.copy(alpha = 0.85f),
                    ) else
                        listOf(
                            DarkNeonGreen.copy(alpha = 0.5f),
                            DarkNeonGreen.copy(alpha = 0.3f),
                            DarkNeonGreen.copy(alpha = 0.35f),
                            DarkNeonGreen.copy(alpha = 0.3f),
                            DarkNeonGreen.copy(alpha = 0.5f),
                        ).reversed(),
                    shape = RoundedCornerShape(
                        10
                    ),
                    onClick = onNavigateToAdvancedSearchScreen
                )
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(7.dp),
                contentAlignment = Alignment.Center
            ) {
                HomeCategoryAndSearchRowItem(
                    modifier = modifier,
                    imageId = R.drawable.icon_home__category,
                    title = "Categories".uppercase(),
                    tintGradient = if (MaterialTheme.colors.isLight) listOf(
                        NeonPink.copy(alpha = 0.5f),
                        NeonPink.copy(alpha = 0.4f),
                        NeonPink.copy(alpha = 0.5f),
                        NeonPink.copy(alpha = 0.7f),
                        NeonPink.copy(alpha = 0.85f),
                    ) else listOf(
                        NeonPink.copy(alpha = 0.5f),
                        NeonPink.copy(alpha = 0.3f),
                        NeonPink.copy(alpha = 0.35f),
                        NeonPink.copy(alpha = 0.3f),
                        NeonPink.copy(alpha = 0.5f),
                    ).reversed(),
                    shape = RoundedCornerShape(
                        10
                    ),
                    onClick = onNavigateToCategoriesScreen
                )
            }
        }
        HomeCategoryRowCircleList(
            rowModifier = rowModifier,
           onNavigateToEachCategory = { item , selected ->
               if (isCollapsed.value) {
                   onNavigateToEachCategory(item)
                   isCollapsed.value = !isCollapsed.value
               }else{
                   if (item != selected){
                       onNavigateToEachCategory(item)
                   }else{
                       isCollapsed.value = true
                   }
               }
              },
            isCollapsed = isCollapsed.value

        )
        AnimatedVisibility(visible = !isCollapsed.value) {
        Box(modifier = modifier
            .fillMaxWidth()
            .height(500.dp), contentAlignment = Alignment.TopStart
        ) {
            homeCategoryVerticalPager()

        }

        }
    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryAndSearchRowItem(
    modifier : Modifier = Modifier,
    imageId : Int,
    title : String,
    tintGradient : List<Color>,
    shape : Shape,
    onClick : () -> Unit
) {
    Button(
        modifier = modifier
            .padding(2.dp)
            .fillMaxSize(1f),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 7.dp,
            pressedElevation = 10.dp
        ),
        shape = shape, onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        )

    ) {
        Column(
            modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        tintGradient
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth(0.55f)
                    .aspectRatio(1f),
                painter = painterResource(id = imageId), contentDescription = ""
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = title,
                fontFamily = FontFamily.SansSerif,
                color = Color.White,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = modifier.height(10.dp))
        }
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryAndSearchPreview(){


        IMdbAppTheme(darkTheme = true) {
            Scaffold() {

        HomeCategoryAndSearchRowAndCircleCategories(onNavigateToAdvancedSearchScreen = { /*TODO*/ }, onNavigateToCategoriesScreen = {}, onNavigateToMovieScreen = {}, onNavigateToEachCategory = {}, homeCategoryVerticalPager = {})

        }
    }
}