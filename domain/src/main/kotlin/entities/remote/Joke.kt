package entities.remote

data class Joke(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)