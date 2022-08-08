package com.example.imdbapp.presentation.ui.advanced_search

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.interactors.advanced_search.*
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.BackButton

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchView(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    setByDropMenu: (DropMenuType, String) -> Unit,
    onCheckedChange: (Boolean, String) -> Unit,
    setTitle : (String) -> Unit,
    title : String,
    isLoading : Boolean,
    onSearchClicked : () -> Unit
){
    val scrollState = rememberScrollState()
    var optionList : MutableList<String> = remember{ mutableListOf() }
    var isSearchAll by remember { mutableStateOf(true)}
    var searchType by remember { mutableStateOf(AdvancedSearchType.SearchAll)}
    val color by animateColorAsState(targetValue =
    if (MaterialTheme.colors.isLight) Purple700 else MaterialTheme.colors.secondary
    )
    Scaffold(
       floatingActionButton = {
           AnimatedVisibility(visible = !isLoading) {


               FloatingActionButton(
                   onClick = onSearchClicked,
                   modifier = modifier
                       .padding(bottom = 20.dp)
                       .size(140.dp, 48.dp),

                   ) {
                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                   ) {
                       Text(text = "Search", color = AlmostSilver)
                       Spacer(modifier = modifier.width(7.dp))
                       Icon(
                           imageVector = Icons.Rounded.Search,
                           contentDescription = "",
                           tint = AlmostSilver
                       )
                   }
               }
           }
       }
    ) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        if (MaterialTheme.colors.isLight)
                            MaterialTheme.colors.surface
                        else
                            Color.DarkGray
                    )
                    .verticalScroll(scrollState)
            ) {
                AdvancedSearchTopBar(onBackPressed = onBackPressed)

                Spacer(modifier = modifier.height(15.dp))

                Card(
                    modifier = modifier
                        .padding(horizontal = 50.dp)
                        .fillMaxWidth(),
                    elevation = 25.dp,
                    shape = RoundedCornerShape(50),
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    TabRow(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.3f),
                        indicator = {
                            AdvancedSearchTabIndicator(
                                it,
                                type = searchType
                            )
                        },
                        contentColor = color,
                        selectedTabIndex = if (isSearchAll) 0 else 1
                    ) {
                        Tab(
                            modifier = modifier
                                .clip(RoundedCornerShape(100))
                                .padding(3.dp),
                            selected = searchType == AdvancedSearchType.SearchAll, onClick = {
                                searchType = AdvancedSearchType.SearchAll
                            }) {
                            Text(
                                "Search All", modifier = modifier.padding(vertical = 8.dp),
                                color = if (searchType == AdvancedSearchType.SearchAll) color else AlmostSilver
                            )

                        }
                        Tab(
                            modifier = modifier
                                .clip(RoundedCornerShape(100))
                                .padding(3.dp),
                            selected = searchType == AdvancedSearchType.Top1000, onClick = {
                                searchType = AdvancedSearchType.Top1000
                            }) {
                            Text(
                                text = "Top 1000", modifier = modifier.padding(vertical = 8.dp),
                                color = if (searchType == AdvancedSearchType.Top1000) color else AlmostSilver
                            )

                        }
                    }
                }
                Spacer(modifier = modifier.height(15.dp))
                AdvancedSearchTextFields(setTitle = setTitle, title = title)


                Spacer(modifier = modifier.height(10.dp))

                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 7.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = if (MaterialTheme.colors.isLight) Color.White else MaterialTheme.colors.background
                ) {

                    Column(modifier.padding(vertical = 15.dp, horizontal = 5.dp)) {
                        Text(" Genres", style = MaterialTheme.typography.h6)
                        GenreListView(onCheckedChange = onCheckedChange)
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
                AdvancedSearchImdbRow(setByDropMenu = setByDropMenu)
                Spacer(modifier = modifier.height(10.dp))
                AdvancedSearchYearRow(toOrFromStr = "from", setByDropMenu = setByDropMenu)
                Spacer(modifier = modifier.height(10.dp))
                AdvancedSearchYearRow(toOrFromStr = "to", setByDropMenu = setByDropMenu)
                Spacer(modifier = modifier.height(10.dp))

                Spacer(modifier.height(90.dp))


            }
            AnimatedVisibility(
                visible = isLoading,
                enter = fadeIn(),
                exit = fadeOut()
                ) {
                Box(
                    modifier = modifier
                        .fillMaxSize()

                        .clickable { },
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(
                        modifier = modifier
                            .blur(70.dp)
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(

                                        TitaniumGradient[0].copy(0.9f),
                                        TitaniumGradient[1].copy(0.9f),

                                        )

                                )
                            )
                    )
                    Card(
                        modifier = modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f),
                        elevation = 10.dp,
                        shape = RoundedCornerShape(20)
                    ) {
                        Box(
                            modifier = modifier
                                .fillMaxSize()
                                .background(
                                    Brush.linearGradient(
                                        RedSunsetGradient

                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            CircularProgressIndicator(
                                modifier = modifier.size(70.dp),
                                strokeWidth = 5.dp,
                                color = AlmostSilver
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
fun AdvancedSearchTopBar(
    modifier: Modifier = Modifier,
    onBackPressed : () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    if (MaterialTheme.colors.isLight) listOf(
                        DarkNeonGreen.copy(alpha = 0.5f),
                        DarkNeonGreen.copy(alpha = 0.4f),
                        DarkNeonGreen.copy(alpha = 0.5f),
                        DarkNeonGreen.copy(alpha = 0.65f),
                        DarkNeonGreen.copy(alpha = 0.75f),
                        DarkNeonGreen.copy(alpha = 0.85f),
                    ).reversed() else
                        listOf(
                            DarkNeonGreen.copy(alpha = 0.5f),
                            DarkNeonGreen.copy(alpha = 0.3f),
                            DarkNeonGreen.copy(alpha = 0.35f),
                            DarkNeonGreen.copy(alpha = 0.3f),
                            DarkNeonGreen.copy(alpha = 0.5f),
                        ).reversed(),
                )

            )
    ) {
        Spacer(modifier = modifier.padding(top = 20.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackButton(tint = AlmostSilver, onBackPressedClick = onBackPressed)
                    Spacer(modifier = modifier.width(7.dp))
                    Text(
                        text = "Advanced search",
                        color = AlmostSilver,
                        style = MaterialTheme.typography.h5,
                    )
                }
                Box(
                    modifier = modifier.padding(horizontal = 15.dp, vertical = 5.dp)
                ) {
                    Text("All fields are optional.",color = AlmostSilver)
                }
            }
            Image(
                modifier = modifier
                    .padding(10.dp)
                    .padding(top = 10.dp, end = 10.dp),
                painter = painterResource(id = R.drawable.icon_home_search), contentDescription = "")
        }


    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchTextFields(modifier : Modifier = Modifier,setTitle: (String) -> Unit,title: String){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
                        backgroundColor = if (MaterialTheme.colors.isLight) Color.White else MaterialTheme.colors.background

        ,
        elevation = 15.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(modifier = modifier.fillMaxWidth()) {

            Row(verticalAlignment = Alignment.CenterVertically,modifier = modifier
                .padding(horizontal = 15.dp,)
                .padding(top = 10.dp)) {
                Text("Title", style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = modifier.height(5.dp))
            OutlinedTextField(
                modifier = modifier
                    .padding(horizontal = 15.dp, vertical = 5.dp)
                    .padding(bottom = 10.dp)
                    .fillMaxWidth(),
                value = title, onValueChange = {
                    setTitle(it)
                },
            shape = RoundedCornerShape(20),
            label = {
                Text("Enter a part or all of the title.(Optional)")
            }
                )
        }

    }
}


@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchYearRow(modifier : Modifier = Modifier,
    toOrFromStr : String,
    setByDropMenu: (DropMenuType, String) -> Unit
                          ){
    var isEnabled by remember { mutableStateOf(false)}
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
                        backgroundColor = if (MaterialTheme.colors.isLight) Color.White else MaterialTheme.colors.background

        ,
                elevation = 15.dp,
            shape = RoundedCornerShape(15.dp)
    ) {
        Column(modifier.padding(vertical = 15.dp, horizontal = 7.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(selected = isEnabled, onClick = {
                    isEnabled = !isEnabled
                })
                Text("Release Date - $toOrFromStr", style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = modifier.height(5.dp))
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .padding(horizontal = 3.dp)
                        .weight(1f)
                ) {
                    AdvancedSearchDropMenu(
                        label = "YYYY",
                        list = if (toOrFromStr == "from") year.reversed() else year,
                        isEnabled = isEnabled, type = if (toOrFromStr == "from") DropMenuType.DateFrom else DropMenuType.DateTo , setByDropMenu = setByDropMenu
                    )
                }

                Box(
                    modifier = modifier
                        .padding(horizontal = 3.dp)
                        .weight(1f)
                ) {
                    AdvancedSearchDropMenu(
                        label = "MM",
                        list = if (toOrFromStr == "from") month  else month.reversed(),
                        isEnabled = isEnabled, type = if (toOrFromStr == "from") DropMenuType.DateFrom else DropMenuType.DateTo, setByDropMenu = setByDropMenu
                    )
                }
                Box(
                    modifier = modifier
                        .padding(horizontal = 3.dp)
                        .weight(1f)
                ) {
                    AdvancedSearchDropMenu(
                        label = "DD",
                        list = if (toOrFromStr == "from") day else day.reversed(),
                        isEnabled = isEnabled, type = if (toOrFromStr == "from") DropMenuType.DateFrom else DropMenuType.DateTo, setByDropMenu = setByDropMenu
                    )
                }
            }


        }
    }
}


@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchImdbRow(
    modifier : Modifier = Modifier,
    setByDropMenu: (DropMenuType, String) -> Unit
){
    var isEnabled by remember{ mutableStateOf(false)}

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        elevation = 15.dp,
        shape = RoundedCornerShape(15.dp),
                        backgroundColor = if (MaterialTheme.colors.isLight) Color.White else MaterialTheme.colors.background


    ){


        Column(modifier.padding(vertical = 15.dp, horizontal = 10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(selected = isEnabled, onClick = {
                    isEnabled = !isEnabled
                })
                Text("Imdb Rate", style = MaterialTheme.typography.h6)
            }
            Spacer(modifier = modifier.height(3.dp))
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = modifier
                        .padding(horizontal = 3.dp)
                        .weight(1f)
                ) {
                    AdvancedSearchDropMenu(label = "from", list = imdbRates, isEnabled = isEnabled, type = DropMenuType.ImdbRatingFrom , setByDropMenu = setByDropMenu)
                }
                Box(
                    modifier = modifier
                        .padding(horizontal = 3.dp)
                        .weight(1f)
                ) {
                    AdvancedSearchDropMenu(label = "to", list = imdbRates, isEnabled = isEnabled, type = DropMenuType.ImdbRatingTo, setByDropMenu = setByDropMenu,)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchDropMenu(
    modifier: Modifier = Modifier,
    label : String,
    list : List<String>,
    isEnabled : Boolean,
    setByDropMenu : (DropMenuType, String) -> Unit,
    type : DropMenuType
){

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText = remember { mutableStateOf(list[0])}

        ExposedDropdownMenuBox(
            expanded = expanded, onExpandedChange ={
            expanded = !expanded
        },
            modifier = modifier.clip(RoundedCornerShape(10))

        ){
            TextField(
                enabled = isEnabled,
                readOnly = true,
                value = selectedOptionText.value.toString(),
                onValueChange = { },
                label = { Text(text = label,color = Color.Gray) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.secondary.copy(0.1f)
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
            ) {
                list.forEach { selectionOption ->
                    DropdownMenuItem(
                        enabled = isEnabled,
                        onClick = {
                            selectedOptionText.value = selectionOption
                            setByDropMenu(
                                type
                                ,selectionOption
                            )
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }


    }
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GenreListView(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean , String) -> Unit
){
    val firstList = remember {mutableListOf<AdvancedGenres>()}
    val secondList = remember { mutableListOf<AdvancedGenres>()}
    val thirdList = remember {   mutableListOf<AdvancedGenres>()}
    AdvancedGenres.values().forEachIndexed{index, genres ->
        when {
            (index + 3) % 3 == 0 ->
                firstList.add(genres)

            (index + 2) %3 == 0 ->
                secondList.add(genres)

            else ->
                thirdList.add(genres)
        }
    }
    Row() {


        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            firstList.forEach {
                AdvancedOptionButton(
                    title = it.title,
                    key = it.str,
                    onCheckedChange = onCheckedChange
                )
            }
        }
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            secondList.forEach {
                AdvancedOptionButton(
                    title = it.title,
                    key = it.str,
                    onCheckedChange = onCheckedChange
                )
            }
        }
        Column(
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            thirdList.forEach {
                AdvancedOptionButton(
                    title = it.title,
                    key = it.str,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedOptionButton(
    modifier: Modifier = Modifier,
    title : String,
    key : String,
    onCheckedChange : (Boolean , String) -> Unit,
    checked : Boolean = false,
){
    var isChecked by remember { mutableStateOf(checked) }
    Row(modifier = modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked = it
            onCheckedChange(it, key)
        })
        Text(text = title,
        fontSize = 14.sp,
        maxLines = 1,
            overflow = TextOverflow.Ellipsis
            )
    }
    
}




@ExperimentalMaterialApi
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchViewPreview(){
    IMdbAppTheme() {


        AdvancedSearchView(onBackPressed = {}, setByDropMenu = { dropMenuType, s ->  
            
        }, onCheckedChange = { b: Boolean, s: String ->



        }, isLoading = false, setTitle = {}, title = ""){

        }
    }
}



enum class AdvancedSearchType(val str : String){
    SearchAll(str = ""),
    Top1000 (str = "top_1000")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdvancedSearchTabIndicator(
    tabPositions: List<TabPosition>,
    type : AdvancedSearchType,
) {
    val transition = updateTransition(
        type,
        label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (AdvancedSearchType.SearchAll isTransitioningTo AdvancedSearchType.Top1000) {
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Indicator left"
    ) { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(
        transitionSpec = {
            if (AdvancedSearchType.SearchAll isTransitioningTo AdvancedSearchType.Top1000) {
                spring(stiffness = Spring.StiffnessMedium)
            } else {
                spring(stiffness = Spring.StiffnessVeryLow)
            }
        },
        label = "Indicator right"
    ) { page ->
        tabPositions[page.ordinal].right
    }
    val color by animateColorAsState(targetValue =
    if (MaterialTheme.colors.isLight) Purple700 else MaterialTheme.colors.secondary
    )


    Box(
        Modifier

            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(0.dp)
            .fillMaxSize()


            .border(
                BorderStroke(3.dp, color = color),
                RoundedCornerShape(50)
            )
    )
}
