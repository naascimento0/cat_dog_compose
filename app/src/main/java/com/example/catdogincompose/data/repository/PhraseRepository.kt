package com.example.catdogincompose.data.repository

import com.example.catdogincompose.data.remote.api.PhraseApiService
import com.example.catdogincompose.domain.model.Phrase
import javax.inject.Inject

/**
 * Repository for managing phrases fetched from external APIs.
 *
 * This repository acts as a data access layer for retrieving animal-related phrases (currently cat and dog).
 * It abstracts the network calls and provides a clean interface for the application to access phrase data.
 * It handles the transformation of API responses into `Phrase` objects and manages potential errors.
 *
 * @property apiService The service responsible for making API calls to fetch phrases.
 */
class PhraseRepository @Inject constructor(
    private val apiService: PhraseApiService
) {

    suspend fun getRandomCatPhrase(): Phrase {
        val response = apiService.getCatPhrase()
        return Phrase(response.fact)
    }

    suspend fun getRandomDogPhrase(): Phrase {
        val response = apiService.getDogPhrase()
        if (response.success) {
            return Phrase(response.facts.first())
        } else {
            throw Exception("Failed to get dog phrase")
        }
    }
}