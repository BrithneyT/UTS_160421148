package com.example.hobbyapp160421148.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.api.ApiService
import com.example.hobbyapp160421148.api.UserProfileResponse
import com.example.hobbyapp160421148.databinding.FragmentProfilBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfilFragment : Fragment() {
    private var _binding: FragmentProfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val firstName = binding.txtNamaDepan.text.toString()
            val lastName = binding.txtNamaBelakang.text.toString()
            val password = binding.txtPassword.text.toString()

            updateProfile(firstName, lastName, password)
        }

        binding.btnLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profilFragment_to_loginFragment)
        }
    }


    private fun updateProfile(firstName: String, lastName: String, password: String) {
        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.3/anmp/uts/") // Base URL of your API
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val userId = getUserId()
        val call = apiService.updateProfile(userId, firstName, lastName, password)
        call.enqueue(object : Callback<UserProfileResponse> {
            override fun onResponse(
                call: Call<UserProfileResponse>,
                response: Response<UserProfileResponse>
            ) {
                if (response.isSuccessful) {
                    val userProfileResponse = response.body()
                    if (userProfileResponse?.status == "success") {
                        Toast.makeText(
                            requireContext(),
                            "Profile updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            userProfileResponse?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Update profile failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                Toast.makeText(
                    requireContext(),
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun getUserId(): Int {
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("userId", -1) // Return -1 if user ID not found
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
