package com.fahmi.shoestore.screen.shoelist

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.fahmi.shoestore.R
import com.fahmi.shoestore.SharedPreference
import com.fahmi.shoestore.ShoesListViewModel
import com.fahmi.shoestore.databinding.FragmentShoesListBinding
import com.fahmi.shoestore.model.Shoe
import java.lang.StringBuilder

class ShoesListFragment : Fragment() {
    private lateinit var pref: SharedPreference
    private val viewModel: ShoesListViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pref = SharedPreference(requireContext())
        val binding: FragmentShoesListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes_list, container, false
        )

        binding.fab.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ShoesListFragmentDirections.actionShoesListFragmentToShoeDetailFragment())
        }
        setHasOptionsMenu(true)

        viewModel.shoesList.observe(viewLifecycleOwner) { shoeList ->
            for (shoe in shoeList) {
                binding.shoeListLayout.addView(createShoeTextView(shoe))
            }
        }

        return  binding.root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createShoeTextView(shoe : Shoe): TextView {
        val shoeTextView = TextView(requireActivity(), null, 0, R.style.list_textView)

        val strb = StringBuilder()
        strb.append("Name : ${shoe.name} \n")
        strb.append("Company : ${shoe.company} \n")
        strb.append("Size : ${shoe.size} \n")
        strb.append("Desc : ${shoe.description}")
        shoeTextView.text = strb.toString()
        return shoeTextView
    }

    // to create logout menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    // after logout, login state will copy to sharedPreferences with different key
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        pref.save("LAST_USER", pref.getValueString("USER").toString())
        pref.save("LAST_PASS", pref.getValueString("PASS").toString())
        pref.removeValue("USER")
        pref.removeValue("PASS")
        pref.submit()
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}