package com.example.imdbapp.presentation.ui.search

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.SearchResults
import com.example.imdbapp.network.model.listItemMovies.Item
import com.example.imdbapp.network.model.search.Result
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.explore.components.SearchMovieItemCard
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultBottomPageView
import com.example.imdbapp.presentation.ui.loading_and_default_views.DefaultErrorView
import com.example.imdbapp.presentation.ui.scaffold.CustomScaffold
import com.example.imdbapp.presentation.ui.search.components.SearchSuggestMovie
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchView(
    modifier : Modifier = Modifier,
    movies : List<Item>?,
    recentQueries : List<String>,
    onSearchTypeSelected: (SearchType) -> Unit,
    query : String,
    searchType: SearchType,
    onQueryChanged : (String) -> Unit,
    loading : Boolean,
    isExpanded : Boolean,
    searchResults: SearchResults?,
    onGoClicked: () -> Unit,
    onNavigateToResultScreen: (Result) -> Unit,
    onRecentQueriesClicked: (String) -> Unit,
    onNavigateToMovieScreen : (String) -> Unit,
    isOnError : Boolean,
    errorMessage : String



){
    val colors = MaterialTheme.colors

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        if (colors.isLight)
                            listOf(
                                Color(0xff00416A),
                                MaterialTheme.colors.background
                            )
                        else listOf(
                            Color(0xff0F2027), Color.Transparent
                        )
                    )
                )
                .padding(top = TOP_APP_PADDING),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SearchMovieBox(
                recentQueries = recentQueries,
                onSearchTypeSelected = onSearchTypeSelected,
                onQueryChanged = onQueryChanged,
                query = query,
                searchType = searchType,
                isExpanded = isExpanded,
                onGoClicked = onGoClicked,
                onRecentQueriesClicked = onRecentQueriesClicked


            )


            if (movies != null) {
                AnimatedVisibility(visible = !isExpanded) {


                    Column(
                        modifier
                            .fillMaxWidth()
                            .fillMaxHeight()

                    ) {
                        var textValue by remember { mutableStateOf("")}
                        CustomTitleText(
                            text = "Suggestions ...",
                            tint = AlmostYellow,
                            modifier = modifier
                                .padding(horizontal = 15.dp, vertical = 10.dp)
                                .padding(bottom = 10.dp)
                        )

                        SearchSuggestMovie(
                            modifier = modifier,
                            items = movies,
                            onClick = onNavigateToMovieScreen
                        )
                        Spacer(modifier = modifier.height(130.dp))
                    }
                }
                AnimatedVisibility(visible = isExpanded) {
                        SearchResultsLazyColumn(
                            searchResults = searchResults,
                            onNavigateToResultScreen = onNavigateToResultScreen,
                            loading = loading,
                            isOnError = isOnError,
                            errorMessage = errorMessage
                        )




                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchMovieBox(
    modifier : Modifier = Modifier,
    recentQueries: List<String>,
    onQueryChanged: (String) -> Unit,
    query: String,
    searchType: SearchType,
    isExpanded : Boolean,
    onGoClicked : () -> Unit,
    onSearchTypeSelected : (SearchType) -> Unit,
    onRecentQueriesClicked : (String) -> Unit,

) {
    val keyboardController = LocalSoftwareKeyboardController.current


    val scaleX by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0.6f,
        animationSpec = spring(
            dampingRatio = 0.4f,
            stiffness = Spring.StiffnessLow
        )
    )
    val scales by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0.9f,
        animationSpec = spring(
            dampingRatio = 0.2f,
            stiffness = Spring.StiffnessLow
        )
    )
    val alpha by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0.7f,
        animationSpec = spring(
            dampingRatio = 0.6f,
            stiffness = Spring.StiffnessLow
        )
    )
    val transitionY by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 220f,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = Spring.StiffnessMediumLow
        )
    )
    val shapeCorner by animateIntAsState(
        targetValue = if (isExpanded) 1 else 100,
        animationSpec = spring(
            dampingRatio = 1f,
            stiffness = Spring.StiffnessMediumLow
        )
    )
    val indicatorColor = animateColorAsState(
        targetValue =
        if (isExpanded) AlmostYellow else Color.Transparent
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth(
                    scaleX
                )


                .graphicsLayer(
                    translationY = transitionY,
                    clip = true,
                    shape = RoundedCornerShape(shapeCorner.dp),
                    alpha = alpha,
                    scaleY = scales,
                    scaleX = scales
                ),
            shape = RoundedCornerShape(shapeCorner),
            elevation = 40.dp,
            backgroundColor =
            if (MaterialTheme.colors.isLight) Color(0xff00416A) else
                Color(0xff0F2027),
            border = BorderStroke(
                1.dp, Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Gray,


                        ),
                )
            )

        ) {
            Box(
                modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        Brush.linearGradient(
                            GlassyGradient,
                            tileMode = TileMode.Mirror
                        )
                    )
                    .blur(20.dp)
            )
            TextField(
                modifier = modifier.fillMaxWidth(),
                value = query, onValueChange = onQueryChanged,
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = indicatorColor.value,
                    focusedLabelColor = if (MaterialTheme.colors.isLight) {
                        Color.Yellow
                    } else
                        AlmostYellow,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = if (MaterialTheme.colors.isLight) {
                        Color.Yellow
                    } else
                        AlmostYellow,
                    backgroundColor = Color.Transparent,
                    disabledLabelColor = Color.LightGray,
                    unfocusedLabelColor = AlmostSilver,
                    leadingIconColor = AlmostSilver,
                    trailingIconColor = AlmostSilver,
                    textColor = if (MaterialTheme.colors.isLight) {
                        Color.White
                    } else Color.White,



                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onGoClicked()
                        keyboardController?.hide()
                               },
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                shape = RoundedCornerShape(10),
                trailingIcon = {
                    IconButton(onClick = {
                        onGoClicked()
                        keyboardController?.hide()
                    }) {
                        Icon(imageVector = Icons.Rounded.ArrowForward, "")
                    }

                },
                label = {
                    Text(text = "Search", fontWeight = FontWeight.W400)
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, "")
                }
            )

        }
        AnimatedVisibility(visible = isExpanded) {
            Column() {

                SearchRecentQueriesRow(recentQueries = recentQueries, onClick = {
                    onRecentQueriesClicked(it)
                })

                Divider(
                    modifier = modifier
                        .padding(horizontal = 1.dp, vertical = 5.dp)
                        .fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                SearchTypeRow(
                    searchType = searchType,
                    onSelected =onSearchTypeSelected
                )
            }


        }
    }
}






