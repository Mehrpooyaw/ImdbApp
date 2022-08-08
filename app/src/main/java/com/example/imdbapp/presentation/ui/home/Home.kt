package com.example.imdbapp.presentation.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.imdbapp.domain.model.advanced_search.TopMovieType
import com.example.imdbapp.network.model.listItemMovies.ListOfItems
import com.example.imdbapp.network.model.search.Result
import com.example.imdbapp.presentation.ui.categories.components.Genres
import com.example.imdbapp.presentation.ui.explore.ExploreScreen
import com.example.imdbapp.presentation.ui.explore.ExploreViewModel
import com.example.imdbapp.presentation.ui.favorites.FavoritesView
import com.example.imdbapp.presentation.ui.favorites.FavoritesViewModel
import com.example.imdbapp.presentation.ui.genres.GenreVerticalPagerScreen
import com.example.imdbapp.presentation.ui.genres.GenresEvent
import com.example.imdbapp.presentation.ui.genres.GenresViewModel
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.home.components.HomeCategoryItems
import com.example.imdbapp.presentation.ui.search.SearchScreen
import com.example.imdbapp.presentation.ui.search.SearchViewModel
import com.example.imdbapp.presentation.ui.settings.SettingsScreen
import com.example.imdbapp.presentation.ui.settings.SettingsViewModel
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
fun NavGraphBuilder.addBaseGraph(
    modifier: Modifier = Modifier,
    onNavigateToAdvancedSearchScreen: () -> Unit,
    onNavigateToMovieScreen: (String) -> Unit,
    onNavigateToCategoryScreen:(HomeCategoryItems) -> Unit,
    onNavigateToCategoryListScreen: () -> Unit,
    onNavigateToMovieListScreen: (ListOfItems, ) -> Unit,
    onNavigateToTopMovies: (TopMovieType) -> Unit,
    onNavigateToResultScreen: (Result) -> Unit,
    toggleTheme: () -> Unit,
    onNavigateToLoginWebView : (String) -> Unit,
    onNavigateToRegisterWebView : (String) -> Unit,
    navController: NavHostController,
    exitApp : () -> Unit,


){
    composable(BaseSections.HOME.route){
        BackHandler() {
            exitApp()
        }
        val factory = HiltViewModelFactory(LocalContext.current,it)
        val viewModel : GenresViewModel= viewModel(factory = factory, key = "GenresViewModel")
        var selectedGenre by remember {mutableStateOf(HomeCategoryItems.Animation)}

        HomeView(
            modifier = modifier,
            top250 = FakeData.SampleTop250, onNavigateToMovieScreen = onNavigateToMovieScreen,
            onNavigateToAdvancedSearchScreen = onNavigateToAdvancedSearchScreen,
            onNavigateToCategoriesScreen = onNavigateToCategoryListScreen,
            onNavigateToEachCategoryScreen = { category->
                 viewModel.onTriggerEvent(GenresEvent.GetByGenre(Genres.valueOf(category.name)))
            },
            onNavigateToMovieListScreen = onNavigateToMovieListScreen,
            onNavigateToTopMovies = onNavigateToTopMovies
        ) {
            GenreVerticalPagerScreen(
                viewModel = viewModel,
                genre = selectedGenre.name,
                onBackPressed = { /*TODO*/ },
                onNavigateToMovieScreen = onNavigateToMovieScreen
            )
        }


    }
    composable(BaseSections.SEARCH.route){
        BackHandler() {
            navController.navigate(BaseSections.HOME.route)
        }
        val factory = HiltViewModelFactory(LocalContext.current,it)
        val viewModel : SearchViewModel = viewModel(key = "SearchViewModel", factory = factory)
        SearchScreen(viewModel = viewModel, onNavigateToResultScreen = onNavigateToResultScreen, onNavigateToMovieScreen = onNavigateToMovieScreen)
    }
    composable(BaseSections.EXPLORE.route){
        BackHandler() {
            navController.navigate(BaseSections.HOME.route)
        }
        val factory = HiltViewModelFactory(LocalContext.current,it)
        val viewModel : ExploreViewModel = viewModel(key = "ExploreViewModel", factory = factory)
        ExploreScreen(viewModel = viewModel, onNavigateToMovieScreen = onNavigateToMovieScreen)
    }
    composable(BaseSections.FAVORITES.route) {
        BackHandler() {
            navController.navigate(BaseSections.HOME.route)
        }
        val factory = HiltViewModelFactory(LocalContext.current,it)
        val viewModel : FavoritesViewModel = viewModel(key = "FavoriteViewModel", factory = factory)
        FavoritesView(viewModel = viewModel)
    }
    composable(BaseSections.SETTINGS.route) {
        BackHandler() {
            navController.navigate(BaseSections.HOME.route)
        }
        val factory = HiltViewModelFactory(LocalContext.current,it)
        val viewModel : SettingsViewModel  = viewModel(key = "SettingsViewModel", factory = factory)
        SettingsScreen(viewModel = viewModel, toggleTheme = toggleTheme, onNavigateToRegisterWebView = onNavigateToRegisterWebView, onNavigateToLoginView = onNavigateToLoginWebView)
    }
}