package com.example.imdbapp.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.network.model.listItemMovies.ListOfItems
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.home.components.*
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeView(
    modifier : Modifier = Modifier,
    top250 : ListOfItems,
    onNavigateToMovieScreen : (String) -> Unit,
    onNavigateToAdvancedSearchScreen : () -> Unit,
    onNavigateToCategoriesScreen :() -> Unit,
    onNavigateToEachCategoryScreen : (HomeCategoryItems) -> Unit,
    onNavigateToMovieListScreen : (ListOfItems) -> Unit,
    onNavigateToTopMovies : (TopMovieType) -> Unit,

    homeCategoryVerticalPager :
@Composable ()-> Unit,


    ){
    val scrollState = rememberScrollState()
    var scrollToPosition by remember {mutableStateOf(0f)}
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(TOP_APP_PADDING))
        Spacer(modifier = modifier.height(5.dp))

        HomeCategoryAndSearchRowAndCircleCategories(
            rowModifier = modifier.onGloballyPositioned {
                scrollToPosition = it.positionInParent().y
            },
            onNavigateToAdvancedSearchScreen = onNavigateToAdvancedSearchScreen,
            onNavigateToCategoriesScreen = onNavigateToCategoriesScreen, onNavigateToEachCategory = {

                onNavigateToEachCategoryScreen(it)

                coroutineScope.launch {
                    scrollState.animateScrollTo(scrollToPosition.roundToInt())
                }},
            onNavigateToMovieScreen = onNavigateToMovieScreen, homeCategoryVerticalPager = homeCategoryVerticalPager
        )

        Spacer(modifier = modifier.height(25.dp))
        top250.items?.let {
        HomeSuggestionCards(movies = it, onNavigateToMovieScreen = onNavigateToMovieScreen, onSeeMoreClicked = {
            onNavigateToTopMovies(TopMovieType.Top250Movies)
        }) }
        Spacer(modifier = modifier.height(15.dp))

        Row(modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.4f)
            .padding(horizontal = 10.dp)) {
            Box(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                HomeSquareButtons(
                    onNavigateToTopMovies = onNavigateToTopMovies
                )
            }
            Spacer(modifier = modifier.width(5.dp))



        }
        Divider(modifier = modifier.padding(vertical = 10.dp))
        DefaultBottomPageView()

        Spacer(modifier = modifier.height(74.dp))



    }



}


@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeViewPreview(){
        val tabs = remember {BaseSections.values()}
        val navController = rememberNavController()

        IMdbAppTheme() {


            Scaffold(
                bottomBar = { BottomNavBar(tabs = tabs, navController = navController) }
            ) {

                HomeView(
                    top250 = FakeData.SampleTop250,
                    onNavigateToMovieScreen = {},
                    onNavigateToAdvancedSearchScreen = {},
                    onNavigateToCategoriesScreen = {},
                    onNavigateToEachCategoryScreen = {},
                    onNavigateToMovieListScreen = {},
                    onNavigateToTopMovies = {},

                    ) {}
            }
        }


}

@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeViewPreviewDark(){
    val tabs = remember {BaseSections.values()}
    val navController = rememberNavController()

    IMdbAppTheme(
        darkTheme = true
    ) {


        Scaffold(
            bottomBar = { BottomNavBar(tabs = tabs, navController = navController) }
        ) {

            HomeView(
                top250 = FakeData.SampleTop250,
                onNavigateToMovieScreen = {},
                onNavigateToAdvancedSearchScreen = {},
                onNavigateToCategoriesScreen = {},
                onNavigateToEachCategoryScreen = {},
                onNavigateToMovieListScreen = {},
                onNavigateToTopMovies = {}


            ) {}
        }
    }


}