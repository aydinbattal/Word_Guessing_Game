package com.aydinbattal.a2_word_guessing_game.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aydinbattal.a2_word_guessing_game.databinding.CustomRowLayoutBinding
import com.aydinbattal.a2_word_guessing_game.models.Guess

/**
 * A2-Word-Guessing-Game created by aydin
 * student ID : 991521740
 * on 2022-03-09 */

class GuessAdapter(val guessList:List<Guess>)
    : RecyclerView.Adapter<GuessAdapter.GuessViewHolder>() {

    // view holder definition
    inner class GuessViewHolder(val binding: CustomRowLayoutBinding
    ):RecyclerView.ViewHolder(binding.root) {}

    // mandatory functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuessViewHolder {
        // boilerplate that you need to remember
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CustomRowLayoutBinding.inflate(layoutInflater, parent, false)
        return GuessViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuessViewHolder, position: Int) {
        // specify what data should be placed in each UI element of the custom row layout
        // 1. get the current employee from the list
        val currGuess = this.guessList[position]
        // 2. associate the employee's information with the UI in the row layout
        holder.binding.tvGuess.text = currGuess.userGuess
    }

    override fun getItemCount(): Int {
        // return the total # of items in your data source
        return guessList.size
    }

}
