package com.example.final_project.overview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.final_project.R
import com.example.final_project.data.CitiesItem
import com.example.final_project.databinding.FragmentOverviewDetailsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener


class Overview_detailsFragment : Fragment() {
    var binding : FragmentOverviewDetailsBinding? = null
    private val viewModel: CityViewModel by activityViewModels()




    private lateinit var param: String // before start
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString("id")!!

        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentOverviewDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = viewModel
        binding?.overViewDetailFragment = this@Overview_detailsFragment
     viewModel.getCitydetail(param)
        //viewModel.getCityList()
    }




    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Overview_detailsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}