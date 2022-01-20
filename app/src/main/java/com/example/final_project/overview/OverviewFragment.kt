package com.example.final_project.overview

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import com.example.final_project.CityAdapter

import com.example.final_project.R
import com.example.final_project.data.CitiesItem
import com.example.final_project.databinding.FragmentOverviewBinding
import com.google.firebase.database.*


class OverviewFragment : Fragment() {
    private val viewModel: CityViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setHasOptionsMenu(true)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        Toast.makeText(requireContext(), "fraqment ", Toast.LENGTH_SHORT).show()
//       make the the connection with the adapter
           binding.photosGrid.adapter = CityAdapter()



        binding.addNewPlan.setOnClickListener{
         findNavController().navigate(R.id.action_overviewFragment_to_planFragment)
        }

        return binding.root

    }
    private fun setCityBasedOnGener(menuItem: MenuItem?){
        if (menuItem == null) {return}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


