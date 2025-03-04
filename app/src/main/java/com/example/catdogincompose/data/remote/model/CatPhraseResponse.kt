package com.example.catdogincompose.data.remote.model

import kotlinx.serialization.Serializable

/**
 * Represents a response containing a cat-related phrase (fact).
 *
 * This data class is designed to hold information about a single cat fact,
 * specifically the fact itself and its length (number of characters).
 * It is annotated with `@Serializable` from the kotlinx.serialization library,
 * allowing it to be easily serialized to and deserialized from various data formats
 * such as JSON.
 *
 * @property fact The cat fact as a string. This is the main content of the response.
 * @property length The length of the cat fact string, representing the number of characters.
 */
@Serializable
data class CatPhraseResponse(
    val fact: String,
    val length: Int
)
