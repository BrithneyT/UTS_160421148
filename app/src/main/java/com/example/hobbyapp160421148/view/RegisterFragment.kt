package com.example.hobbyapp160421148.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.databinding.FragmentRegisterBinding
import com.example.hobbyapp160421148.model.AppDatabase
import com.example.hobbyapp160421148.model.User
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.user = UserData("", "", "", "", "")
        return binding.root
    }

    fun onRegisterClick() {
        val username = binding.user?.username ?: ""
        val firstName = binding.user?.firstName ?: ""
        val lastName = binding.user?.lastName ?: ""
        val email = binding.user?.email ?: ""
        val password = binding.user?.password ?: ""

        lifecycleScope.launch {
            register(username, firstName, lastName, email, password)
        }
    }

    fun onBackToLoginClick() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private suspend fun register(username: String, firstName: String, lastName: String, email: String, password: String) {
        val db = AppDatabase.getDatabase(requireContext())
        val userDao = db.userDao()

        val user = User(username = username, firstName = firstName, lastName = lastName, email = email, password = password)
        userDao.insertUser(user)

        Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    data class UserData(
        var username: String,
        var firstName: String,
        var lastName: String,
        var email: String,
        var password: String
    )
}