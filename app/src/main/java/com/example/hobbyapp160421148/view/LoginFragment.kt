package com.example.hobbyapp160421148.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.databinding.FragmentLoginBinding
import com.example.hobbyapp160421148.model.AppDatabase
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.user = User("", "")
        return binding.root
    }

    fun onLoginClick() {
        val username = binding.user?.username ?: ""
        val password = binding.user?.password ?: ""

        lifecycleScope.launch {
            login(username, password)
        }
    }

    fun onRegisterClick() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private suspend fun login(username: String, password: String) {
        val db = AppDatabase.getDatabase(requireContext())
        val userDao = db.userDao()

        val user = userDao.getUser(username, password)
        if (user != null) {
            // Save user ID to SharedPreferences
            val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().putInt("userId", user.userId).apply()

            Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
        }
    }

    data class User(
        var username: String,
        var password: String
    )
}