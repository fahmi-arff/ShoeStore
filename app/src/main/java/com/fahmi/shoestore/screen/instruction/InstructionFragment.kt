package com.fahmi.shoestore.screen.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fahmi.shoestore.R
import com.fahmi.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instruction, container, false)
        binding.instuctionBtn.setOnClickListener{v : View ->
            v.findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoesListFragment())
        }
        return binding.root
    }
}