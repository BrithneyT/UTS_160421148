package com.example.hobbyapp160421148.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.databinding.FragmentProfilBinding
import com.example.hobbyapp160421148.model.AppDatabase
import kotlinx.coroutines.launch

class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private var userId: Int = -1
    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val password = ObservableField<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUserData()
    }

    private fun loadUserData() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getInt("userId", -1)

        if (userId != -1) {
            lifecycleScope.launch {
                val db = AppDatabase.getDatabase(requireContext())
                val userDao = db.userDao()
                val currentUser = userDao.getUserById(userId) ?: return@launch
                firstName.set(currentUser.firstName)
                lastName.set(currentUser.lastName)
                password.set(currentUser.password)
            }
        }
    }

    fun onSaveClick() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val userDao = db.userDao()
            val currentUser = userDao.getUserById(userId) ?: return@launch

            val updatedUser = currentUser.copy(
                firstName = firstName.get() ?: "",
                lastName = lastName.get() ?: "",
                password = password.get() ?: ""
            )

            userDao.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
        }
    }

    fun onLogoutClick() {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("userId").apply()
        findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
    }

    fun onDeleteAccountClick() {
        lifecycleScope.launch {
            val db = AppDatabase.getDatabase(requireContext())
            val userDao = db.userDao()
            val currentUser = userDao.getUserById(userId) ?: return@launch
            userDao.deleteUser(currentUser)

            val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove("userId").apply()

            Toast.makeText(requireContext(), "Account deleted successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }
    }
}