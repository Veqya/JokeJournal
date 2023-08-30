package usecases

interface DeleteLocalJokeUseCase {
    suspend fun execute(jokeId: Int)
}