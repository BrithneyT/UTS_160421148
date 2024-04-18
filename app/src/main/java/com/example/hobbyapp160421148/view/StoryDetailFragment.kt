package com.example.hobbyapp160421148.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hobbyapp160421148.databinding.FragmentStoryDetailBinding
import com.example.hobbyapp160421148.model.Story

class StoryDetailFragment : Fragment() {

    private var _binding: FragmentStoryDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var story: Story
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        story = arguments?.getParcelable("news") ?: Story("", "", "", "",emptyList())

        binding.showTitle.text = story.title
        binding.showAuthor.text = story.author

        binding.btnPrevPage.setOnClickListener {
            if (currentPage > 0) {
                currentPage--
                updateContent()
            }
        }
        Glide.with(requireContext())
            .load(story.imageUrl)
            .into(binding.showImage)

        binding.btnNextPage.setOnClickListener {
            if (currentPage < story.content.size - 1) {
                currentPage++
                updateContent()
            }
        }

        updateContent()
    }

    private fun updateContent() {
        if (story.content.isNotEmpty()) {
            binding.showContent.text = story.content[currentPage]
            binding.btnPrevPage.isEnabled = currentPage > 0
            binding.btnNextPage.isEnabled = currentPage < story.content.size - 1
        } else {
            binding.showContent.text = "None"
            binding.btnPrevPage.isEnabled = false
            binding.btnNextPage.isEnabled = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}