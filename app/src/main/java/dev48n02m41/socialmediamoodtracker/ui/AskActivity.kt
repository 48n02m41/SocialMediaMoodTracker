package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import java.time.Instant
import java.util.*

private lateinit var seekBarAfter: SeekBar
private lateinit var seekBarBefore: SeekBar
private lateinit var textViewHowIFeel: TextView
private lateinit var textViewHowIFelt: TextView
private lateinit var spinner: Spinner
private var beforeRating: Int = 0
private var afterRating: Int = 0
private var chosenSocialNetwork: String = ""

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
                beforeRating = progress
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
                afterRating = progress
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

        spinner.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                chosenSocialNetwork = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "Chosen social network: $chosenSocialNetwork")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    fun submit(view: View) {
        // TODO: implement
        Log.d(TAG, "Submit function was triggered.")

        var newDiary = DiaryEntryEntity(chosenSocialNetwork, beforeRating, afterRating)
    }


    companion object {
        private const val TAG = "AskActivity"
    }
}