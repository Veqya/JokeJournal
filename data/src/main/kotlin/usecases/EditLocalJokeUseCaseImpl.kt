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
    override suspend fun execute(id: Int, type: String, setup: String, punchline: String) =
        with(dispatcher) {
            jokesRepository.editLocalJoke(Joke(id, type, setup, punchline))
        }
}
