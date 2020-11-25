package com.fahmi.shoestore.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.fahmi.shoestore.R
import com.fahmi.shoestore.SharedPreference
import com.fahmi.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var pref : SharedPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        pref = SharedPreference(requireContext())

        val lastEmail = pref.getValueString("LAST_USER")
        val lastPassword = pref.getValueString("LAST_PASS")

        binding.loginBtn.setOnClickListener{ v: View ->

            binding.apply{
                if(email.text.toString().isEmpty()){
                    email.error = "Username Must be Filled"
                }

                if(password.text.toString().isEmpty()){
                    password.error = "Password Must be Filled"
                }

                // check if the user has logged in before matching with SharedPreferences
                if(email.text.toString() == lastEmail && password.text.toString() == lastPassword){
                    pref.save("USER", email.text.toString())
                    pref.save("PASS", password.text.toString())
                    pref.submit()
                    v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

                } else {
                    Toast.makeText(requireContext(), getString(R.string.wrong_email_or_password),
                            Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.registerBtn.setOnClickListener{v : View ->
            binding.apply {
                if(email.text.toString().isEmpty()){
                    binding.email.error = "Username Must be Filled"
                }

                if(password.text.toString().isEmpty()){
                    binding.password.error = "Password Must be Filled"
                }

                if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                    pref.save("USER", email.text.toString())
                    pref.save("PASS", password.text.toString())
                    pref.submit()
                    v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                }
            }

        }

        return binding.root
    }

}

