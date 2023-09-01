package repositories

import entities.remote.ActionResult
import entities.remote.Joke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {
    suspend fun getRandomRemoteJoke(): ActionResult<Joke>
    suspend fun saveLocalJoke(joke: Joke)
    suspend fun deleteLocalJokeById(joke: Joke)
    suspend fun editLocalJoke(joke: Joke)

    suspend fun getLocalJokeById(id: Int): Joke
    suspend fun getLocalJokes(): Flow<List<Joke>>
}