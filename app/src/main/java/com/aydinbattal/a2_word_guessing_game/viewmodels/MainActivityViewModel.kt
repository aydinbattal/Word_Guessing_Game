package com.aydinbattal.a2_word_guessing_game.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aydinbattal.a2_word_guessing_game.db.GuessDatabase
import com.aydinbattal.a2_word_guessing_game.models.Guess

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-08 */
class MainActivityViewModel(val db: GuessDatabase): ViewModel() {
    var secretWord:String = "CABLE"
    var guessList:MutableList<Guess> = mutableListOf()
    var isGameOver:Boolean = false
    //var isGameOver = MutableLiveData<Boolean>(false)

    fun saveGame(){
        //todo
    }

    fun secretWord(userGuess:Guess){
        if (userGuess.userGuess == userGuess.secretWord || userGuess.chanceId > 5){
            isGameOver
        }
        guessList.add(userGuess)
    }
}