package com.example.final_project.tripplane

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.final_project.R
import com.example.final_project.databinding.FragmentAddPlanBinding
import com.example.final_project.planmodel.TripViewModel
import com.google.android.material.datepicker.MaterialDatePicker


class AddPlanFragment : Fragment() {
    private var _binding: FragmentAddPlanBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TripViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            addPlanFragment = this@AddPlanFragment
        }
    }

    // add new plan to list
    fun saveNewTask() {
        sharedViewModel.addTaskToList()
        findNavController().navigate(R.id.action_addPlanFragment_to_planFragment)

    }

    // show date Dialog to add due date for plan, this value is type of  Long so we send it to another function
    fun dateDialog() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(requireFragmentManager(), picker.toString())

        picker.addOnNegativeButtonClickListener {
        }
        picker.addOnPositiveButtonClickListener {
            sharedViewModel.formatDate(it)
        }

    }


}
