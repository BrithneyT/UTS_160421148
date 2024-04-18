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

        binding.btnRegister.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val firstName = binding.txtNamaDepan.text.toString()
            val lastName = binding.txtNamaBelakang.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()

//            val gson = GsonBuilder()
//                .setLenient()
//                .create()


            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.3/anmp/uts/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.register(username, firstName, lastName, email, password)
            call.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        if (registerResponse?.status == "success") {
                            Toast.makeText(
                                requireContext(),
                                "Registration successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                registerResponse?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}