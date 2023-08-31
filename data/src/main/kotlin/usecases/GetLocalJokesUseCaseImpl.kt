package usecases

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import local.dao.JokesDao
import local.entities.extensions.toRemote
import javax.inject.Inject

class GetLocalJokesUseCaseImpl @Inject constructor(
    private val jokesDao: JokesDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GetLocalJokesUseCase {
    override suspend fun execute(): Flow<List<Joke>> =
        withContext(dispatcher) {
            jokesDao.getLocalJokes().map { jokes ->
                jokes.map { jokeLocalDto ->
                    jokeLocalDto.toRemote()
                }
            }
        }
}