package com.aydinbattal.a2_word_guessing_game.viewmodels

import android.util.Log
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
    var guessList:MutableLiveData<MutableList<Guess>> = MutableLiveData(mutableListOf())
    var isGameOver = MutableLiveData<Boolean>(false)

    fun saveGame(){
        db.getGuessDAO().insert(guessList.value!!.toList())
    }

    fun secretWord(userGuess:Guess){
        if (userGuess.userGuess == secretWord || userGuess.chanceId == 4){
            isGameOver.value = true
        }
        guessList.value?.add(userGuess)
        guessList.value = guessList.value

        Log.d("ABC", guessList.value.toString())
    }
}