package com.jokejournal.data.local.usecases

import entities.remote.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.SaveLocalJokeUseCase
import usecases.SaveLocalJokeUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class SaveLocalJokeUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var saveLocalJokeUseCase: SaveLocalJokeUseCase

    @Before
    fun setUp() {
        saveLocalJokeUseCase = SaveLocalJokeUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute saves joke to repository`(): Unit = runBlocking {
        val testJoke = Joke(1, "Test Type", "Test Setup", "Test Punchline")

        saveLocalJokeUseCase.execute(testJoke)

        verify(jokesRepository).saveLocalJoke(testJoke)
    }
}
