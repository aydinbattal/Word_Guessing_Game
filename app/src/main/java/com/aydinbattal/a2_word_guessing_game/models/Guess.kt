package com.aydinbattal.a2_word_guessing_game.models

import androidx.room.Entity

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-08 */

@Entity(tableName="guesses")
data class Guess(val chanceId:Int, val secretWord:String, val userGuess:String) {

    fun getLetterStates():List<Result>{
        val resultList:MutableList<Result> = mutableListOf()
        userGuess.forEach {
            if (it in secretWord && userGuess.indexOf(it) == secretWord.indexOf(it)){
                resultList.add(Result.CORRECT)
            } else if (it in secretWord && userGuess.indexOf(it) != secretWord.indexOf(it)) {
                resultList.add(Result.WRONG_POSITION)
            } else {
                resultList.add(Result.NOT_IN_WORD)
            }
        }

        return resultList.toList()
    }
}