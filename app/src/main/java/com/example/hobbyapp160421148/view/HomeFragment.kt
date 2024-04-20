package com.example.hobbyapp160421148.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.databinding.FragmentHomeBinding
import com.example.hobbyapp160421148.viewmodel.StoryViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var storyViewModel: StoryViewModel
    private lateinit var storyListAdapter: StoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initStoryList()
        observeStoryList()
        setupClickListener()
    }

    private fun initViewModel() {
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
    }

    private fun initStoryList() {
        storyListAdapter = StoryListAdapter()
        binding.lstStory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = storyListAdapter
        }
    }

    private fun observeStoryList() {
        storyViewModel.storyList.observe(viewLifecycleOwner) { newsList ->
            storyListAdapter.submitList(newsList)
        }
    }

    private fun setupClickListener() {
        storyListAdapter.setOnItemClickListener { news ->
            val bundle = Bundle().apply {
                putParcelable("news", news)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailBeritaFragment, bundle)
        }
//        binding.btnProfil.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_profilFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
