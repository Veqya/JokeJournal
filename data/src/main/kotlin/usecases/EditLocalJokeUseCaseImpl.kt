package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import repositories.JokesRepository
import javax.inject.Inject

class EditLocalJokeUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : EditLocalJokeUseCase {
    override suspend fun execute(joke: Joke) = with(dispatcher) {
        jokesRepository.editLocalJoke(joke)
    }
}
