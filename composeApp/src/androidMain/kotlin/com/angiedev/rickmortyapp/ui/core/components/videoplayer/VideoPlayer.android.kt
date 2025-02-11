package com.angiedev.rickmortyapp.ui.core.components.videoplayer

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun VideoPlayer(modifier: Modifier, videoUrl: String) {
    if(videoUrl.isNotBlank()){
        AndroidView(modifier = modifier, factory = { context ->
            val videoView = VideoView(context)
            videoView.apply {
                setVideoPath(videoUrl)
                setMediaController(MediaController(context).apply {
                    setAnchorView(videoView)
                })
                start()
            }
        })
    }

}