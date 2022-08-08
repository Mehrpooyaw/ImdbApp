package com.example.imdbapp.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.R
import com.example.imdbapp.presentation.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryRowCircleList(
    modifier : Modifier = Modifier,
    rowModifier : Modifier = Modifier,
    onNavigateToEachCategory : (HomeCategoryItems,HomeCategoryItems?) -> Unit,
    isCollapsed : Boolean
){
    val selected = remember { mutableStateOf<HomeCategoryItems?>(null)}
    Box(modifier = rowModifier.fillMaxWidth()) {
        LazyRow(
            modifier = modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            items(HomeCategoryItems.values()) {

                HomeCategoryCircleItem(
                    id = it,
                    onClick = { item ->
                        if (isCollapsed) {
                            selected.value = it
                            onNavigateToEachCategory(item,selected.value)
                        }else{
                            if (selected.value != item) {
                                onNavigateToEachCategory(item,selected.value)
                                selected.value = it
                            }else{
                                onNavigateToEachCategory(item,selected.value)
                                selected.value = null

                            }
                        }
                    },
                    isSelected = it == selected.value

                )
            }

        }
        Spacer(modifier =
        modifier
            .align(Alignment.CenterEnd)
            .height(100.dp)
            .width(8.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color.Transparent,
                        MaterialTheme.colors.background,
                    )
                )
            ))
        Spacer(modifier =
        modifier
            .align(Alignment.CenterStart)
            .height(100.dp)
            .width(8.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(

                        MaterialTheme.colors.background,
                        Color.Transparent

                    )
                )
            ))

    }

}




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryCircleItem(
    modifier : Modifier = Modifier,
    id : HomeCategoryItems,
    onClick : (HomeCategoryItems) -> Unit,
    isSelected : Boolean
    ){
    val buttonOffset = animateFloatAsState(targetValue =
    if (isSelected) 30f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy, stiffness = Spring.StiffnessMediumLow
        )
    )
    Column(
        modifier
            .padding(bottom = 15.dp)
            .graphicsLayer {
                translationY = buttonOffset.value
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier

                .padding(horizontal = 5.dp)
                .padding(top = 10.dp)
                .size(90.dp),
            onClick = {
                onClick(id)
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 20.dp,
                pressedElevation = 0.dp
            ),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.background
            ),


            ) {
            Box(
                modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(
                                id.gradient[0].copy(alpha = 0.8f),
                                id.gradient[0].copy(alpha = 0.6f),
                                id.gradient[0].copy(alpha = 0.45f),
                                id.gradient[1].copy(alpha = 0.45f),
                                id.gradient[1].copy(alpha = 0.6f),
                                id.gradient[1].copy(alpha = 0.8f),

                                )

                        )

                    ), Alignment.Center
            ) {
                id.icon?.let {
                    Image(
                        painter = painterResource(id = id.icon),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(0.7f)
                    )

                } ?: run {
                    Text("See more", color = Color.White)
                }
            }

        }
        AnimatedVisibility(visible = isSelected) {


            Icon(
                imageVector = Icons.Rounded.ArrowDropDown,
                contentDescription = "",
                tint = id.gradient[1],
                modifier = modifier.size(28.dp)
            )
        }
    }


}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeCategoryRowPreview(){
    IMdbAppTheme() {
        Scaffold() {
            HomeCategoryRowCircleList(isCollapsed = true, onNavigateToEachCategory = {item , item2 ->

            })
        }
    }
}

enum class HomeCategoryItems(val icon: Int?,val gradient : List<Color>) {
    Animation(R.drawable.icon_category_animation,VodkaGradient),
    Action(R.drawable.icon_category_action, CyanGradient),
    Comedy(R.drawable.icon_category_comedy, listOf(NeonBlue, NeonBlue.copy(blue = 0.9f))),
    Horror(R.drawable.icon_category_horror, TGreenGradient),
    Scifi(R.drawable.icon_category_scifi, BabyBlueGradient),

}
