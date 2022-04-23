package com.tanvir.training.actioninputsbatch03

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanvir.training.actioninputsbatch03.databinding.ScheduleRowBinding

class ScheduleAdapter
    : ListAdapter<BusSchedule, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffUtil()) {

    class ScheduleViewHolder(val binding: ScheduleRowBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(busSchedule: BusSchedule) {
                binding.schedule = busSchedule
            }
        }

    class ScheduleDiffUtil : DiffUtil.ItemCallback<BusSchedule>() {
        override fun areItemsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BusSchedule, newItem: BusSchedule): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = getItem(position)
        holder.bind(schedule)
    }
}