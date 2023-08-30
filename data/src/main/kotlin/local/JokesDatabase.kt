package local

import androidx.room.Database
import androidx.room.RoomDatabase
import local.dao.JokesDao
import local.entities.JokeLocalDto

@Database(entities = [JokeLocalDto::class], version = 1)
abstract class JokesDatabase : RoomDatabase() {
    abstract fun jokesDao(): JokesDao
}