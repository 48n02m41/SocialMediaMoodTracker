package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import dev48n02m41.socialmediamoodtracker.R

private lateinit var seekBar: SeekBar
private lateinit var textViewHowIFeel: TextView

class AskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)

        handleUI()
    }

    private fun handleUI() {
        title = "How do you feel?"

        seekBar = findViewById(R.id.seekBar)
        textViewHowIFeel = findViewById(R.id.textViewHowIFeel)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                when (progress) {
                    0 -> textViewHowIFeel.text = "I feel: terrible"
                    1 -> textViewHowIFeel.text = "I feel: bad"
                    2 -> textViewHowIFeel.text = "I feel: meh"
                    3 -> textViewHowIFeel.text = "I feel: good"
                    4 -> textViewHowIFeel.text = "I feel: fantastic"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}