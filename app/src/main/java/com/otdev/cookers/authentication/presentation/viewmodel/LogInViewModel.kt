package com.otdev.cookers.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otdev.cookers.authentication.domain.usecase.interfaces.LogIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logIn: LogIn,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    data class UIState(
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed class UIEvent {
        data object NavigateToHome: UIEvent()
    }

    sealed class UIAction {
        data class LogIn(val userName: String, val password: String) : UIAction()
    }

    fun sendIntent(intent: UIAction) {
        when (intent) {
            is UIAction.LogIn -> logUser(intent.userName, intent.password)
        }
    }

    private fun logUser(userName: String, password: String) {
        viewModelScope.launch {

            _uiState.update { state -> state.copy(isLoading = true) }

            logIn(userName, password).fold(
                onSuccess = {
                    _uiState.update { state -> state.copy(isLoading = false) }

                    _uiEvent.emit(UIEvent.NavigateToHome)
                },
                onFailure = { exception ->
                    println(exception)
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            error = ""
                        )
                    }
                }
            )
        }
    }
}