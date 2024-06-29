package com.example.hobbyapp160421148.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hobbyapp160421148.databinding.FragmentStoryListAdapterBinding
import com.example.hobbyapp160421148.model.Story

class StoryListAdapter : ListAdapter<Story, StoryListAdapter.StoryViewHolder>(StoryDiffCallback()) {

    private var onItemClickListener: ((Story) -> Unit)? = null

    fun setOnItemClickListener(listener: (Story) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = FragmentStoryListAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
    }

    inner class StoryViewHolder(private val binding: FragmentStoryListAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(story: Story) {
            binding.story = story
            binding.clickListener = android.view.View.OnClickListener {
                onItemClickListener?.invoke(story)
            }

            // Load image using Glide
            Glide.with(binding.root.context)
                .load(story.imageUrl)
                .into(binding.showImage)

            binding.executePendingBindings()
        }
    }

    class StoryDiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }
}