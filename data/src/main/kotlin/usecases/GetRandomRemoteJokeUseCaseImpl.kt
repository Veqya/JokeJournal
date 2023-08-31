package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import remote.services.JokesApi
import javax.inject.Inject

class GetRandomRemoteJokeUseCaseImpl @Inject constructor(
    private val api: JokesApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : GetRandomRemoteJokeUseCase {
    override suspend fun execute(): Joke = withContext(dispatcher) {
        api.getRandomJoke()
    }

}
