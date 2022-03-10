package com.aydinbattal.a2_word_guessing_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aydinbattal.a2_word_guessing_game.adapters.GuessAdapter
import com.aydinbattal.a2_word_guessing_game.databinding.ActivityMainBinding
import com.aydinbattal.a2_word_guessing_game.db.GuessDatabase
import com.aydinbattal.a2_word_guessing_game.models.Guess
import com.aydinbattal.a2_word_guessing_game.viewmodels.MainActivityViewModel
import com.aydinbattal.a2_word_guessing_game.viewmodels.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var guessList:MutableList<Guess> = mutableListOf()

    private lateinit var db:GuessDatabase

    var counter = 0

    // view model
    val vm: MainActivityViewModel by viewModels(){
        MainActivityViewModelFactory(db)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize the database
        db = GuessDatabase.getDatabase(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup your RecyclerView
        // create an instance of the adapter class
        val guessAdapter = GuessAdapter(guessList)
        // configure the layout manager for the rv
        binding.rvGuesses.layoutManager = LinearLayoutManager(this)
        // associate the rv with the adapter we created
        binding.rvGuesses.adapter = guessAdapter


        // list of guesses to display in the recycler view
//        guessList.add(Guess(1, "joe", "biden"))
//        guessList.add(Guess(2, "joe", "aydin"))
//        guessList.add(Guess(3, "joe", "white"))

        //create an observer
        var gameOverObserver = Observer<Boolean> {
                gameOver ->
            Log.d("ABC", "Detected that something changed in the isGameOver: $gameOver")
            if (gameOver == true && binding.etGuess.text.toString().uppercase() == vm.secretWord){
                binding.tvResult.isVisible = true
                binding.tvResult.text = "YOU WIN"
                binding.btnSubmit.isEnabled = false

            }else if (gameOver == true && binding.etGuess.text.toString().uppercase() != vm.secretWord) {
                binding.tvResult.isVisible = true
                binding.tvResult.text = "YOU LOSE"
                binding.btnSubmit.isEnabled = false
            } else{
                binding.btnSubmit.isEnabled = true
            }
        }

        // associate the observer with the variable in the viewModel
        vm.isGameOver.observe(this, gameOverObserver)

        //create an observer
        var guessListObserver = Observer<List<Guess>> {
                gl ->
            Log.d("ABC", "Detected that something changed in the guessList: $gl")

            // tell the adapter that the data changed
            guessAdapter.notifyDataSetChanged()
        }

        // associate the observer with the variable in the viewModel
        vm.guessList.observe(this, guessListObserver)

        binding.btnSubmit.setOnClickListener{
            vm.secretWord(Guess(counter, vm.secretWord, binding.etGuess.text.toString().uppercase()))
            guessList.add(Guess(counter, vm.secretWord, binding.etGuess.text.toString().uppercase()))
            counter += 1
            binding.etGuess.text.clear()
        }

        binding.btnRestart.setOnClickListener{
            counter = 0
            binding.etGuess.text.clear()
            guessList.clear()
            guessAdapter.notifyDataSetChanged()
            binding.tvResult.isVisible = false
            binding.btnSubmit.isEnabled = true
        }

        binding.btnSave.setOnClickListener{
            vm.saveGame()
        }

    }
}
