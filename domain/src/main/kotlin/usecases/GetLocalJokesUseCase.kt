package usecases

import entities.remote.Joke
import kotlinx.coroutines.flow.Flow

interface GetLocalJokesUseCase {
    suspend fun execute(): Flow<List<Joke>>
}