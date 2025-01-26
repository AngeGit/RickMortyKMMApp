package com.angiedev.rickmortyapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.angiedev.rickmortyapp.domain.model.CharacterModel
import com.angiedev.rickmortyapp.ui.home.homebottombar.tabs.characters.CharacterOfTheDayComponent

@Preview
@Composable
fun Preview(){
    CharacterOfTheDayComponent(CharacterModel(
        id=3,
        image="",
        isAlive=true,
        name="Rick"
    ))

}