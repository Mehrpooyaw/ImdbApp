package com.example.imdbapp.interactors.movie.util

fun convertIntRateToCountAndDecimal(imDbRate : Double) : Pair<Int,Double> {
    val count : Int
    val decimal : Double
    // Input will be a float between 0 to 10 .
    if (imDbRate != 0.0) { // Ex. : rate is 6.7 . diveBased will be 3.35
        val fiveBasedRate = imDbRate / 2
        count = (fiveBasedRate - fiveBasedRate % 1).toInt()
        decimal = fiveBasedRate % 1
        return Pair(count,decimal)
    }
    return Pair(0,0.0)
}




