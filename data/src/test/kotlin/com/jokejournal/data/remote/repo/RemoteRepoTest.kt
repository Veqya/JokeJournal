package com.jokejournal.data.remote.repo

import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.GetLocalJokeByIdUseCase
import usecases.GetLocalJokeByIdUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class RemoteRepoTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var useCase: GetLocalJokeByIdUseCase

    @Before
    fun setUp() {
        useCase = GetLocalJokeByIdUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `test get joke by ID`() = runBlocking {
        val jokeId = 1
        val expectedJoke = Joke(jokeId, "Test Joke Type", "Test Joke Setup", "Test Joke Punchline")

        Mockito.`when`(jokesRepository.getLocalJokeById(jokeId)).thenReturn(expectedJoke)

        val result = useCase.execute(jokeId)

        assertEquals(expectedJoke, result)
    }
}