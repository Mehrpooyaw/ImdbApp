package com.example.imdbapp.presentation.ui.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.cache.models.ApiKey
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.BackButton
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.presentation.ui.web.WebView

enum class DarkAndLight(val str : String, val resId : Int , val color : Color){
    LightMode(str = "Light mode", resId = R.drawable.ic_round_wb_sunny_24, color = Color(
        89,
        93,
        97,
        255
    )
    ),
    DarkMode(str = "Dark mode", resId = R.drawable.icon_night, color = Color(0xFFC9C7BA)),
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsView(
    modifier : Modifier = Modifier,
    apiKeys : List<ApiKey>,
    toggleTheme : () -> Unit,
    addToApiKeys : (ApiKey) -> Unit,
    removeFromApiKeys : (String) -> Unit,
    setApiKey : (String) -> Unit,
    selectedApiKey : String,
    getSelectedApiKey : () -> Unit,
    onNavigateToLoginWebView : (String) -> Unit,
    onNavigateToRegisterWebView : (String) -> Unit,

) {
    val background by animateColorAsState(
        targetValue =
        if (MaterialTheme.colors.isLight)Color(229, 239, 255, 255) else Color(24, 40, 66, 255)
    )
    val halfTransparentBackground by animateColorAsState(
        targetValue =
        if (MaterialTheme.colors.isLight) MaterialTheme.colors.background else MaterialTheme.colors.background
    )

    val cardBackground by animateColorAsState(targetValue =
    if (MaterialTheme.colors.isLight) Color(251, 255, 208, 255) else Color(
        9,
        20,
        54,
        255
    )
    )

    var onRegister by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {


        Card(
            elevation = 20.dp,
            shape = RectangleShape,
            modifier = modifier.animateContentSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                background,
                                halfTransparentBackground
                            )
                        )
                    )
                    .padding(top = TOP_APP_PADDING)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LightAndDarkCard(toggleTheme = toggleTheme)

