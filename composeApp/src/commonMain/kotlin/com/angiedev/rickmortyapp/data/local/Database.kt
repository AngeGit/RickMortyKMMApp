package com.angiedev.rickmortyapp.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.angiedev.rickmortyapp.data.local.dao.UserPreferencesDAO
import com.angiedev.rickmortyapp.data.local.entities.CharacterOfTheDayEntity

const val DATABASE_NAME = "rickmorty_app.db"

expect object RickMortyCTor: RoomDatabaseConstructor<RickMortyDatabase>

@Database(entities = [CharacterOfTheDayEntity::class], version = 1)
@ConstructedBy(RickMortyCTor::class)
abstract class RickMortyDatabase:RoomDatabase() { //aquí van los dao --- DATA ACCESS OBJECT
    abstract fun userPreferencesDao(): UserPreferencesDAO
    //abstract fun characterDao(): CharacterDao
}
