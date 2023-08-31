package com.jokejournal.android.ui.jokes.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import entities.remote.Joke
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import usecases.GetLocalJokesUseCase
import usecases.GetRandomRemoteJokeUseCase
import usecases.SaveLocalJokeUseCase
import javax.inject.Inject

@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val getLocalJokesUseCase: GetLocalJokesUseCase,
    private val getRandomRemoteJokeUseCase: GetRandomRemoteJokeUseCase,
    private val saveLocalJokeUseCase: SaveLocalJokeUseCase
) : ViewModel() {
    private val _localJokes = MutableStateFlow<List<Joke>?>(null)
    val localJokes: StateFlow<List<Joke>?> = _localJokes.asStateFlow()

    init {
        viewModelScope.launch {
            getLocalJokesUseCase.execute().collect { jokes ->
                _localJokes.value = jokes
            }
        }
    }

    fun fetchRandomJokes() {
        viewModelScope.launch {
            getRandomRemoteJokeUseCase.execute().let {
                Log.e("fetchRandomJokes: ", it.toString())
            }
            saveLocalJokeUseCase.execute(getRandomRemoteJokeUseCase.execute())
        }
    }
}