package repositories

import entities.remote.Joke

interface JokesRepository {
    suspend fun getRandomRemoteJoke(): Joke
    suspend fun saveLocalJoke(joke: Joke)
    suspend fun deleteLocalJokeById(id: Int)
    suspend fun editLocalJoke(localDto: Joke)
}