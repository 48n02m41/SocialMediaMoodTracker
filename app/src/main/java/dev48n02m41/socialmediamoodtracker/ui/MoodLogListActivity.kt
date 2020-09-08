package dev48n02m41.socialmediamoodtracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.ui.viewmodels.DiaryEntryViewModel

private lateinit var diaryEntryViewModel: DiaryEntryViewModel

class MoodLogListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_log_list)

        // ViewModel
        diaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)

        diaryEntryViewModel.allDiaryEntries.observe(this, { results ->
            results?.let {

                // TODO: Hook up to recycler adapter.

                Log.d(MoodLogListActivity.TAG, "Number of diary entries in database right now: " + results.size)
            }
        })

    }

    companion object {
        private const val TAG = "MoodLogListActivity"
    }
}