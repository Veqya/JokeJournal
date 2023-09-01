package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import repositories.JokesRepository
import javax.inject.Inject

class GetLocalJokesUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GetLocalJokesUseCase {
    override suspend fun execute(): Flow<List<Joke>> =
        withContext(dispatcher) {
            jokesRepository.getLocalJokes()
        }
}