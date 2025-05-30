package com.angiedev.rickmortyapp.ui.core.components.videoplayer


import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.angiedev.rickmortyapp.ui.core.resources.BackgroundSecondaryColor
import com.angiedev.rickmortyapp.ui.core.resources.DefaultTextColor
import com.angiedev.rickmortyapp.ui.core.resources.PlaceholderBorderColor
import com.angiedev.rickmortyapp.ui.core.resources.PlaceholderShadowColor
import com.angiedev.rickmortyapp.ui.core.resources.Strings
import org.jetbrains.compose.resources.painterResource
import rickmortykmmapp.composeapp.generated.resources.Res
import rickmortykmmapp.composeapp.generated.resources.placeholder
import rickmortykmmapp.composeapp.generated.resources.portal

@Composable
fun VideoPlayerView(
    url: String,
    onVideoPlayerClose: () -> Unit
) = AnimatedContent(targetState = url.isNotBlank()) { condition ->
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .padding(16.dp)
                .border(5.dp, PlaceholderBorderColor, shape = CardDefaults.elevatedShape),
            colors = CardDefaults.elevatedCardColors()
                .copy(containerColor = BackgroundSecondaryColor).takeIf { !condition }
                ?: CardDefaults.elevatedCardColors()
        ) {
            if (condition) {
                VideoPlayer(url, onVideoPlayerClose)
            } else {
                VideoPlaceHolder()
            }
        }
    }
}

@Composable
private fun VideoPlaceHolder() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(Res.drawable.placeholder),
            contentDescription = Strings.VIDEO_PLACEHOLDER_DESCRIPTION,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.height(300.dp).align(Alignment.Center).padding(top = 16.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(PlaceholderShadowColor, Color.Transparent)
                    )
                )
                .padding(16.dp),
            color = DefaultTextColor,
            style = TextStyle.Default.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            text = Strings.VIDEO_PLACEHOLDER_TEXT
        )
    }
}

@Composable
private fun VideoPlayer(
    url: String,
    onVideoPlayerClose: () -> Unit
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
                contentDescription = Strings.CLOSE_BUTTON,
                modifier = Modifier.size(40.dp)
                    .padding(8.dp)
                    .clickable {
                        onVideoPlayerClose()
                    }
            )
        }
    }
}