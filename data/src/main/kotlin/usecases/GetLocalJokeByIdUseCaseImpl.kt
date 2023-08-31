package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import repositories.JokesRepository
import javax.inject.Inject

class GetLocalJokeByIdUseCaseImpl @Inject constructor(
    private val jokesRepository: JokesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GetLocalJokeByIdUseCase {
    override suspend fun execute(id: Int): Flow<Joke> =
        withContext(dispatcher) {
            jokesRepository.getLocalJokeById(id)
        }
}