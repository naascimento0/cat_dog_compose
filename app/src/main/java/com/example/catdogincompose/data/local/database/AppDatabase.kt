package com.example.catdogincompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catdogincompose.data.local.dao.PhraseDao
import com.example.catdogincompose.data.local.entity.PhraseEntity

/**
 * The Room database for the application.
 *
 * This class defines the database configuration, including the entities that make up the database
 * and the version number. It also provides access to the Data Access Objects (DAOs) that allow
 * interaction with the database.
 *
 * In this specific implementation, the database contains a single entity: [PhraseEntity].
 * It also provides access to the [PhraseDao] for performing CRUD (Create, Read, Update, Delete)
 * operations on the [PhraseEntity] table.
 *
 * @property phraseDao An abstract method that returns a [PhraseDao] instance. Room will generate
 *                     the implementation of this method. It is used to interact with the
 *                     [PhraseEntity] table in the database.
 * @constructor Creates an instance of AppDatabase.
 */
@Database(entities = [PhraseEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun phraseDao(): PhraseDao
}