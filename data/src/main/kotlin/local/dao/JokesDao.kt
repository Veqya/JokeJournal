package local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import local.entities.JokeLocalDto

@Dao
interface JokesDao {
    @Query("SELECT * FROM jokes WHERE id = :id")
    suspend fun getJokeById(id: Int): JokeLocalDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(joke: JokeLocalDto)

    @Delete
    suspend fun deleteJoke(joke: JokeLocalDto)
}