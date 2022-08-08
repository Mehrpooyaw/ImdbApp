package com.example.imdbapp.presentation.ui.person.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.imdbapp.R
import com.example.imdbapp.domain.model.Person
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.HorizontalKeyValue
import com.example.imdbapp.presentation.ui.util.getRatioFromImageLink
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonImageAndBaseDetails(person : Person,
                              modifier : Modifier = Modifier,
                              onBack : () -> Unit,
                              isFavorite : Boolean,
                              onFavoriteButtonClicked : (Person) -> Unit

                              ){
    val imageRatio : Float? = getRatioFromImageLink(person.image)
    val isExpanded = remember {mutableStateOf(false)}
    Column(modifier = modifier
        .fillMaxWidth()
        ,) {

        Row(modifier = modifier.fillMaxWidth().background(
            Brush.verticalGradient(
                colors =
                if (MaterialTheme.colors.isLight)
                    listOf(
                        Color.Transparent,MaterialTheme.colors.background
                    )
                else
                listOf(
                    Color.Black, Color.Black, Color.Black, AlmostBlack
                )
            )

        ).padding(top = 26.dp)) {
            Column(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .height(250.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Rounded.ArrowBack,
                        "",
                        modifier = modifier.size(30.dp),
                        tint =
                            if (MaterialTheme.colors.isLight)
                                Color.DarkGray
                            else
                                AlmostSilver
                    )
                }
                IconButton(onClick = {
                    onFavoriteButtonClicked(person)
                }) {
                    Icon(
                        Icons.Rounded.Star,
                        "",
                        modifier = modifier.size(30.dp),
                        tint = if (isFavorite) AlmostYellow else
                                if (MaterialTheme.colors.isLight)
                                    Color.DarkGray
                                else
                                    AlmostSilver
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Rounded.Share,
                        "",
                        modifier = modifier.size(30.dp),
                        tint =
                        if (MaterialTheme.colors.isLight)
                            Color.DarkGray
                        else
                            AlmostSilver
                    )
                }
            }
            Card(
                elevation = 20.dp,
                shape = RoundedCornerShape(bottomStartPercent = 15, topStartPercent = 8),
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(imageRatio ?: 0.7f),
                border = BorderStroke(1.dp,Brush.linearGradient(
                    colors = if (MaterialTheme.colors.isLight)
                        GlassyGradient
                    else
                        AlmostBlackToDarkGrayGradient
                    ))
            ) {
                Box(modifier = modifier.fillMaxSize()) {
                    Image(
                        painter = rememberImagePainter(
                            data = person.image
                        ),
                        contentDescription = "person_image",
                        modifier = modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Spacer(
                modifier = modifier
                    .padding(horizontal = 10.dp)
                    .size(width = 5.dp, height = 50.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(
                        Brush.verticalGradient(
                            tileMode = TileMode.Clamp,
                            colors = listOf(NeonPink, NeonGreen)
                        )
                    )
            )
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalAlignment = Alignment.Start
            ) {
                person.name?.let {
                    Text(
                        text = it.uppercase(),
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1
                    )
                }

                person.role?.let {
                    Text(
                        text = it,
                        modifier = modifier.padding(vertical = 5.dp),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NeonGreen
                    )
                }
            }
        }
        person.awards?.let {
            if (it != "") {
                Row(
                    modifier = modifier.fillMaxWidth().padding(vertical =
                    5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.trophy_icon),
                        modifier = modifier
                            .padding(horizontal = 5.dp)
                            .size(40.dp),
                        contentDescription = ""
                    )
                    Text(
                        person.awards.replace(". ", ".\n"),
                        color = AlmostYellow,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
        person.summary?.let {
            if (it != "") {

                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    if (isExpanded.value) {
                        Text(
                            it,
                            modifier = modifier.padding(horizontal = 5.dp),
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.body1

                        )
                    } else {
                        Text(
                            it,
                            modifier = modifier.padding(horizontal = 5.dp),
                            textAlign = TextAlign.Justify,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.body1

                        )
                    }
                    Box(
                        modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp), Alignment.Center
                    ) {
                        Button(
                            onClick = { isExpanded.value = !isExpanded.value },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primary,
                                contentColor = Color.White
                            ),
                            modifier = modifier.height(26.dp),
                            contentPadding = PaddingValues(0.dp)

                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = modifier
                                    .fillMaxHeight()
                                    .background(
                                        Brush.linearGradient(listOf(NeonBlue, NeonGreen))
                                    )
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(if (isExpanded.value) "Less" else "More",)

                                Icon(
                                    if (isExpanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = "",
                                    modifier = modifier.size(20.dp)
                                )
                            }
                        }
                    }

                }
            }
        }
        Column(modifier = modifier.fillMaxWidth().padding(5.dp)) {


            person.id?.let {
                if (it != "") {
                    HorizontalKeyValue(key = "ImDb Id", value = it, tint = Color.Cyan) }}
            person.birthDate?.let {
                if (it != "") {

                    HorizontalKeyValue(
                    key = "Birth Date",
                    value = it,
                    tint = Color.Cyan
                )
            }}
            person.deathDate?.let {
                if (it != "") {

                    HorizontalKeyValue(
                    key = "Death Date",
                    value = it,
                    tint = Color.Cyan
                )
            }}
            person.height?.let {
                if (it != "") {
                    HorizontalKeyValue(key = "Height", value = it, tint = Color.Cyan) }
        }}
        Divider(
            modifier = modifier.padding(10.dp).fillMaxWidth(), thickness = 1.dp,
            color = AlmostSilver
        )
    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonImageAndBaseDetailsPreview(){
    val samplePerson = FakeData.SamplePerson
    IMdbAppTheme() {
        Scaffold() {
            PersonImageAndBaseDetails(person = samplePerson, isFavorite = true, onFavoriteButtonClicked = {}, onBack = {})
        }
    }
}