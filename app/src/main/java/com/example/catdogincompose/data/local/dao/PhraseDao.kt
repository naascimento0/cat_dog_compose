package com.example.catdogincompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catdogincompose.data.local.entity.PhraseEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for interacting with the "phrases" table in the database.
 * This interface provides methods for inserting phrases and retrieving them based on their type.
 */
@Dao
interface PhraseDao {
    @Insert
    suspend fun insert(phrase: PhraseEntity)

    @Query("SELECT * FROM phrases WHERE type = 'cat'")
    fun getCatPhrase(): Flow<List<PhraseEntity>>

    @Query("SELECT * FROM phrases WHERE type = 'dog'")
    fun getDogPhrase(): Flow<List<PhraseEntity>>

    @Query("SELECT * FROM phrases WHERE type = :type ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomPhraseByType(type: String): PhraseEntity?
}