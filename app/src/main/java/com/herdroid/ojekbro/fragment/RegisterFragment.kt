package com.herdroid.ojekbro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.herdroid.ojekbro.R
import com.herdroid.ojekbro.databinding.FragmentRegisterBinding
import com.herdroid.ojekbro.network.api.ApiService
import com.herdroid.ojekbro.network.repository.OjekBroRepository
import com.herdroid.ojekbro.utils.Resource
import com.herdroid.ojekbro.utils.visible
import com.herdroid.ojekbro.viewmodel.OjekBroViewModel


class RegisterFragment : BaseFragment<OjekBroViewModel, FragmentRegisterBinding, OjekBroRepository>(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.registerResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(it is Resource.loading)
            when (it) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.loginFragment)
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()

                }

                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Somthing Went Wrong", Toast.LENGTH_SHORT)
                        .show()

                }

            }
        })

        binding.tvNextLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)

        }
        binding.btnRegister.setOnClickListener {
            signup()
        }
    }


    private fun signup(){
        val name = binding.nameRegister.text.toString().trim()
        val email = binding.emailRegister.text.toString().trim()
        val password = binding.passwordRegister.text.toString().trim()
        viewModel.register(name,email,password)

    }


    override fun getViewModal(): Class<OjekBroViewModel> {
        return OjekBroViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterBinding {
       return  FragmentRegisterBinding.inflate(inflater, container, false)
    }

    override fun getFragmentRepository(): OjekBroRepository {
        return  OjekBroRepository(apiClient.getApi(ApiService::class.java), userPreferences)
    }

}