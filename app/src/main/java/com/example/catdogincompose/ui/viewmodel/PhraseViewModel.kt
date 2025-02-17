package com.example.catdogincompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.catdogincompose.data.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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


    init {
        loadRandomCatPhrase()
    }

    fun loadRandomCatPhrase() {
        _isCatSelected.value = true
        _isDogSelected.value = false
        _currentPhrase.value = phraseRepository.getRandomCatPhrase().text
    }

    fun loadRandomDogPhrase() {
        _isCatSelected.value = false
        _isDogSelected.value = true
        _currentPhrase.value = phraseRepository.getRandomDogPhrase().text
    }

    fun loadNewPhrase() {
        if (_isCatSelected.value) {
            loadRandomCatPhrase()
        } else {
            loadRandomDogPhrase()
        }
    }
}