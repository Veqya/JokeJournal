package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import repositories.JokesRepository
import javax.inject.Inject

class SaveLocalJokeUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SaveLocalJokeUseCase {
    override suspend fun execute(joke: Joke) = withContext(dispatcher) {
        jokesRepository.saveLocalJoke(joke)
    }
}
