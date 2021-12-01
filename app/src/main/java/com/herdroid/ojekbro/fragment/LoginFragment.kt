package com.herdroid.ojekbro.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.herdroid.ojekbro.R
import com.herdroid.ojekbro.databinding.FragmentLoginBinding
import com.herdroid.ojekbro.network.api.ApiService
import com.herdroid.ojekbro.network.repository.OjekBroRepository
import com.herdroid.ojekbro.ui.MainActivity
import com.herdroid.ojekbro.utils.*
import com.herdroid.ojekbro.viewmodel.OjekBroViewModel
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<OjekBroViewModel, FragmentLoginBinding, OjekBroRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnLogin.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, {
            binding.progressbar.visible(it is Resource.loading)

            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.data.token)
                        requireActivity().startNewActivity(MainActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.passwordLogin.addTextChangedListener {
            val email = binding.emailLogin.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }



        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvNextRegister.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
        }


    }


    private fun login(){
        val email = binding.emailLogin.text.toString().trim()
        val password = binding.passwordLogin.text.toString().trim()
        viewModel.login(email, password)
    }

    override fun getViewModal(): Class<OjekBroViewModel> {
        return  OjekBroViewModel::class.java
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return  FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun getFragmentRepository(): OjekBroRepository {
        return OjekBroRepository(apiClient.getApi(ApiService::class.java), userPreferences)
    }


}