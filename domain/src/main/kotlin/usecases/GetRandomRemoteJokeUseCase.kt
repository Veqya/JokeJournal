package usecases

import entities.remote.ActionResult
import entities.remote.Joke

interface GetRandomRemoteJokeUseCase {
    suspend fun execute(): ActionResult<Joke>
}