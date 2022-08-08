package com.example.imdbapp.presentation.ui.youtube

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrailerPlayer(
    modifier : Modifier = Modifier,
    url : String,

){
    val playWhenReady = rememberSaveable {
        mutableStateOf(false)
    }
    val link = "https://rr2---sn-4g5lzner.googlevideo.com/videoplayback?expire=1642705661&ei=nV7pYdG2GYzs1wLGxreoDg&ip=144.76.178.200&id=o-AFMbaXJfggSii7jNuUR5VJf-nXurDAr90S5elCWed2wG&itag=22&source=youtube&requiressl=yes&mh=W_&mm=31%2C26&mn=sn-4g5lzner%2Csn-f5f7kn7e&ms=au%2Conr&mv=u&mvi=2&pl=24&vprv=1&mime=video%2Fmp4&cnr=14&ratebypass=yes&dur=148.352&lmt=1550189110826143&mt=1642683248&fvip=2&fexp=24001373%2C24007246&c=ANDROID&txp=5535432&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Ccnr%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIhAJ3J3I7bkmpNqgghg4dJHmLjavQy1l4re_oaKzkub-prAiBctBVwfLijQHqrMVK5c7a9nGKrLU84MQLuvE0BNS_b4w%3D%3D&lsparams=mh%2Cmm%2Cmn%2Cms%2Cmv%2Cmvi%2Cpl&lsig=AG3C_xAwRgIhAMB-_2SsDUnJy03k2VbrwC3pOqjrHTsegcFufcUZ9qcwAiEAhju9hhRsnAR5ypKusJN_HoRbPv-XsdqMX-jZn1Q2Cqo%3D&title=Leon+-+The+Professional+Trailer"
    MyPlayer(url = link, playWhenReady = playWhenReady.value)

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyPlayer(
    url : String,
    playWhenReady : Boolean
) {
    val context = LocalContext.current
    val player = SimpleExoPlayer.Builder(context).build()
    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri(url)

    player.setMediaItem(mediaItem)
    playerView.player = player
    LaunchedEffect(player) {
        player.prepare()
        player.playWhenReady = playWhenReady

    }
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = {
        playerView
    })
}




@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrailerPlayerPreview(){
    TrailerPlayer(
        url = ""
    )
}