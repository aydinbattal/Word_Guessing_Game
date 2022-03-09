package com.aydinbattal.a2_word_guessing_game.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.aydinbattal.a2_word_guessing_game.models.Guess

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-08 */

@Dao
interface GuessDAO {
    // create
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: List<Guess>)
}