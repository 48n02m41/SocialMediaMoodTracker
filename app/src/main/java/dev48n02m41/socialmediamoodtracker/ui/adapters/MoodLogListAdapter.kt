package dev48n02m41.socialmediamoodtracker.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev48n02m41.socialmediamoodtracker.R
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity

class MoodLogListAdapter internal constructor(context: Context) : RecyclerView.Adapter<MoodLogListAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var moodLogsList = emptyList<DiaryEntryEntity>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewId: TextView = itemView.findViewById(R.id.textview_id)
        val textViewDate: TextView = itemView.findViewById(R.id.textview_date)
        val textViewBeforeRating: TextView = itemView.findViewById(R.id.textview_before)
        val textViewAfterRating: TextView = itemView.findViewById(R.id.textview_after)
        val textViewSocialNetwork: TextView = itemView.findViewById(R.id.textview_network)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current = moodLogsList[position]

        holder.textViewId.text = current.id.toString()
        holder.textViewDate.text = current.getFormattedDateVeryShort()
        holder.textViewBeforeRating.text = (current.beforeMoodRating?.plus(1)).toString() + "/5"
        holder.textViewAfterRating.text = (current.afterMoodRating?.plus(1)).toString() + "/5"
        holder.textViewSocialNetwork.text = current.socialNetworkChoice
    }

    internal fun setMoodLogsList(listIn: List<DiaryEntryEntity>) {
        this.moodLogsList = listIn
        notifyDataSetChanged()
    }

    override fun getItemCount() = moodLogsList.size
}