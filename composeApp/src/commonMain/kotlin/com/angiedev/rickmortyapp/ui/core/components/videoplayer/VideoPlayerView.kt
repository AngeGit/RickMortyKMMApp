package com.angiedev.rickmortyapp.ui.core.components.videoplayer


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import rickmortykmmapp.composeapp.generated.resources.Res
import rickmortykmmapp.composeapp.generated.resources.portal

@Composable
fun VideoPlayerView(
    url: String,
    onVideoPlayerClose: () -> Unit
) {
    AnimatedVisibility(
       visible = url.isNotBlank(),
        enter =  expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = shrinkVertically() + fadeOut()

    ){

        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .border(5.dp, Color.Green, shape = CardDefaults.elevatedShape)
        ) {
            Box(
                modifier = Modifier.background(Color.Black)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                        .height(200.dp)
                        .clip(CardDefaults.elevatedShape),
                    contentAlignment = Alignment.Center,
                ) {
                    VideoPlayer(
                        Modifier.fillMaxWidth()
                            .height(200.dp),
                        url
                    )
                }
                Row {
                    Spacer(Modifier.weight(1F))
                    Image(
                        painter = painterResource(Res.drawable.portal),
                        contentDescription = "Close",
                        modifier = Modifier.size(40.dp)
                            .padding(8.dp)
                            .clickable {
                                onVideoPlayerClose()
                            }
                    )
                }
            }
        }
    }
}