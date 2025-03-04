package com.example.catdogincompose.data.remote.api

import com.example.catdogincompose.data.remote.model.CatPhraseResponse
import com.example.catdogincompose.data.remote.model.DogPhraseResponse
import retrofit2.http.GET

/**
- * Interface for defining the API service.
+ * Interface for interacting with external phrase APIs.
 *
- * This interface defines the methods for fetching cat and dog phrases.
+ * This interface provides methods to retrieve random phrases (or "facts") about cats and dogs
+ * from their respective online APIs. It uses the Retrofit library's annotations
+ * (`@GET`, `suspend`) to define the API endpoints and request structure.
 */
interface PhraseApiService {
    @GET("https://catfact.ninja/fact")
    suspend fun getCatPhrase(): CatPhraseResponse

    @GET("https://dog-api.kinduff.com/api/facts")
    suspend fun getDogPhrase(): DogPhraseResponse
}