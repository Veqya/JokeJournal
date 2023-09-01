package com.jokejournal.android.ui.jokes.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import entities.remote.CommonState
import entities.remote.uiState.state.JokeUiState
import entities.remote.uiState.uiintent.JokeEditIntent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import usecases.DeleteLocalJokeUseCase
import usecases.EditLocalJokeUseCase
import usecases.GetLocalJokeByIdUseCase
import javax.inject.Inject

@HiltViewModel
class JokesItemEditViewModel @Inject constructor(
    private val deleteLocalJokeUseCase: DeleteLocalJokeUseCase,
    private val editLocalJokeUseCase: EditLocalJokeUseCase,
    private val getLocalJokeByIdUseCase: GetLocalJokeByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CommonState.NORMAL)
    val state = _state.asStateFlow()

    private val _jokeUiState = MutableStateFlow<JokeUiState?>(null)
    val jokeUiState = _jokeUiState.asStateFlow()

    private val _jokeUiIntents = MutableSharedFlow<JokeEditIntent>()

    init {
        viewModelScope.launch {
            _jokeUiIntents.collect { intent ->
                val newState = when (intent) {
                    is JokeEditIntent.UpdateType -> _jokeUiState.value?.copy(type = intent.type)
                    is JokeEditIntent.UpdateSetup -> _jokeUiState.value?.copy(setup = intent.setup)
                    is JokeEditIntent.UpdatePunchline -> _jokeUiState.value?.copy(punchline = intent.punchline)
                }
                _jokeUiState.value = newState
            }
        }
    }

    private fun processIntent(intent: JokeEditIntent) {
        viewModelScope.launch { _jokeUiIntents.emit(intent) }
    }

    private fun updateJokeUiState(newState: JokeUiState?) {
        _jokeUiState.value = newState
    }

    fun updateJokeTypeState(newType: String) {
        processIntent(JokeEditIntent.UpdateType(newType))
    }

    fun updateSetupTypeState(newSetup: String) {
        processIntent(JokeEditIntent.UpdateSetup(newSetup))
    }

    fun updatePunchlineState(newPunchline: String) {
        processIntent(JokeEditIntent.UpdatePunchline(newPunchline))
    }

    fun initLocalJokeById(jokeId: Int) {
        viewModelScope.launch {
            if (_jokeUiState.value == null) {
                with(getLocalJokeByIdUseCase.execute(jokeId)) {
                    updateJokeUiState(JokeUiState(type, setup, punchline))
                }
            }
        }
    }

    fun editLocaleJoke(id: Int, type: String, setup: String, punchline: String) {
        viewModelScope.launch {
            editLocalJokeUseCase.execute(id, type, setup, punchline)
            navigateUpState()
        }
    }

    fun deleteLocalJokeById(jokeId: Int) {
        viewModelScope.launch {
            deleteLocalJokeUseCase.execute(getLocalJokeByIdUseCase.execute(jokeId))
            navigateUpState()
        }
    }

    private fun navigateUpState() {
        _state.value = CommonState.NAVIGATE_UP
    }
}