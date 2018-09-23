package com.demo.architecturecomponentstalk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.architecturecomponentstalk.databinding.EventRowBinding
import com.demo.architecturecomponentstalk.db.entity.MeetupEvent

object EVENT_DIFF_CALLBACK : DiffUtil.ItemCallback<MeetupEvent>() {
    override fun areItemsTheSame(oldItem: MeetupEvent, newItem: MeetupEvent): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MeetupEvent, newItem: MeetupEvent): Boolean {
        return oldItem == newItem
    }
}

class EventsAdapter : ListAdapter<MeetupEvent, EventsAdapter.ViewHolder>(EVENT_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventRowBinding.inflate(LayoutInflater.from(parent.context), parent,
                false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = getItem(position)
        holder.apply {
            bind(createOnClickListener(event.id), event)
            itemView.tag = event;
        }
    }

    private fun createOnClickListener(eventId: String): View.OnClickListener {
        return View.OnClickListener {
            // TODO navigation to details
        }
    }

    class ViewHolder(private val binding: EventRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: MeetupEvent) {
            binding.apply {
                clickListener = listener
                event = item
            }
        }
    }

}