package com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import coil3.compose.AsyncImage
import com.angiedev.rickmortyapp.domain.model.CharacterModel

@Composable
fun CharactersGridList(characters: LazyPagingItems<CharacterModel>, state: State<CharacterState>) {

    LazyVerticalGrid(
        modifier= Modifier.fillMaxSize().padding(16.dp),
        columns=GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ){
        when {
            characters.loadState.refresh is LoadState.Loading && characters.itemCount==0 -> {
                //Initial loading
                item (span = { GridItemSpan(2) }){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(Modifier.size(64.dp), color = Color.Red)
                    }
                }
            }
            characters.loadState.refresh is LoadState.NotLoading && characters.itemCount==0 -> {
                //API Empty list
                item (span = { GridItemSpan(2) }){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                      Text("No hay personajes")
                    }
                }
            }
            else -> {
                item (span = { GridItemSpan(2) }){
                    Column {
                        Text(
                            text = "Characters",
                            color = Color.Green.copy(alpha = 0.8f),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(8.dp).fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        CharacterOfTheDayComponent(state.value.characterOfTheDay)
                    }

                }

                items(characters.itemCount) { index ->
                    characters[index]?.let {
                        CharacterItemList(it)
                    }
                }

                if (characters.loadState.append is LoadState.Loading) {
                    item (span = { GridItemSpan(2) }){
                        Box(
                            modifier = Modifier.fillMaxWidth().height(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(Modifier.size(64.dp), color = Color.Green)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItemList(character: CharacterModel) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(
                2.dp,
                Color.Green,
                shape = RoundedCornerShape(0,24,0,24)
            ).fillMaxSize()
            .clickable {
                //go to Detail
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = "Character:${character.name}",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.verticalGradient(
                     listOf(
                         Color.Black.copy(alpha = 0f),
                         Color.Black.copy(alpha = 0.6f),
                         Color.Black.copy(alpha = 0.9f)
                     )
                    )
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = character.name,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
