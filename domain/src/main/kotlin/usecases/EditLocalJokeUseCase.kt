package usecases

import entities.remote.Joke

interface EditLocalJokeUseCase {
    suspend fun execute(joke: Joke)
}