package com.angiedev.rickmortyapp.ui.core.components.videoplayer

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
actual fun VideoPlayer(modifier: Modifier, videoUrl: String) {
    if (videoUrl.isNotBlank()) {
        YoutubeVideoPlayer(videoUrl, modifier).takeIf { !videoUrl.contains("https") }
            ?: DefaultVideoPlayer(modifier, videoUrl)
    }
}

@Composable
fun DefaultVideoPlayer(modifier: Modifier, videoUrl: String) {
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

@Composable
fun YoutubeVideoPlayer(videoId: String, modifier: Modifier) {
    val context = LocalContext.current

    val youtubePlayer = remember {
        YouTubePlayerView(context).apply {
            enableAutomaticInitialization = false
            initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }

    AndroidView({ youtubePlayer }, modifier = modifier)
}


