package usecases

import entities.remote.Joke

interface SaveLocalJokeUseCase {
    suspend fun execute(joke: Joke)
}