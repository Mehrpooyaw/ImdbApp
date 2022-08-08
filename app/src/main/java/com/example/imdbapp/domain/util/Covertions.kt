package com.example.imdbapp.domain.util

fun convertAwardsStrToList(awards : String): List<String>{
    val returnedList = arrayListOf<String>()
    var newAwards : String = awards
    return if (awards.contains(" | ") || awards.contains(". ")){
        if(awards.contains(" | ")){
            newAwards = newAwards.replace(" | ","\n")
        }
        if(awards.contains(". ")){
            newAwards = newAwards.replace(". ","\n")
        }
        for (i in newAwards.lines()){
            returnedList.add(i)
        }
        returnedList
    }else {
        returnedList.add(awards)
        returnedList
    }
}