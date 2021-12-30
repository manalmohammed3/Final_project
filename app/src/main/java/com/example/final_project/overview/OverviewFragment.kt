package com.example.final_project.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.final_project.CityAdapter
import com.example.final_project.R
import com.example.final_project.databinding.FragmentOverviewBinding


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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
//       make the the connection with the adapter
        binding.photosGrid.adapter = CityAdapter()
        return binding.root
    }


    private fun setCityBasedOnGener(menuItem: MenuItem?){
        if (menuItem == null) {return}
    }




}


