package usecases

import entities.remote.Joke

interface DeleteLocalJokeUseCase {
    suspend fun execute(joke: Joke)
}