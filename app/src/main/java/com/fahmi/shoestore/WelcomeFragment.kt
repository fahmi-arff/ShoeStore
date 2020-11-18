package com.fahmi.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fahmi.shoestore.databinding.FragmentLoginBinding
import com.fahmi.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)
        binding.welcomeBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_instructionFragment)
        )
        return binding.root
    }
}