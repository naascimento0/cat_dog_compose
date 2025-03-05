package com.example.catdogincompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catdogincompose.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * [MainViewModel] is the ViewModel responsible for managing the main screen's data and interactions.
 * It handles the user's name, persisting it to and retrieving it from the [UserPreferencesRepository].
 *
 * This ViewModel uses Hilt for dependency injection.
 *
 * @property userPreferencesRepository The repository for managing user preferences, such as the user's name.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    init { // inicializa o nome do usuario se ele existir no shared preferences, senao deixa vazio
        viewModelScope.launch {
            userPreferencesRepository.getUserName().collect { savedName ->
                _name.value = savedName ?: ""
            }
        }
    }

    fun saveName(newName: String) {
        _name.value = newName
        viewModelScope.launch {
            userPreferencesRepository.saveUserName(newName)
            _name.value = newName
        }
    }
}