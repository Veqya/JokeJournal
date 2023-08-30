package entities.local

data class JokeLocalDto(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)