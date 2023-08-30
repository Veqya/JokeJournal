package entities.extensions

import entities.local.JokeLocalDto
import entities.remote.Joke

fun Joke.toLocal() = JokeLocalDto(
    id, type, setup, punchline
)