                Spacer(modifier = modifier.height(40.dp))

                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .heightIn(250.dp, 350.dp)
                        .animateContentSize()
                        .padding(15.dp,),
                    elevation = 7.dp,
                    backgroundColor = cardBackground,
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp,Color.Gray)
                ) {
                    AnimatedVisibility(visible = !onRegister) {
                        SettingsApiKeyCardContent(
                            addToApiKeys = addToApiKeys,
                            removeFromApiKeys = removeFromApiKeys,
                            setApiKey = setApiKey,
                            selectedApiKey = selectedApiKey,
                            getSelectedApiKey = getSelectedApiKey,
                            apiKeys = apiKeys,
                            onRegister = {
                                onRegister = true
                            },

                            )
                    }
                    AnimatedVisibility(visible = onRegister) {

                        Box(
                            modifier = modifier
                                .defaultMinSize(minHeight = 250.dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background.copy(0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            BackButton(
                                modifier = modifier
                                    .padding(20.dp)
                                    .align(Alignment.TopStart)
                            ) {
                                onRegister = false
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Spacer(modifier = modifier.height(30.dp))
                                Button(
                                    modifier = modifier.fillMaxWidth(0.8f),
                                    shape = RoundedCornerShape(35.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.primary,
                                        contentColor = Color.White
                                    ),
                                    onClick = {
                                        onNavigateToLoginWebView("https://imdb-api.com/Identity/Account/Login?ReturnUrl=%2FIdentity%2FAccount%2FManage")

                                    }) {
                                    Text("Login")
                                }
                                Spacer(modifier = modifier.height(10.dp))
                                Button(
                                    modifier = modifier.fillMaxWidth(0.8f),
                                    shape = RoundedCornerShape(35.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.primary,
                                        contentColor = Color.White
                                    ),
                                    onClick = {
                                        onNavigateToRegisterWebView("https://imdb-api.com/Identity/Account/Register")
                                    }) {
                                    Text("Register")
                                }

                            }
                        }
                    }


                }
                Spacer(modifier = modifier.height(7.dp))
            }

        }
        Divider(
            modifier = modifier.fillMaxWidth(),
            thickness = 3.dp
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(halfTransparentBackground)
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.Start
        ) {
            SettingsButton(title = "About us", onClick = {})
            Divider(
                thickness = 1.dp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            SettingsButton(title = "Contact us", onClick = {})
            Divider(
                thickness = 1.dp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = modifier.height(200.dp))

        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsButton(
    modifier : Modifier = Modifier,
    title : String,
    onClick : () -> Unit
) {
    TextButton(onClick = onClick,) {
        Text(textAlign = TextAlign.Start, text = title,modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp))
    }


}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsApiKeyCardContent(
    modifier : Modifier = Modifier,
    addToApiKeys : (ApiKey) -> Unit,
    removeFromApiKeys : (String) -> Unit,
    setApiKey : (String) -> Unit,
    selectedApiKey : String,
    getSelectedApiKey : () -> Unit,
    apiKeys : List<ApiKey>,
    onRegister : () -> Unit,

    ){
    val keys = apiKeys
    var textValue by remember { mutableStateOf("")}
    val state = rememberLazyListState()

    var selected by remember {
        mutableStateOf(selectedApiKey)
    }
    Log.d("appDebug","SettingsView : apiKeys : $apiKeys")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background.copy(0.5f))
            .padding(top = 15.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Api keys",
                modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                style = MaterialTheme.typography.h6
            )
            TextButton(onClick = onRegister, modifier = modifier.padding(end = 15.dp)) {
                Text(text = "Login/Register", color = MaterialTheme.colors.secondary)
            }

        }
        LazyColumn(modifier = modifier
            .padding(horizontal = 10.dp),
            state = state) {
            item {
                OutlinedTextField(modifier = modifier.fillMaxWidth(),
                    value = textValue,
                    onValueChange = {
                        textValue = it
                    },
                    label = {
                        Text(text = "Add a new apiKey")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            if (textValue.isNotEmpty()) {

                                addToApiKeys(ApiKey(stringId = textValue))
                                Log.d(
                                    "appDebug",
                                    "we clicked add button , now apiKeys are : $apiKeys"
                                )
                            }
                        }) {


                            Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
                        }
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.secondaryVariant,
                        focusedLabelColor = MaterialTheme.colors.secondaryVariant
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        if (textValue.isNotEmpty()) {
                            addToApiKeys(ApiKey(stringId = textValue))
                        }
                    }),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(20))
            }
            itemsIndexed(keys) { index , item   ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = item.stringId == selected, onClick = {
                            if (item.stringId != selected) {
                                setApiKey(item.stringId)
                                selected = item.stringId


                            }
                        })
                        Text(
                            text = "${index + 1}. ${item.name ?: item.stringId}",
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                    IconButton(onClick = {
                        removeFromApiKeys(item.stringId)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primary.copy(alpha = 0.7f)
                        )
                    }
                }
            }
        }
        Spacer(modifier = modifier.height(18.dp))

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LightAndDarkCard(
    modifier : Modifier = Modifier,
    toggleTheme: () -> Unit
){
    val isThemeOnLight = MaterialTheme.colors.isLight
    val theme = remember { mutableStateOf( if (isThemeOnLight) DarkAndLight.LightMode else DarkAndLight.DarkMode)}
    var isDark = remember {mutableStateOf(!isThemeOnLight)}
    val tabBackgroundColor by animateColorAsState(targetValue =
    if (theme.value == DarkAndLight.LightMode) Color(251, 255, 208, 255) else Color(
        9,
        20,
        54,
        255
    )
    )

    Card(
        modifier = modifier
            .fillMaxWidth(0.85f)
            .height(90.dp),
        shape = RoundedCornerShape(40),
        elevation = 25.dp
    ){
        TabRow(
            divider = {

            },
            modifier = modifier
                .fillMaxSize(),
            backgroundColor = tabBackgroundColor,
            selectedTabIndex = if (isDark.value)DarkAndLight.DarkMode.ordinal else DarkAndLight.LightMode.ordinal,
            indicator = { tabPositions ->
                ThemeTabIndicator(
                    tabPositions = tabPositions, darkAndLight = theme.value)
            }) {
            DarkAndLight.values().forEach {
                Tab(
                    modifier = modifier.clip(RoundedCornerShape(35)),
                    selected = theme.value == it,
                    onClick = {
                        if (theme.value != it) {
                            toggleTheme()
                            theme.value = it
                            isDark.value = !isDark.value

                        }
                    },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = it.resId),
                            contentDescription = "",
                            modifier = modifier.size(20.dp)
                        )
                        Spacer(modifier = modifier.width(6.dp))
                        Text(
                            text = it.str,
                            color = it.color,
                            fontFamily = FontFamily.SansSerif
                        )
                        
                    }

                }
            }


        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ThemeTabIndicator(
    tabPositions: List<TabPosition>,
    darkAndLight: DarkAndLight,
) {
    val transition = updateTransition(
        darkAndLight,
        label = "Tab indicator"
    )
    val indicatorLeft by transition.animateDp(
        transitionSpec = {
            if (DarkAndLight.LightMode isTransitioningTo DarkAndLight.DarkMode) {
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
            if (DarkAndLight.LightMode isTransitioningTo DarkAndLight.DarkMode) {
                 spring(stiffness = Spring.StiffnessMedium)
            } else {
               spring(stiffness = Spring.StiffnessVeryLow)
            }
        },
        label = "Indicator right"
    ) { page ->
        tabPositions[page.ordinal].right
    }
    val color by transition.animateColor(
        label = "Border color"
    ) { page ->
        if (page == DarkAndLight.LightMode)  Color(
            255,
            213,
            0,
            255
        ) else Color(0xff34495E)
    }
    Box(
        Modifier

            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(5.dp)
            .fillMaxSize()


            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(40)
            )
    )
}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsViewPreview(){
    SettingsView(apiKeys = remember { listOf(
        ApiKey( stringId = "Default one", id = 111),
        ApiKey( stringId = "Default two", id = 111),
        ApiKey( stringId = "Default Three", id = 111),
        ApiKey( stringId = "Default Four", id = 111),


    )}, toggleTheme = {}, addToApiKeys = {}, removeFromApiKeys = {}, setApiKey = {}, selectedApiKey = "", onNavigateToRegisterWebView = {}, onNavigateToLoginWebView = {}
    , getSelectedApiKey = {})
}