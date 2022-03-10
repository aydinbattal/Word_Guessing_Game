package com.aydinbattal.a2_word_guessing_game.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aydinbattal.a2_word_guessing_game.db.GuessDatabase

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-09 */
class MainActivityViewModelFactory(private val db: GuessDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}