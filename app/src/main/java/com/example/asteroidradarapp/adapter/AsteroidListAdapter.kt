package com.example.asteroidradarapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradarapp.databinding.FragmentAsteroidListDetailsBinding
import com.example.asteroidradarapp.domain.AsteroidData

class AsteroidListAdapter(val clickListener: AsteroidListListener) : ListAdapter<AsteroidData,
        AsteroidListAdapter.ViewHolder>(AsteroidListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FragmentAsteroidListDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: AsteroidListListener, item: AsteroidData) {
            binding.asteroidData = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    FragmentAsteroidListDetailsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AsteroidListDiffCallback : DiffUtil.ItemCallback<AsteroidData>() {
    override fun areItemsTheSame(oldItem: AsteroidData, newItem: AsteroidData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsteroidData, newItem: AsteroidData): Boolean {
        return oldItem == newItem
    }
}

class AsteroidListListener(val clickListener: (shoeData: AsteroidData) -> Unit) {
    fun onClick(shoeData: AsteroidData) = clickListener(shoeData)
}