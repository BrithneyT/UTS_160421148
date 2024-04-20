package com.example.hobbyapp160421148.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp160421148.R
import com.example.hobbyapp160421148.api.ApiService
import com.example.hobbyapp160421148.api.Db_Contract
import com.example.hobbyapp160421148.api.RegisterResponse
import com.example.hobbyapp160421148.databinding.FragmentRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.btnRegister.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val firstName = binding.txtNamaDepan.text.toString()
            val lastName = binding.txtNamaBelakang.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

            register(username, firstName, lastName, email, password)
        }
    }

    private fun register(username: String, firstName: String, lastName: String, email: String, password: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(Db_Contract.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.register(username, firstName, lastName, email, password)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                handleRegisterResponse(response)
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                handleError(t)
            }
        })
    }

    private fun handleRegisterResponse(response: Response<RegisterResponse>) {
        if (response.isSuccessful) {
            val registerResponse = response.body()
            if (registerResponse?.status == "success") {
                showToast("Registration successful")
                navigateToLoginFragment()
            } else {
                showToast(registerResponse?.message ?: "Unknown error occurred")
            }
        } else {
            showToast("Registration failed")
        }
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun handleError(t: Throwable) {
        showToast("Error: ${t.message}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
