package com.jokejournal.android.ui.jokes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import entities.remote.ActionResult
import entities.remote.CommonState
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
    private val _state = MutableStateFlow(CommonState.NORMAL)
    val state = _state.asStateFlow()
    private val _error = MutableStateFlow<Throwable?>(null)
    val error = _error.asStateFlow()
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
            setState(CommonState.PROGRESS)
            when (val response = getRandomRemoteJokeUseCase.execute()) {
                is ActionResult.Success -> {
                    saveLocalJokeUseCase.execute(response.data)
                    setState(CommonState.NORMAL)
                }

                is ActionResult.Error -> {
                    setError(response.message)
                }

                is ActionResult.Failure -> {
                    setError(response.message)
                    _state.value = CommonState.NO_CONNECTION
                }
            }
        }
    }

    private fun setError(message: String?) {
        _error.value = Throwable(message = message)
    }

    fun resetError() {
        _error.value = null
        setState(CommonState.NORMAL)
    }

    private fun setState(state: CommonState) {
        _state.value = state
    }
}