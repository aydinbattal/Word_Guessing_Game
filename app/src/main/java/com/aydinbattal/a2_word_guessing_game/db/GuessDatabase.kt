package com.aydinbattal.a2_word_guessing_game.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aydinbattal.a2_word_guessing_game.models.Guess

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-08 */

@Database(entities = [Guess::class], version=1)
abstract class GuessDatabase : RoomDatabase() {

    abstract fun getGuessDAO(): GuessDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: GuessDatabase? = null

        fun getDatabase(context: Context): GuessDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuessDatabase::class.java,
                    "guess_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
