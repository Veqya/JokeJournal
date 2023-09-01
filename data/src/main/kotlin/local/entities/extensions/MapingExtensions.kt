package local.entities.extensions

import entities.remote.Joke
import local.entities.JokeLocalDto

fun Joke.toLocal() = JokeLocalDto(
    id, type, setup, punchline
)

fun JokeLocalDto.toRemote() = Joke(
    id, type, setup, punchline
)