enum class  SearchType(
    val title : String,
){
    Movie(title = "Movie"),
    Series(title = "Series"),
    Name(title = "Name"),
    Company(title = "Company"),
    Title(title = "Title"),
    All(title = "Search All")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchTypeRow( 
    modifier : Modifier = Modifier,
    onSelected : (SearchType) -> Unit,
    searchType : SearchType
) {
    
    Column() {
        Row {
            SearchType.values().toList().subList(0, 3).forEach {
                Box(modifier = modifier.weight(1f)) {
                    SearchTypeRadioButton(
                    selected = searchType == it,
                    onSelected = onSelected,
                    searchType = it
                    )

                }
            }
        }
        Row(horizontalArrangement = Arrangement.Start){
            SearchType.values().toList().subList(3, 6).forEach {
                Box(modifier = modifier.weight(1f)) {
                    SearchTypeRadioButton(
                        selected = searchType == it,
                        onSelected = onSelected,
                        searchType = it
                    )
                }
            }

        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchTypeRadioButton(
    selected : Boolean,
    searchType : SearchType,
    onSelected : (SearchType) -> Unit,
    
){
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected =selected, onClick = {
            onSelected(searchType)
        })
        Text(searchType.title,color = AlmostSilver)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchRecentQueriesRow(
    modifier: Modifier = Modifier,
    recentQueries : List<String>,
    onClick : (String) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,

    ) {
        item {
            Icon(
                modifier = modifier
                    .padding(horizontal = 5.dp)
                    .padding(vertical = 5.dp),
                tint = AlmostYellow,
                painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                contentDescription = ""
            )
        }
        items(recentQueries) {
            TextButton(onClick = {onClick(it)},
            border = BorderStroke(width = 1.dp, color =  NeonGreen),
                shape = RoundedCornerShape(20),
                modifier = modifier
                    .padding(3.dp)
                    .padding(top = 4.dp)
                ) {
                Text(
                    it,
                    modifier = modifier.padding(
                        horizontal = 1.dp,
                        vertical = 5.dp
                    ),
                    color = AlmostSilver
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchResultsLazyColumn(
    modifier : Modifier = Modifier,
    searchResults: SearchResults?,
    onNavigateToResultScreen : (Result) -> Unit,
    loading : Boolean,
    isOnError: Boolean,
    errorMessage: String
){
    AnimatedVisibility(visible = loading, enter = fadeIn(), exit = fadeOut()) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            repeat(3){
                SearchMovieLoadingCard()
            }

        }
    }
    AnimatedVisibility(visible = isOnError) {
        Box(modifier = modifier.fillMaxSize()) {
            DefaultErrorView(
                error = errorMessage,
                backButtonAvailable = false,
                onBackPressed = {})
        }
    }
    if (searchResults != null) {
        searchResults.results?.let {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(searchResults.results) {
                    SearchMovieItemCard(searchResult = it, onClick = onNavigateToResultScreen)

                }
                item {
                    Divider(modifier = modifier.padding(vertical = 10.dp))
                    DefaultBottomPageView()
                    Spacer(modifier.height(74.dp))
                }
            }
        }
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchMovieLoadingCard(
    modifier : Modifier = Modifier
){
    Button(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .aspectRatio(18 / 9f),
        onClick = {

        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
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
                        .aspectRatio(0.7f)
                        .placeholder(
                            visible = true,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(25),
                            highlight = PlaceholderHighlight.fade()
                        ),
                    shape = RoundedCornerShape(25),
                    elevation = 10.dp,
                ) {

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

                                Text(
                                    text = "",
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .placeholder(
                                            visible = true,
                                            color = Color.LightGray,
                                            shape = RoundedCornerShape(30),
                                            highlight = PlaceholderHighlight.fade()
                                        ),
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Cyan
                                )


                                Text("it", color = AlmostYellow,modifier = modifier
                                    .fillMaxWidth()
                                    .placeholder(
                                        visible = true,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(30),
                                        highlight = PlaceholderHighlight.fade()
                                    ),)
                            }



                            Text(
                                text = "it",
                                modifier = modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                                    .placeholder(
                                        visible = true,
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(30),
                                        highlight = PlaceholderHighlight.fade()
                                    ),
                                textAlign = TextAlign.Start,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.White
                            )




                }

            }

        }
    }
}


@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchViewPreview(){
        val recentlist = listOf(
            "Inception",
            "How I met",
            "Good fellas",
            "something else",
            "Dark night",
            "Harry potter",
            "Friends",
        )
    val tabs = remember {BaseSections.values()}
    val navController = rememberNavController()
    IMdbAppTheme() {

        CustomScaffold(tabs = tabs, navController = navController ) {
                val movies = FakeData.SampleTop250
                movies.items?.let { it1 ->
                    SearchView(
                        movies = FakeData.SampleTop250.items?.shuffled()?.subList(0,5),
                        recentQueries = listOf("something","something else"),
                        onSearchTypeSelected = {  },
                        query = "query",
                        loading = false,
                        searchResults = null,
                        searchType = SearchType.Movie,
                        onQueryChanged = {  },
                        isExpanded = false,
                                onGoClicked = {},
                        onRecentQueriesClicked = {},
                        onNavigateToResultScreen = {},
                        onNavigateToMovieScreen = {},
                        isOnError = false,
                        errorMessage = ""
                    ) }
            }
        }

}
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchViewPreviewDark(){
    val recentlist = listOf(
        "Inception",
        "How I met",
        "Good fellas",
        "something else",
        "Dark night",
        "Harry potter",
        "Friends",
    )
    val tabs = remember { BaseSections.values()}
    val navController = rememberNavController()
    IMdbAppTheme(darkTheme = true) {
        CustomScaffold(tabs = tabs, navController = navController ) {
            val movies = FakeData.SampleTop250
            movies.items?.let { it1 ->
                SearchView(
                    movies = FakeData.SampleTop250.items?.shuffled()?.subList(0,5),
                    recentQueries = listOf("something","something else"),
                    onSearchTypeSelected = {  },
                    query = "query",
                    loading = false,
                    searchResults = null,
                    searchType = SearchType.Movie,
                    onQueryChanged = {  },
                    isExpanded = false,
                    onGoClicked = {},
                    onRecentQueriesClicked = {},
                    onNavigateToResultScreen = {},
                    onNavigateToMovieScreen = {},
                    isOnError = false,
                    errorMessage = ""
                ) }
        }
    }

}