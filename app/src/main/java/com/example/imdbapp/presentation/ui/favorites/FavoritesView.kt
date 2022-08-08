package com.example.imdbapp.presentation.ui.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.imdbapp.presentation.theme.AlmostYellow
import com.example.imdbapp.presentation.theme.IMdbAppTheme
import com.example.imdbapp.presentation.ui.home.components.BaseSections
import com.example.imdbapp.presentation.ui.scaffold.CustomScaffold
import com.example.imdbapp.presentation.ui.util.ImageQuality
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.presentation.ui.util.changeImageQuality
import com.example.imdbapp.util.FakeData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


enum class FavoritesTab(val title : String){
    Movies("Movies"),
    Person("Name"),
    Company("Company")
}

@ExperimentalPagerApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesView(
    modifier : Modifier = Modifier,
    viewModel : FavoritesViewModel

){
    val pagerState = rememberPagerState()
    val favoritesTab = FavoritesTab.values()
    val onLoad = viewModel.onLoad.value
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle.observeAsSate()
    val state = lifecycleOwner.value

    if (state == Lifecycle.Event.ON_RESUME || state == Lifecycle.Event.ON_CREATE || state == Lifecycle.Event.ON_START){
        viewModel.getMovie()
        viewModel.getPerson()
    }

    val favoriteMovies = viewModel.favoriteMovies.value
    val favoritePerson = viewModel.favoritePerson.value

    val coroutineScope = rememberCoroutineScope()
    Column() {

        TabRow(
            modifier = modifier.padding(top = TOP_APP_PADDING),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    color = AlmostYellow
                )
            }
        ) {


            favoritesTab.forEachIndexed { index, it ->
                Tab(
                    text = { Text(it.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = favoritesTab.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 ->
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LazyColumn() {
                            items(favoriteMovies) {
                                Text(it.title ?: "Noting")
                            }
                        }
                    }
                1 ->
                    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        LazyColumn() {
                            items(favoritePerson) {
                                Text(it.name ?: "Noting")
                            }
                        }
                    }
                2->{
                    Box(modifier.fillMaxSize())
                }
            }
        }
    }


}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Lifecycle.observeAsSate(): State<Lifecycle.Event> {
    val state = remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            state.value = event
        }
        this@observeAsSate.addObserver(observer)
        onDispose {
            this@observeAsSate.removeObserver(observer)
        }
    }
    return state
}

