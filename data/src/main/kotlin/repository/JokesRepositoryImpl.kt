package repository

import common.IoDispatcher
import common.utils.safeApiCall
import entities.remote.ActionResult
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import local.dao.JokesDao
import local.entities.extensions.toLocal
import local.entities.extensions.toRemote
import remote.services.JokesApi
import repositories.JokesRepository
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val jokesDao: JokesDao,
    private val api: JokesApi,
) : JokesRepository {
    override suspend fun getRandomRemoteJoke(): ActionResult<Joke> = withContext(dispatcher) {
        safeApiCall { api.getRandomJoke() }
    }

    override suspend fun saveLocalJoke(joke: Joke) = withContext(dispatcher) {
        jokesDao.insertJoke(joke.toLocal())
    }

    override suspend fun editLocalJoke(joke: Joke) = withContext(dispatcher) {
        jokesDao.insertJoke(joke.toLocal())
    }

    override suspend fun getLocalJokeById(id: Int): Joke = withContext(dispatcher) {
        jokesDao.getJokeById(id).toRemote()
    }

    override suspend fun getLocalJokes(): Flow<List<Joke>> = withContext(dispatcher) {
        jokesDao.getLocalJokes().map { jokes ->
            jokes.map { jokeLocalDto ->
                jokeLocalDto.toRemote()
            }
        }
    }

    override suspend fun deleteLocalJokeById(joke: Joke) = withContext(dispatcher) {
        jokesDao.deleteJoke(joke.toLocal())
    }
}
