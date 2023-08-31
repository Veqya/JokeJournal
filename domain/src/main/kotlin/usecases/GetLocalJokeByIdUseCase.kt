package usecases

import entities.remote.Joke
import kotlinx.coroutines.flow.Flow

interface GetLocalJokeByIdUseCase {
    suspend fun execute(id: Int): Flow<Joke>
}