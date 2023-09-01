package com.jokejournal.data.local.usecases

import entities.remote.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.EditLocalJokeUseCase
import usecases.EditLocalJokeUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class EditLocalJokeUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var editLocalJokeUseCase: EditLocalJokeUseCase

    @Before
    fun setUp() {
        editLocalJokeUseCase = EditLocalJokeUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute calls repository's edit method with correct joke`() = runBlocking {
        val jokeId = 1
        val type = "Test Type"
        val setup = "Test Setup"
        val punchline = "Test Punchline"
        val editedJoke = Joke(jokeId, type, setup, punchline)

        editLocalJokeUseCase.execute(jokeId, type, setup, punchline)

        verify(jokesRepository).editLocalJoke(editedJoke)
    }

    @Test(expected = Throwable::class)
    fun `execute throws error when repository throws error`() = runBlocking {
        val jokeId = 1
        val type = "Test Type"
        val setup = "Test Setup"
        val punchline = "Test Punchline"
        val error = Throwable("Test error")
        val editedJoke = Joke(jokeId, type, setup, punchline)

        Mockito.doThrow(error).`when`(jokesRepository).editLocalJoke(editedJoke)

        editLocalJokeUseCase.execute(jokeId, type, setup, punchline)
    }
}
