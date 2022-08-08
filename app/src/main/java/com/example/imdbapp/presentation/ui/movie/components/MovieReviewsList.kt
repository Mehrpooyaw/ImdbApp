package com.example.imdbapp.presentation.ui.movie.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.domain.model.Reviews
import com.example.imdbapp.network.model.reviews.Review
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.util.TOP_APP_PADDING
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieReviewsList(
    modifier : Modifier = Modifier,
    reviews : Reviews?,

){
    Log.d("appDebug/reviews","the reviews is : ${reviews?.imDbId}}")
    Log.d("appDebug/reviews","the reviews is : ${reviews?.errorMessage}}")

    val scrollState = rememberScrollState()
    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)
        .padding(vertical = 10.dp).padding(top = TOP_APP_PADDING)
        ) {
        CustomTitleText(text = "${reviews?.title?:""} Reviews ...",modifier = modifier.padding(start = 10.dp))
        reviews?.items?.forEach { review ->
            ReviewCard(review = review)

        }

    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReviewCard(
    modifier : Modifier = Modifier,
    review : Review
){
    val isExpanded = remember {mutableStateOf(false)}
    Card(modifier = modifier
        .padding(horizontal = 10.dp, vertical = 10.dp)
        .fillMaxWidth()
        .animateContentSize(),
        shape = RoundedCornerShape(10.dp)

    ){
        Column(modifier = modifier
            .fillMaxWidth()

            ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(color = 0xFF1F1F29), Color.Transparent)
                        )
                    )
                    .padding(horizontal = 17.dp)
                    .padding(top = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                review.username?.let { username ->
                    if (username != "") {
                        Text(text = username, fontFamily = FontFamily.SansSerif, color = NeonPink, fontWeight = FontWeight.SemiBold)
                    }
                }
                review.rate?.let { rate ->
                    if (rate != "") {
                        ImdbRateStars(imDbRate = rate.toDouble())
                    }
                }
            }
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)) {
                review.helpful?.let { helpful ->
                    Box(modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, bottom = 3.dp), contentAlignment = Alignment.CenterEnd){
                        Text(helpful, color = NeonGreen)
                    }

                }
                Divider(modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 5.dp),
                    thickness = 1.dp, color = Color.Cyan.copy(alpha = 0.5f))

                review.title?.let { title ->
                    if (title != "") {
                        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.TopStart) {
                            Text(
                                title,
                                color = Color.LightGray,
                                modifier = modifier.padding(bottom = 5.dp),
                                lineHeight = 16.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Thin,
                            )
                        }
                    }
                    review.content?.let { content ->
                        if (content != "") {

                            if (isExpanded.value) {
                                Text(
                                    text = content,
                                    textAlign = TextAlign.Justify,
                                    style = MaterialTheme.typography.body1

                                    )
                            } else {
                                Text(
                                    text = content,
                                    textAlign = TextAlign.Justify,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.body1
                                )


                            }
                        }
                    }
                }
            }
            review.content?.let { content2 ->
                if (content2 != "" && content2.length > 100) {
                    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter) {


                        Button(
                            onClick = { isExpanded.value = !isExpanded.value }, colors = ButtonDefaults.buttonColors(
                                backgroundColor = NeonBlue,
                                contentColor = AlmostSilver
                            ),
                            shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
                        ) {
                            Row() {
                                Text(if (isExpanded.value) "Less " else "More ")
                                Icon(
                                    imageVector = if (isExpanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }


        }

    }
}


@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieReviewsListPreview(){
    IMdbAppTheme() {
        Scaffold() {
            MovieReviewsList(reviews = FakeData.sampleReviews)
        }
    }
}