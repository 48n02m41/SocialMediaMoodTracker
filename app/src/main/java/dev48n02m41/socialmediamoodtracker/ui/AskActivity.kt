package dev48n02m41.socialmediamoodtracker.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.ui.viewmodels.DiaryEntryViewModel
import kotlinx.coroutines.delay

private lateinit var seekBarAfter: SeekBar
private lateinit var seekBarBefore: SeekBar
private lateinit var textViewHowIFeel: TextView
private lateinit var textViewHowIFelt: TextView
private lateinit var btnSubmit: Button
private lateinit var spinner: Spinner
private var beforeRating: Int = 0
private var afterRating: Int = 0
private var chosenSocialNetwork: String = ""
private lateinit var diaryEntryViewModel: DiaryEntryViewModel

class AskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask)
        handleUI()
        animate()

        // ViewModel
        diaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)

        diaryEntryViewModel.allDiaryEntries.observe(this, { results ->
            results?.let {
                Log.d(TAG, "Number of diary entries in database right now: " + results.size)
            }
        })
    }

    private fun handleUI() {
        setSupportActionBar(findViewById(R.id.toolbar))

        title = "Mood Diary"
        seekBarBefore = findViewById(R.id.seekBarBefore)
        seekBarAfter = findViewById(R.id.seekBarAfter)
        textViewHowIFeel = findViewById(R.id.textViewHowIFeel)
        textViewHowIFelt = findViewById(R.id.textViewHowIFelt)
        btnSubmit = findViewById(R.id.btn_submit)

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

    private fun animate() {
        val from: Int = ContextCompat.getColor(this, R.color.colorPrimary)
        val to: Int = ContextCompat.getColor(this, R.color.colorSecondary)
        val extra: Int = ContextCompat.getColor(this, R.color.colorAlmostWhite)
        val myView: View = findViewById(R.id.view_ask)
        val actionBar: ActionBar? = supportActionBar

        ValueAnimator.ofObject(ArgbEvaluator(), from, to).apply {
            addUpdateListener { updatedAnimation ->
                //myView.setBackgroundColor(updatedAnimation.animatedValue as Int)
                //actionBar?.setBackgroundDrawable(ColorDrawable(updatedAnimation.animatedValue as Int))
                seekBarBefore.progressDrawable.colorFilter = (PorterDuffColorFilter(
                    updatedAnimation.animatedValue as Int,
                    PorterDuff.Mode.SRC_IN
                ))
                seekBarBefore.thumb.colorFilter = (PorterDuffColorFilter(
                    updatedAnimation.animatedValue as Int,
                    PorterDuff.Mode.SRC_IN
                ))
            }
            duration = 3000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }

        ValueAnimator.ofObject(ArgbEvaluator(), from, to).apply {
            addUpdateListener { updatedAnimation ->
                seekBarAfter.progressDrawable.colorFilter = (PorterDuffColorFilter(
                    updatedAnimation.animatedValue as Int,
                    PorterDuff.Mode.SRC_IN
                ))
                seekBarAfter.thumb.colorFilter = (PorterDuffColorFilter(
                    updatedAnimation.animatedValue as Int,
                    PorterDuff.Mode.SRC_IN
                ))
            }
            duration = 4000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }

        ObjectAnimator.ofFloat(textViewHowIFelt, "scaleX", 1.03f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(textViewHowIFelt, "scaleY", 1.03f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(textViewHowIFeel, "scaleX", 1.01f).apply {
            duration = 1500
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(textViewHowIFeel, "scaleY", 1.01f).apply {
            duration = 1500
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnSubmit, "scaleX", 1.015f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(btnSubmit, "scaleY", 1.015f).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    fun submit(view: View) {
        val newDiary = DiaryEntryEntity(chosenSocialNetwork, beforeRating, afterRating)
        diaryEntryViewModel.insertDiaryEntry(newDiary)

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        private const val TAG = "AskActivity"
    }
}