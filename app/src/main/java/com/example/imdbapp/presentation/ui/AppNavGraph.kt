package com.example.imdbapp.presentation.ui

import android.content.pm.ActivityInfo
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdbapp.presentation.ui.MainDestinations.ADVANCED_SEARCH_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.BASE_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.CATEGORIES_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.CATEGORY_ID_KEY
import com.example.imdbapp.presentation.ui.MainDestinations.GENRES_ID_KEY
import com.example.imdbapp.presentation.ui.MainDestinations.GENRES_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.MOVIE_ID_KEY
import com.example.imdbapp.presentation.ui.MainDestinations.MOVIE_LIST_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.MOVIE_REVIEWS_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.MOVIE_SCREEN_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.PERSON_ID_KEY
import com.example.imdbapp.presentation.ui.MainDestinations.PERSON_SCREEN_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.SPLASH_SCREEN_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.TOP_MOVIE_ROUTE
import com.example.imdbapp.presentation.ui.MainDestinations.TOP_MOVIE_TYPE
import com.example.imdbapp.presentation.ui.MainDestinations.WEB_URL_KEY
import com.example.imdbapp.presentation.ui.MainDestinations.WEB_VIEW_ROUTE
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchScreen
import com.example.imdbapp.presentation.ui.advanced_search.AdvancedSearchViewModel
import com.example.imdbapp.presentation.ui.categories.CategoriesView
import com.example.imdbapp.presentation.ui.genres.GenreListScreen
import com.example.imdbapp.presentation.ui.genres.GenreVerticalPagerScreen
import com.example.imdbapp.presentation.ui.genres.GenresViewModel
import com.example.imdbapp.presentation.ui.home.addBaseGraph
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.movie.MovieDetailScreen
import com.example.imdbapp.presentation.ui.movie.MovieViewModel
import com.example.imdbapp.presentation.ui.person.PersonDetailScreen
import com.example.imdbapp.presentation.ui.person.PersonViewModel
import com.example.imdbapp.presentation.ui.reviews.ReviewsScreen
import com.example.imdbapp.presentation.ui.reviews.ReviewsViewModel
import com.example.imdbapp.presentation.ui.splash.SplashView
import com.example.imdbapp.presentation.ui.top_movies.TopMovieViewModel
import com.example.imdbapp.presentation.ui.top_movies.TopMoviesScreen
import com.example.imdbapp.presentation.ui.util.LockScreenOrientation
import com.example.imdbapp.presentation.ui.web.WebView
import com.google.accompanist.pager.ExperimentalPagerApi
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object MainDestinations {
    const val BASE_ROUTE = "base"
    const val ADVANCED_SEARCH_ROUTE = "advanced_search"
    const val CATEGORIES_ROUTE = "categories"
    const val CATEGORY_ID_KEY = "categoryId"
    const val MOVIE_SCREEN_ROUTE = "movie"
    const val MOVIE_ID_KEY = "movieId"
    const val PERSON_SCREEN_ROUTE = "person"
    const val PERSON_ID_KEY = "personId"
    const val COMPANY_SCREEN_ROUTE = "company"
    const val COMPANY_ID_KEY = "companyId"
    const val MOVIE_LIST_ROUTE = "movies"
    const val MOVIE_REVIEWS_ROUTE = "reviews"
    const val WEB_VIEW_ROUTE = "web_view"
    const val WEB_URL_KEY = "url"
    const val TOP_MOVIE_ROUTE = "top_movies"
    const val TOP_MOVIE_TYPE = "topMovieType"
    const val GENRES_ROUTE = "genres"
    const val GENRES_ID_KEY = "genreId"
    const val SPLASH_SCREEN_ROUTE = "splash"

}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SPLASH_SCREEN_ROUTE,
    toggleTheme: () -> Unit,
    addToApiKeys : (String) -> Unit,
    removeFromApiKeys : (String) -> Unit,
    exitApp : () -> Unit,
){
    NavHost(navController = navController,
        startDestination = startDestination ){
        composable(SPLASH_SCREEN_ROUTE){
            SplashView() {
                navController.navigate(BASE_ROUTE)
            }
        }
        navigation(
            route = MainDestinations.BASE_ROUTE,
            startDestination = BaseSections.HOME.route,
        ){
            addBaseGraph(
                onNavigateToMovieScreen =
                { movieId ->
                navController.navigate("$MOVIE_SCREEN_ROUTE/$movieId")

                },
                onNavigateToCategoryScreen =
                {  categoryItem ->
                navController.navigate("$CATEGORIES_ROUTE/${categoryItem.name}")
                    Log.d("appDebug","AppNavGraph: categoryItem is ${categoryItem.name}")
                },
                onNavigateToCategoryListScreen =
                {
                navController.navigate(CATEGORIES_ROUTE)
                },
                onNavigateToTopMovies =
                { topMovieType ->
                navController.navigate("$TOP_MOVIE_ROUTE/${topMovieType.name}")

                },
                onNavigateToAdvancedSearchScreen =
                {
                navController.navigate(ADVANCED_SEARCH_ROUTE)
                },
                onNavigateToMovieListScreen =
                { listOfItems ->
                    navController.navigate("$MOVIE_LIST_ROUTE/$listOfItems")
                },
                onNavigateToResultScreen = { it.id

                        when (it.resultType) {
                            "Name" ->
                                navController.navigate("$PERSON_SCREEN_ROUTE/${it.id}")
                            "Title" ->
                                navController.navigate("$MOVIE_SCREEN_ROUTE/${it.id}")
                            "Movie" ->
                                navController.navigate("$MOVIE_SCREEN_ROUTE/${it.id}")
                            "Series" ->
                                navController.navigate("$MOVIE_SCREEN_ROUTE/${it.id}")

                            "Company"->
                                Toast.makeText(navController.context,"It has not been completed yet.",Toast.LENGTH_LONG).show()

                        }
                },
                toggleTheme = toggleTheme,
                onNavigateToLoginWebView = {
                    val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())

                    navController.navigate("$WEB_VIEW_ROUTE/$encodedUrl")
                },
            onNavigateToRegisterWebView = {
                val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())

                navController.navigate("$WEB_VIEW_ROUTE/$encodedUrl")

                },
                exitApp = exitApp,
                navController = navController




            )


        }


        composable(
            route = ADVANCED_SEARCH_ROUTE
        ){
            val factory = HiltViewModelFactory(LocalContext.current,it)
            val viewModel : AdvancedSearchViewModel = viewModel(factory = factory, key = "AdvancedSearchViewModel")
            AdvancedSearchScreen(
                viewModel = viewModel,
                onNavigateToMovieScreen = { str ->
                    Log.d("appDebug","on Navigate in Advanced search : is ${{MOVIE_SCREEN_ROUTE}}/$str")
                    navController.navigate("$MOVIE_SCREEN_ROUTE/$str")
                },
                onBackPressed = {
                    navController.navigateUp()

                })


        }

        composable(
            route = "${CATEGORIES_ROUTE}/{$CATEGORY_ID_KEY}",
            arguments = listOf(navArgument(CATEGORY_ID_KEY){
                type = NavType.StringType
            })
        ){
            val id = requireNotNull(it.arguments).getString(CATEGORY_ID_KEY)
            val factory = HiltViewModelFactory(LocalContext.current,it)
            val viewModel : GenresViewModel = viewModel(factory = factory,key = "GenresViewMovel")

                GenreVerticalPagerScreen(
                    viewModel = viewModel ,
                    genre = id,
                    onNavigateToMovieScreen = {
                        navController.navigate("$MOVIE_SCREEN_ROUTE/$it")
                    }, onBackPressed = {
                        navController.navigateUp()
                    })


        }
        composable(
            route = "${GENRES_ROUTE}/{$GENRES_ID_KEY}",
            arguments = listOf(navArgument(GENRES_ID_KEY){
                type = NavType.StringType
            })
        ){
            val id = requireNotNull(it.arguments).getString(GENRES_ID_KEY)
            val factory = HiltViewModelFactory(LocalContext.current,it)
            val viewModel : GenresViewModel = viewModel(factory = factory,key = "GenresViewMovel")

            GenreListScreen(
                viewModel = viewModel ,
                genre = id,
                onNavigateToMovieScreen = {
                    navController.navigate("$MOVIE_SCREEN_ROUTE/$it")
                }, onBackPressed = {
                    navController.navigateUp()
                })


        }


        composable(
            route = "${MOVIE_SCREEN_ROUTE}/{$MOVIE_ID_KEY}",
            arguments = listOf(navArgument(MOVIE_ID_KEY) {
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
            val viewModel : MovieViewModel = viewModel(key = "MovieDetailViewModel", factory = factory)
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val movieId = arguments.getString(MOVIE_ID_KEY)
                MovieDetailScreen(movieId = movieId, viewModel = viewModel,
                    onPersonClicked ={
                        navController.navigate("$PERSON_SCREEN_ROUTE/$it")},
                    onNavigateToActorsScreen = {
                        navController.navigate(PERSON_SCREEN_ROUTE)},
                    onMovieClicked ={
                        navController.navigate("$MOVIE_SCREEN_ROUTE/$it")
                    },
                    onNavigateToWikipediaScreen = {},
                    onNavigateToReviewScreen = {

                        navController.navigate("$MOVIE_REVIEWS_ROUTE/$it")
                    },
                    onNavigateToCompanyScreen = {},
                    onNavigateToTrailerScreen = {
                        val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())

                        navController.navigate("${WEB_VIEW_ROUTE}_land/${encodedUrl}")
                    },
                    onBackPressed = {
                        navController.navigateUp()
                    }
                    )

        }
        composable(
            route = "${WEB_VIEW_ROUTE}/{$WEB_URL_KEY}",
            arguments = listOf(navArgument(WEB_URL_KEY){
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val url = arguments.getString(WEB_URL_KEY)
            WebView(url = url)

        }
        composable(
            route = "${WEB_VIEW_ROUTE}_land/{$WEB_URL_KEY}",
            arguments = listOf(navArgument(WEB_URL_KEY){
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val url = arguments.getString(WEB_URL_KEY)
            LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            WebView(url = url)

        }

        composable(
            route = CATEGORIES_ROUTE,
        ){
            CategoriesView(
                onTopMovieClicked = {
                    navController.navigate("$TOP_MOVIE_ROUTE/${it.name}")

                }, onGenreClicked = {
                    navController.navigate("$GENRES_ROUTE/${it.name}")

                },
                onNavigateToAdvancedSearchScreen = {
                    navController.navigate(ADVANCED_SEARCH_ROUTE)
                }
            )
        }

        composable(
            route = "${TOP_MOVIE_ROUTE}/{$TOP_MOVIE_TYPE}",
            arguments = listOf(navArgument(TOP_MOVIE_TYPE) {
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
            val viewModel : TopMovieViewModel = viewModel(key = "TopMovieViewModel", factory = factory)
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val type = arguments.getString(TOP_MOVIE_TYPE)
            if (type != null) {
                TopMoviesScreen(viewModel = viewModel, type = type, popUp = {navController.navigateUp()}, onMovieClicked = {
                    navController.navigate("$MOVIE_SCREEN_ROUTE/$it")
                }  )
            }
        }



        composable(
            route = "${MOVIE_REVIEWS_ROUTE}/{$MOVIE_ID_KEY}",
            arguments = listOf(navArgument(MOVIE_ID_KEY) {
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current,navBackStackEntry)
            val viewModel : ReviewsViewModel = viewModel(key = "ReviewsViewModel", factory = factory)
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val movieId = arguments.getString(MOVIE_ID_KEY)
            ReviewsScreen(viewModel = viewModel, id = movieId )
        }

        composable(
            route = "${PERSON_SCREEN_ROUTE}/{$PERSON_ID_KEY}",
            arguments = listOf(navArgument(PERSON_ID_KEY){
                type = NavType.StringType
            })
        ){ navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry = navBackStackEntry)
            val viewModel : PersonViewModel = viewModel(key = "PersonDetailViewModel", factory = factory)
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val personId = arguments.getString(PERSON_ID_KEY)
            PersonDetailScreen(personId = personId, viewModel = viewModel, onNavigateToMovieScreen = {
                navController.navigate("$MOVIE_SCREEN_ROUTE/$it")
            },onBackPressed = {
                navController.navigateUp()

            })
        }

    }

}
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED