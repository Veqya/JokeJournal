package usecases

import entities.remote.Joke

interface GetRandomRemoteJokeUseCase {
    suspend fun execute(): Joke
}