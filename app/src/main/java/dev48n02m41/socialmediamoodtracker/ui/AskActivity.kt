package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import dev48n02m41.socialmediamoodtracker.R

private lateinit var seekBarAfter: SeekBar
private lateinit var seekBarBefore: SeekBar
private lateinit var textViewHowIFeel: TextView
private lateinit var textViewHowIFelt: TextView
private lateinit var spinner: Spinner


class AskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        handleUI()
    }

    private fun handleUI() {
        title = "Mood Diary"
        seekBarBefore = findViewById(R.id.seekBarBefore)
        seekBarAfter = findViewById(R.id.seekBarAfter)
        textViewHowIFeel = findViewById(R.id.textViewHowIFeel)
        textViewHowIFelt = findViewById(R.id.textViewHowIFelt)

        seekBarBefore.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    0 -> textViewHowIFelt.text = "I felt terrible."
                    1 -> textViewHowIFelt.text = "I felt bad."
                    2 -> textViewHowIFelt.text = "I felt okay."
                    3 -> textViewHowIFelt.text = "I felt good."
                    4 -> textViewHowIFelt.text = "I felt fantastic!"
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarAfter.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    0 -> textViewHowIFeel.text = "I feel terrible."
                    1 -> textViewHowIFeel.text = "I feel bad."
                    2 -> textViewHowIFeel.text = "I feel okay."
                    3 -> textViewHowIFeel.text = "I feel good."
                    4 -> textViewHowIFeel.text = "I feel fantastic!"
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.social_networks_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }
}