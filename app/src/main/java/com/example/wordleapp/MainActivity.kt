package com.example.wordleapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var summitButton: Button
    private lateinit var resetButton: Button
    private lateinit var firstGuessTextView: TextView
    private lateinit var firstGuessCheckTextView: TextView
    private lateinit var secondGuessTextView: TextView
    private lateinit var secondGuessCheckTextView: TextView
    private lateinit var thirdGuessTextView: TextView
    private lateinit var thirdGuessCheckTextView: TextView
    public var clicks = 0
    public lateinit var wordToGuess: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //setting up variables
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        inputEditText = findViewById<EditText>(R.id.input)
        summitButton = findViewById(R.id.summit_button)
        resetButton = findViewById(R.id.reset_button)
        firstGuessTextView = findViewById(R.id.first_guess)
        firstGuessCheckTextView = findViewById(R.id.first_guess_check)
        secondGuessTextView = findViewById(R.id.second_guess)
        secondGuessCheckTextView = findViewById(R.id.second_guess_check)
        thirdGuessTextView = findViewById(R.id.third_guess)
        thirdGuessCheckTextView = findViewById(R.id.third_guess_check)



        summitButton.setOnClickListener {
            clicks+=1
            var userInputText = inputEditText.text.toString()
            if (clicks == 1) {
                firstGuessTextView.text = userInputText
                firstGuessCheckTextView.text = checkGuess(userInputText)
            } else if (clicks == 2) {
                secondGuessTextView.text = userInputText
                secondGuessCheckTextView.text = checkGuess(userInputText)
            } else if (clicks == 3) {
                thirdGuessTextView.text = userInputText
                thirdGuessCheckTextView.text = checkGuess(userInputText)
                resetGame()
                userInputText = ""
            }
        }

        resetButton.setOnClickListener {
            resetGame()
        }




    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }

    private fun resetGame() {
        clicks = 0
        firstGuessTextView.text = ""
        firstGuessCheckTextView.text = ""
        secondGuessTextView.text = ""
        secondGuessCheckTextView.text = ""
        thirdGuessTextView.text = ""
        thirdGuessCheckTextView.text = ""
        Toast.makeText(this, "word was $wordToGuess", Toast.LENGTH_LONG).show()
        wordToGuess = FourLetterWordList.getRandomFourLetterWord()
        inputEditText.text.clear()
    }
}