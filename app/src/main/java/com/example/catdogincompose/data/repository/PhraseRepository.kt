package com.example.catdogincompose.data.repository

import com.example.catdogincompose.data.local.dao.PhraseDao
import com.example.catdogincompose.data.local.entity.PhraseEntity
import com.example.catdogincompose.data.remote.api.PhraseApiService
import com.example.catdogincompose.domain.model.Phrase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository for managing animal-related phrases, acting as a data source abstraction layer.
 *
 * This repository is responsible for fetching and providing animal-related phrases (currently cat and dog).
 * It serves as a central data access point, abstracting away the complexities of fetching data from
 * external APIs and local database storage.  It handles network requests through the `PhraseApiService`
 * and local persistence via the `PhraseDao`.
 *
 * **Key Responsibilities:**
 *
 * - **Data Abstraction:**  Hides the underlying data source (API or database) from the rest of the application.
 * - **Network & Persistence Management:** Handles fetching data from the network, storing it locally, and retrieving it from the local database when needed.
 * - **Error Handling:** Manages potential network or database errors, providing fallback mechanisms.
 * - **Data Transformation:** Transforms API responses (e.g., String facts) into `Phrase` objects for use within the application.
 * - **Caching:** Provides a basic caching mechanism by storing fetched phrases in the local database.
 *
 * **Functionality:**
 *
 * - Retrieves random cat phrases.
 * - Retrieves random dog phrases.
 *
 * **Error Handling:**
 *
 * - If a network request fails, it attempts to retrieve a phrase of the corresponding type from the local database.
 * - If no matching phrase is found in the database, it throws an `IllegalStateException` indicating that no phrases are available.
 *
 * @property apiService The service responsible for making API calls to fetch phrases from external sources.
 * @property phraseDao The Data Access Object (DAO) responsible for interacting with the local database for phrase storage and retrieval.
 */
class PhraseRepository @Inject constructor(
    private val apiService: PhraseApiService,
    private val phraseDao: PhraseDao
) {

    suspend fun getRandomCatPhrase(): Phrase {
        return try {
            val apiPhrase = apiService.getCatPhrase().fact
            phraseDao.insert(PhraseEntity(text = apiPhrase, type = "cat"))
            Phrase(apiPhrase)
        } catch (e: Exception) {
            phraseDao.getRandomPhraseByType("cat")?.let { Phrase(it.text) }
                ?: throw IllegalStateException("No cat phrases available")
        }
    }

    suspend fun getRandomDogPhrase(): Phrase {
        return try {
            val apiPhrase = apiService.getDogPhrase().facts.first()
            phraseDao.insert(PhraseEntity(text = apiPhrase, type = "dog"))
            Phrase(apiPhrase)
        } catch (e: Exception) {
            phraseDao.getRandomPhraseByType("dog")?.let { Phrase(it.text) }
                ?: throw IllegalStateException("No dog phrases available")
        }
    }
}