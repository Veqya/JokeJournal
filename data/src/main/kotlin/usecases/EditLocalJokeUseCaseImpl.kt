package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import local.dao.JokesDao
import local.entities.extensions.toLocal
import javax.inject.Inject

class EditLocalJokeUseCaseImpl @Inject constructor(
    private val jokesDao: JokesDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : EditLocalJokeUseCase {
    override suspend fun execute(joke: Joke) = with(dispatcher) {
        jokesDao.insertJoke(joke.toLocal())
    }
}
