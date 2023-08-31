package repository

import common.IoDispatcher
import entities.remote.Joke
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import repositories.JokesRepository
import usecases.DeleteLocalJokeUseCase
import usecases.EditLocalJokeUseCase
import usecases.GetLocalJokeByIdUseCase
import usecases.GetLocalJokesUseCase
import usecases.GetRandomRemoteJokeUseCase
import usecases.SaveLocalJokeUseCase
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val deleteLocalJokeUseCase: DeleteLocalJokeUseCase,
    private val editLocalJokeUseCase: EditLocalJokeUseCase,
    private val getRandomRemoteJokeUseCase: GetRandomRemoteJokeUseCase,
    private val saveLocalJokeUseCase: SaveLocalJokeUseCase,
    private val getLocalJokeByIdUseCase: GetLocalJokeByIdUseCase,
    private val getLocalJokesUseCase: GetLocalJokesUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : JokesRepository {
    override suspend fun getRandomRemoteJoke(): Joke = withContext(dispatcher) {
        getRandomRemoteJokeUseCase.execute()
    }

    override suspend fun saveLocalJoke(joke: Joke) = withContext(dispatcher) {
        saveLocalJokeUseCase.execute(joke)
    }

    override suspend fun editLocalJoke(joke: Joke) = withContext(dispatcher) {
        editLocalJokeUseCase.execute(joke)
    }

    override suspend fun getLocalJokeById(id: Int): Flow<Joke> = withContext(dispatcher) {
        getLocalJokeByIdUseCase.execute(id)
    }

    override suspend fun getLocalJokes(): Flow<List<Joke>> = withContext(dispatcher) {
        getLocalJokesUseCase.execute()
    }

    override suspend fun deleteLocalJokeById(joke: Joke) = withContext(dispatcher) {
        deleteLocalJokeUseCase.execute(joke)
    }
}
