package com.example.catdogincompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catdogincompose.data.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Phrase screen.
 *
 * This ViewModel is used to manage the state of the Phrase screen.
 */
@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val phraseRepository: PhraseRepository, // injeta o repositorio de frases
) : ViewModel() {

    private val _currentPhrase = MutableStateFlow("")
    val currentPhrase: StateFlow<String> = _currentPhrase

    private val _isCatSelected = MutableStateFlow(true)
    val isCatSelected: StateFlow<Boolean> = _isCatSelected

    private val _isDogSelected = MutableStateFlow(false)
    val isDogSelected: StateFlow<Boolean> = _isDogSelected

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    init {
        viewModelScope.launch {
            loadRandomCatPhrase()
        }
    }

    fun loadRandomCatPhrase() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _isCatSelected.value = true
                _isDogSelected.value = false
                _currentPhrase.value = phraseRepository.getRandomCatPhrase().text
            } catch (e: Exception) {
                _currentPhrase.value = "Error loading phrase: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadRandomDogPhrase() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _isCatSelected.value = false
                _isDogSelected.value = true
                _currentPhrase.value = phraseRepository.getRandomDogPhrase().text
            } catch (e: Exception) {
                _currentPhrase.value = "Error loading phrase: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadNewPhrase() {
        if (_isLoading.value) return // Don't load a new phrase if we're already loading one
        viewModelScope.launch {
            if (_isCatSelected.value) {
                loadRandomCatPhrase()
            } else {
                loadRandomDogPhrase()
            }
        }
    }
}