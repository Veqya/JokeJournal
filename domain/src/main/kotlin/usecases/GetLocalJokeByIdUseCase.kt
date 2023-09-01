package usecases

import entities.remote.Joke

interface GetLocalJokeByIdUseCase {
    suspend fun execute(id: Int): Joke
}