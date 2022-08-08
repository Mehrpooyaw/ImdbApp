package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.*


enum class BaseSections(
    val str : String,
    val icon: ImageVector? = null,
    val resource : Int? = null,
    val route : String,
) {
    HOME(str = "Home",icon = Icons.Rounded.Home, route =  "base/home"),
    SEARCH(str = "Search", resource = R.drawable.icon_search, route = "base/search"),
    EXPLORE(str = "Explore",resource = R.drawable.ic_round_explore_28, route = "base/explore"),
    FAVORITES(str = "Favorites",icon = Icons.Rounded.Favorite, route = "base/favorites"),
    SETTINGS(str = "Settings",icon = Icons.Rounded.Settings, route = "base/settings")
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavBar(
    modifier : Modifier = Modifier,
    tabs : Array<BaseSections>,
    navController: NavController,
){
    val isLight = MaterialTheme.colors.isLight
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute =  navBackStackEntry?.destination?.route
    val sections = remember {BaseSections.values()}
    val routes = remember {sections.map { it.route }}
    if (currentRoute in routes) {
        val currentSection = sections.first {
            it.route == currentRoute
        }
        Box(
            modifier = modifier.fillMaxWidth()
        ){
            Spacer(
                modifier
                    .padding(horizontal = 5.dp)
                    .clip(RoundedCornerShape(topEndPercent = 25, topStartPercent = 25))
                    .fillMaxWidth()
                    .height(73.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colors.background.copy(alpha = 0.01f),
                                MaterialTheme.colors.background.copy(alpha = 0.2f),
                                MaterialTheme.colors.background.copy(alpha = 0.5f),
                                MaterialTheme.colors.background.copy(alpha = 0.8f),
                                MaterialTheme.colors.background.copy(alpha = 0.9f),
                                MaterialTheme.colors.background

                            )
                        )
                    )
                    .blur(50.dp)
            )

        }
        Card(
            modifier = modifier
                .padding(horizontal = 30.dp,)
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(63.dp),
            shape = RoundedCornerShape(25),
            elevation = 20.dp
        ) {
            Box(
                modifier.background(
                    Brush.linearGradient(
                        tileMode = TileMode.Mirror,

                    colors = RedSunsetGradient
                )
                )

            ) {
                Spacer(modifier = modifier
                    .fillMaxSize()
                    .background(
                        if (MaterialTheme.colors.isLight)
                            Color.Black.copy(0.2f)
                        else
                            Color.Transparent
                    ))
                BottomNavigation(
                    modifier
                        .fillMaxSize()

                        .shadow(elevation = 0.dp, clip = true, shape = RoundedCornerShape(10.dp))
                        ,
                    elevation = 0.dp,
                    backgroundColor =
                        if (MaterialTheme.colors.isLight)
                            MaterialTheme.colors.primary
                        else
                            Color.Transparent



                ) {
                    tabs.forEach { section ->
                        val selected = section == currentSection
                        val tint by animateColorAsState(
                            if (selected) Color.White else Color.White.copy(alpha = 0.85f)
                        )
                        val brightness by animateColorAsState(
                            if (selected) Color.White else Color.Transparent
                        )
                        val scale by animateFloatAsState(targetValue =
                            if (selected) 1f else 0.8f,
                            animationSpec = spring(
                                dampingRatio = 0.4f,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        )
                        val position by animateIntAsState(targetValue =
                        if (selected) 5 else 19,
                            animationSpec = spring(
                                dampingRatio = 0.4f,
                                stiffness = Spring.StiffnessMediumLow
                            )
                        )

                        BottomNavigationItem(
                            selected = selected,
                            onClick = {
                                if (section.route != currentRoute) {
                                    navController.navigate(section.route) {
                                        launchSingleTop = true
                                        restoreState = true
                                        popUpTo(findStartDestination(navController.graph).id) {
                                            saveState = true
                                        }
                                    }
                                }
                            },
                            icon = {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = modifier.padding(top = position.dp)
                                ) {
                                    if (section.icon != null) {
                                        Icon(
                                            imageVector = section.icon,
                                            contentDescription = "",
                                            tint = tint,
                                            modifier = modifier
                                                .graphicsLayer {
                                                    scaleX = scale
                                                    scaleY = scale
                                                    alpha = scale
                                                }
                                                .size(28.dp)
                                        )

                                    } else {
                                        section.resource?.let {
                                            Icon(
                                                painter = painterResource(id = it),
                                                contentDescription = "",
                                                tint = tint,
                                                modifier = modifier
                                                    .graphicsLayer {
                                                        scaleX = scale
                                                        scaleY = scale
                                                        alpha = scale
                                                    }
                                                    .size(28.dp)
                                            )
                                        }

                                    }
                                    Spacer(modifier = modifier.height(5.dp))
                                    Text(
                                        text =section.str.uppercase(),
                                        color = brightness,
                                        style = MaterialTheme.typography.caption,
                                        fontSize = 9.sp
                                        )
                                }
                            },

                            )
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBar(
    modifier : Modifier = Modifier
){
    Box() {
        Column() {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(Color(11, 16, 34, 255))
            ) {
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .clip(RoundedCornerShape(bottomStartPercent = 25, bottomEndPercent = 25))
                    .background(Color(11, 16, 34, 255))
            )
            Spacer(modifier = modifier.height(30.dp))
        }
        Card(
            modifier = modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter),
            elevation = 20.dp,
            shape = RoundedCornerShape(10)
        ){
            Row(
                modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Search in imDb ...",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold
                )
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
            }

        }

    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomTopAppBar(
    modifier : Modifier = Modifier
){
    Card(modifier = modifier
        .fillMaxWidth()
        .height(56.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(bottomEndPercent = 15, bottomStartPercent = 15),


        ) {
        Row(
            modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        if (MaterialTheme.colors.isLight) {
                            listOf(Color(11, 16, 34, 255), Color(11, 16, 34, 255))
                        } else {
                            listOf(
                                MaterialTheme.colors.background,
                                TitaniumGradient[1].copy(alpha = 0.7f)
                            )
                        }

                    )
                )
                .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
            ){
            Text(
                text = "IMooneyDb",
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp,
                fontWeight = FontWeight.W900,
                color = AlmostSilver,

                )
            Icon(Icons.Rounded.Search, contentDescription = "", tint = AlmostSilver)
        }
    }

}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavBarPreview(){
    val navController = rememberNavController()
    val tabs = remember {BaseSections.values()}
    IMdbAppTheme() {


        Scaffold(
            topBar = { CustomTopAppBar() },
            bottomBar = {
                BottomNavBar(
                    tabs = tabs,
                    navController = navController
                )

            },

        ) {
            BoxWithTitleAndMoreButton(
                onMoreClick = {},
                title = "Top 1000",
                subTitle = "Categories"
            ) {
                HomeCategoryRowCircleList(onNavigateToEachCategory = { i , i2 ->

                }, isCollapsed = true)
            }

        }
    }

}

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomNavBarPreviewDark(){
    val navController = rememberNavController()
    val tabs = remember {BaseSections.values()}
    IMdbAppTheme(darkTheme = true) {
        Scaffold(
            topBar = { CustomTopAppBar() },
            bottomBar = {
                BottomNavBar(
                    tabs = tabs,
                    navController = navController
                )
            },

        ) {
            BoxWithTitleAndMoreButton(
                onMoreClick = {},
                title = "Top 1000",
                subTitle = "Categories"
            ) {
                HomeCategoryRowCircleList(onNavigateToEachCategory = {i , i2 ->}, isCollapsed = true)
            }

        }
    }


}
private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)


/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}