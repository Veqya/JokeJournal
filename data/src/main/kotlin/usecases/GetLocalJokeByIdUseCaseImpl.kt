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

class GetLocalJokeByIdUseCaseImpl @Inject constructor(
    private val jokesDao: JokesDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : GetLocalJokeByIdUseCase {
    override suspend fun execute(id: Int): Flow<Joke> =
        withContext(dispatcher) {
            jokesDao.getJokeById(id).map { jokeLocal ->
                jokeLocal.toRemote()
            }
        }
}