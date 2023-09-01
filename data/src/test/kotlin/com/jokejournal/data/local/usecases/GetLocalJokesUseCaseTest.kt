package com.jokejournal.data.local.usecases

import entities.remote.Joke
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import repositories.JokesRepository
import usecases.GetLocalJokesUseCase
import usecases.GetLocalJokesUseCaseImpl

@RunWith(MockitoJUnitRunner::class)
class GetLocalJokesUseCaseTest {

    @Mock
    lateinit var jokesRepository: JokesRepository

    private lateinit var getLocalJokesUseCase: GetLocalJokesUseCase

    @Before
    fun setUp() {
        getLocalJokesUseCase = GetLocalJokesUseCaseImpl(jokesRepository, Dispatchers.IO)
    }

    @Test
    fun `execute returns list of jokes from repository`(): Unit = runBlocking {
        val expectedJokes = listOf(
            Joke(1, "Test Type", "Test Setup 1", "Test Punchline 1"),
            Joke(2, "Test Type", "Test Setup 2", "Test Punchline 2")
        )
        Mockito.`when`(jokesRepository.getLocalJokes()).thenReturn(flowOf(expectedJokes))

        val result = getLocalJokesUseCase.execute().toList()

        assertEquals(expectedJokes, result.first())
    }
}
