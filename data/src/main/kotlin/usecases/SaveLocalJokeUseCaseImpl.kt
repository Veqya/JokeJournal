package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import local.dao.JokesDao
import local.entities.extensions.toLocal
import javax.inject.Inject

class SaveLocalJokeUseCaseImpl @Inject constructor(
    private val jokesDao: JokesDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SaveLocalJokeUseCase {
    override suspend fun execute(joke: Joke) = withContext(dispatcher) {
        jokesDao.insertJoke(joke.toLocal())
    }
}
