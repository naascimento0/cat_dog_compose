package com.example.catdogincompose.data.remote.model

import kotlinx.serialization.Serializable

/**
 * Represents the response from a dog fact API, containing a list of dog facts and
 * a success indicator.
 *
 * @property facts A list of strings, where each string is a unique dog fact.
 * @property success A boolean indicating whether the request to retrieve dog facts was successful.
 *                   `true` if successful, `false` otherwise.
 */
@Serializable
data class DogPhraseResponse(
    val facts: List<String>,
    val success: Boolean
)
