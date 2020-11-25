package com.fahmi.shoestore.screen.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.fahmi.shoestore.R
import com.fahmi.shoestore.ShoesListViewModel
import com.fahmi.shoestore.databinding.FragmentShoeDetailBinding
import com.fahmi.shoestore.model.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var addShoes : Shoe
    private val viewModel: ShoesListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )
        binding.shoeListViewModel = viewModel

        binding.cancelBtn.setOnClickListener { v : View ->
            viewModel.resetShoeForm()
            v.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment())
        }

        // to observe if all value is correct  on the all field
        viewModel.checkCorrectValue.observe(viewLifecycleOwner, { isCorrectValue ->
            if (isCorrectValue) {
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesListFragment())
                viewModel.afterCorrectValue()
            }
        })

        // observe if value still have empty field in the form
        viewModel.checkEmptyForm.observe(viewLifecycleOwner){isEmpty ->
            if(isEmpty){
                Toast.makeText(requireContext(), "Please fill all fields in the form", Toast.LENGTH_LONG).show()
            }
        }

        return  binding.root
    }
}