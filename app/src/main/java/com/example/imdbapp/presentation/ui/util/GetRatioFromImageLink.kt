package com.example.imdbapp.presentation.ui.util

import kotlin.math.roundToInt

fun getRatioFromImageLink(
    link: String?
): Float? {
    return if (link != null) {
        val regex =
            Regex(pattern = "Ratio+[0-9]*\\.[0-9]+", options = setOf(RegexOption.IGNORE_CASE))
        val ratio: String? = regex.find(link)?.value
        ratio?.replace("Ratio", "")?.toFloatOrNull()
    }else{
        null
    }
}


enum class ImageQuality(val str : String){
    Original("original"),
    XSmall("150x220"),
    Small("210x313"),
    Medium("268x400")

}

fun changeImageQuality(url : String?,quality: String?,width : Float? = null) : String? {
    val pattern = "images/original"
    quality?.let {
        return url?.replace(pattern,"images/$quality") ?: url
    }
    width?.let {
        val height = width * 1.49
        return url?.replace(pattern, "images/${width.roundToInt()}x${height.roundToInt()}")
    }
    return url
}