package com.example.imdbapp.presentation.ui.movie.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imdbapp.R
import com.example.imdbapp.network.model.movie.Other
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.util.FakeData


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OtherFullcastListItemView(others : List<Other>
                              , modifier : Modifier = Modifier){
    var halfList = emptyList<Other>()
    var remainList = emptyList<Other>()
    var scroll = rememberScrollState()
    if (others.size %2 ==0){
        halfList = others.subList(0,others.size/2)
        remainList = others.subList(others.size/2,others.size )
    } else{
        halfList = others.subList(0,(others.size+1)/2)
        remainList = others.subList((others.size+1)/2,others.size)
    }
    Scaffold(
        backgroundColor = Color.Black
    ) {


        Column(modifier = modifier.verticalScroll(scroll)) {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "FULLCAST", color = Color.LightGray, fontSize = 18.sp,)
                Text(
                    text = "OTHERS",
                    color = MaterialTheme.colors.primary,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(
                thickness = 1.dp,
                color = NeonBlue,
                modifier = modifier.padding(bottom = 8.dp)
            )

            Row() {
                Column(modifier = modifier.fillMaxWidth(0.5f)) {
                    for (i in halfList) {
                        OtherItemView(other = i)
                    }
                }
                Column(modifier = modifier.fillMaxWidth(1f)) {
                    for (i in remainList) {
                        OtherItemView(other = i)
                    }
                }
            }
            Divider(modifier = modifier.padding(top = 10.dp, bottom = 100.dp), thickness = 1.dp, color = NeonBlue)
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OtherItemView(other : Other, modifier : Modifier = Modifier){
    val expanded = remember{mutableStateOf(false)}
    val isMoreThan4 = other.items != null && other.items.size > 4
    Card(
        modifier = modifier
            .padding(horizontal = 5.dp, vertical = 10.dp)
            .fillMaxSize()
            ,
        backgroundColor = NeonBlue,
        contentColor = AlmostSilver,
        border = BorderStroke(2.dp,NeonBlue),
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp)
    ){
        Column() {
            Card(
                backgroundColor = NeonBlue,
                contentColor = Color.Yellow,
                modifier = modifier.fillMaxWidth(),
                shape = RectangleShape
            ) {
                other.job?.let { Text(it, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = modifier.padding(horizontal = 8.dp, vertical = 5.dp)) }
            }
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(AlmostBlack.copy(alpha = 0.8f))
                    .fillMaxWidth()
                    .padding(5.dp)

            ){

                if (!expanded.value){
                    if (other.items!!.size > 4) {
                        for (i in other.items.subList(0, 4)) {
                            i.name?.let { NameAndMinusSign(name = it) }
                        }
                    }
                    else{
                        for (i in other.items) {
                            i.name?.let { NameAndMinusSign(name = it) }
                        }
                    }
                }else {
                    for (i in other.items!!) {
                        i.name?.let { NameAndMinusSign(name = it) }
                    }
                }
            }
            if (other.items != null && other.items.size >4) {

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = NeonBlue,
                        contentColor = Color.Yellow,
                    ),
                    shape = RectangleShape,
                    onClick = { expanded.value = !expanded.value },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        painterResource(id = if (expanded.value) R.drawable.ic_baseline_keyboard_arrow_up_24 else R.drawable.ic_baseline_keyboard_arrow_down_24),
                        contentDescription = ""
                    )

                }
            }
        }
    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NameAndMinusSign(name : String){
    Text("- $name",Modifier.padding(start = 4.dp, bottom = 2.dp))
}



@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OtherListItemViewPreview(){
    val fakeData = FakeData
    IMdbAppTheme() {
        Scaffold() {
            fakeData.SampleMovie.fullCast?.others?.let { it1 -> OtherFullcastListItemView(others = it1) }
        }
    }
}