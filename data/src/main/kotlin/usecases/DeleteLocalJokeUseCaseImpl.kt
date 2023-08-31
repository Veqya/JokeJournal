package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import local.dao.JokesDao
import local.entities.extensions.toLocal
import javax.inject.Inject

class DeleteLocalJokeUseCaseImpl @Inject constructor(
    private val jokesDao: JokesDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DeleteLocalJokeUseCase {
    override suspend fun execute(joke: Joke) = with(dispatcher) {
        jokesDao.deleteJoke(joke.toLocal())
    }
}
