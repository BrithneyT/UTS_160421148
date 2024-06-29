package com.example.hobbyapp160421148.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hobbyapp160421148.databinding.FragmentStoryDetailBinding
import com.example.hobbyapp160421148.model.Story

class StoryDetailFragment : Fragment() {

    private var _binding: FragmentStoryDetailBinding? = null
    private val binding get() = _binding!!

    private val args: StoryDetailFragmentArgs by navArgs()
    private lateinit var story: Story

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        story = args.story
        setupUI()
    }

    private fun setupUI() {
        binding.story = story
        binding.handler = ClickHandler()

        Glide.with(requireContext())
            .load(story.imageUrl)
            .into(binding.showImage)

        binding.btnPrevPage.visibility = View.GONE
        binding.btnNextPage.visibility = View.GONE
    }

    inner class ClickHandler {
        fun onPrevClick() {
            // Handle previous page click
        }

        fun onNextClick() {
            // Handle next page click
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}