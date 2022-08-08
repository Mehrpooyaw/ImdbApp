package com.example.imdbapp.presentation.ui.movie.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import android.annotation.SuppressLint 
import androidx.compose.runtime.Composable

import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imdbapp.network.model.movie.BoxOffice
import com.example.imdbapp.network.model.movie.Company
import com.example.imdbapp.presentation.theme.*
import com.example.imdbapp.presentation.ui.components.CustomTitleText
import com.example.imdbapp.presentation.ui.components.VerticalKeyValue
import com.example.imdbapp.util.FakeData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieCompaniesAndBoxOffice(
    modifier : Modifier = Modifier,
    boxOffice: BoxOffice?,
    companies : List<Company>?
){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp, horizontal = 5.dp)) {

        boxOffice?.let { boxOffice ->
            val isBoxOfficeActive = (boxOffice.budget != null && boxOffice.budget != "") || (boxOffice.cumulativeWorldwideGross != null && boxOffice.cumulativeWorldwideGross != "")
                    || (boxOffice.grossUSA != null && boxOffice.grossUSA != "") || (boxOffice.openingWeekendUSA != null && boxOffice.openingWeekendUSA != "")
            if (isBoxOfficeActive){
                MovieBoxOffice(boxOffice = boxOffice)

            }
        }

        companies?.let { companies ->
            MovieCompaniesView(companies = companies)
        }




    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieBoxOffice(modifier : Modifier = Modifier,
    boxOffice: BoxOffice
   ){
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp),) {

        CustomTitleText(text = "Box Office")
        boxOffice.budget?.let { budget ->
            if (budget != ""){
                VerticalKeyValue(key = "Budget", value = boxOffice.budget)
            }
        }
        boxOffice.openingWeekendUSA?.let { owUsa ->
            if (owUsa != ""){
                VerticalKeyValue(key = "Opening Weekend USA", value = owUsa)
            }
        }
        boxOffice.grossUSA?.let { gross ->
            if (gross != ""){
                VerticalKeyValue(key = "Gross USA", value = gross)
            }
        }
        boxOffice.cumulativeWorldwideGross?.let { cumulative ->
            if (cumulative != "") {
                VerticalKeyValue(key = "Cumulative World wide gross", value = cumulative)
            }
        }


    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieCompaniesView(
    modifier : Modifier = Modifier,
    companies: List<Company>
){

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .padding(end = 10.dp),
        horizontalAlignment = Alignment.End
        ) {
        Box(
            modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)){
            CustomTitleText(text = "Companies")
        }

        companies.forEach { company ->
            val isActive = company.id != null && company.id != ""
            company.name?.let {
                Button(
                    modifier = modifier.fillMaxWidth(4/5f),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                              if (isActive){



                              }
                        //TODO : Later
                    },
                    enabled = isActive,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor =
                        if (MaterialTheme.colors.isLight)
                            DarkNeonPink
                        else
                            Color(0xFFF890F8)
                        ,
                    ),
                    contentPadding = PaddingValues(0.dp),
                    border = BorderStroke(1.dp, Brush.horizontalGradient(
                        listOf(
                            Color.Gray.copy(0.11f),
                            Color.Gray.copy(0.09f),
                            Color.Transparent,
                            Color.Transparent,
                        )

                    ))
                ){
                    Row(
                        modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    if (MaterialTheme.colors.isLight)
                                        listOf(
                                            Color.White.copy(0.11f),
                                            Color.LightGray.copy(0.06f),
                                            Color.LightGray.copy(0.05f),
                                            DarkNeonPink.copy(0.1f),
                                            DarkNeonPink.copy(0.3f),
                                        )
                                    else
                                        listOf(
                                            Color.Gray.copy(0.11f),
                                            Color.Gray.copy(0.09f),
                                            Color.Gray.copy(0.05f),
                                            Color.Magenta.copy(alpha = 0.1f),
                                            Color.Magenta.copy(alpha = 0.3f),
                                        )
                                )
                            )
                            .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                        Text(company.name, fontFamily = FontFamily.SansSerif)
                        if(company.id != null && company.id != ""){
                            Icon(Icons.Rounded.KeyboardArrowRight,"", tint = if (MaterialTheme.colors.isLight) DarkNeonPink else Color(0xFFF890F8))
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
fun MovieCompAndBoxOfficePreview(){
    val movie = FakeData.SampleMovie
    IMdbAppTheme() {
        Scaffold() {
            MovieCompaniesAndBoxOffice(boxOffice = movie.boxOffice, companies = movie.companyList)

        }
    }
}