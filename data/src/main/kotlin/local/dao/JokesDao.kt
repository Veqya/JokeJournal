package local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import local.entities.JokeLocalDto

@Dao
interface JokesDao {
    @Query("SELECT * FROM jokes WHERE id = :id")
    fun getJokeById(id: Int): Flow<JokeLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: JokeLocalDto)

    @Delete
    fun deleteJoke(joke: JokeLocalDto)

    @Query("SELECT * FROM jokes")
    fun getLocalJokes(): Flow<List<JokeLocalDto>>
}
