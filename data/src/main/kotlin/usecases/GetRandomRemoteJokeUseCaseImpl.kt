package usecases

import common.IoDispatcher
import entities.remote.ActionResult
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import repositories.JokesRepository
import javax.inject.Inject

class GetRandomRemoteJokeUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetRandomRemoteJokeUseCase {
    override suspend fun execute(): ActionResult<Joke> = withContext(dispatcher) {
        jokesRepository.getRandomRemoteJoke()
    }

}
