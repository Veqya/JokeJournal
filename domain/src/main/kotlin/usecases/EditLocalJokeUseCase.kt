package usecases

interface EditLocalJokeUseCase {
    suspend fun execute(id: Int, type: String, setup: String, punchline: String)
}