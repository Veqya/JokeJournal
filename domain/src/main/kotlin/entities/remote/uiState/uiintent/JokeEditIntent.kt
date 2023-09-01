package entities.remote.uiState.uiintent

sealed class JokeEditIntent {
    data class UpdateType(val type: String) : JokeEditIntent()
    data class UpdateSetup(val setup: String) : JokeEditIntent()
    data class UpdatePunchline(val punchline: String) : JokeEditIntent()
}