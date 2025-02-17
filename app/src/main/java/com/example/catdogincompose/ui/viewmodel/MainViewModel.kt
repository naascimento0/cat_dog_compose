package com.example.catdogincompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catdogincompose.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Main screen.
 *
 * This ViewModel is used to manage the state of the Main screen.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    init { // inicializa o nome do usuario se ele existir no shared preferences, senao deixa vazio
        viewModelScope.launch {
            _name.value = userPreferencesRepository.getUserName() ?: ""
        }
    }

    fun saveName(newName: String) {
        _name.value = newName
        viewModelScope.launch {
            userPreferencesRepository.saveUserName(newName)
        }
    }
}