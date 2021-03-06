package com.example.final_project.tripplane

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.final_project.R
import com.example.final_project.databinding.FragmentEditPlanBinding
import com.example.final_project.planmodel.TripViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EditPlanFragment : Fragment() {
    private var _binding: FragmentEditPlanBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: TripViewModel by activityViewModels()
    var pos: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            editPlanFragment = this@EditPlanFragment
        }


        arguments?.let {
            pos = it.getInt("itemPos")
        }

        sharedViewModel.displayInformation()

//        sharedViewModel.displayInformation(pos)
        showIfComplete()
        showIsPast()


    }

    // if date of plant past change text
    fun showIsPast() {
        sharedViewModel.isPast.observe(viewLifecycleOwner, {

            if (it) {
                binding.pastComing.setTextColor(Color.parseColor("#E6392D"))
                binding.pastComing.text = getString(R.string.past)
            }
        })
    }

    private fun showIfComplete() {
        sharedViewModel.isComplete.observe(viewLifecycleOwner, {
            if (it) {
                binding.iconDone.setImageResource(R.drawable.plan_check)
            }
        })
    }

    // after user insert his change, this function will call to update values in dataSet
    fun updateTask() {
        sharedViewModel.updatedTripInfo()
        findNavController().navigate(R.id.action_editPlanFragment_to_planFragment)

    }

    fun backWithOutUpdate() {
        findNavController().navigate(R.id.action_editPlanFragment_to_planFragment)
    }

    //Dialog to confirm delete plan
    fun showConfirmDeletionDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delet_plan))
            .setMessage(getString(R.string.dialog_message)).setCancelable(false)
            .setNegativeButton(getString(R.string.confirm)) { _, _ ->
                deleteTask()
            }
            .setPositiveButton(getString(R.string.cancel)) { _, _ ->
                findNavController().navigate(R.id.action_editPlanFragment_to_planFragment)
            }
            .show()
    }

    // after user click delete, the plan will delete from List then user navigate to planFragment
    fun deleteTask() {
        sharedViewModel.removeTask()
        findNavController().navigate(R.id.action_editPlanFragment_to_planFragment)
    }

    // show date Dialog to add due date for plan, this value is type of  Long so we send it to another function
    fun dateDialog() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()
        picker.show(requireFragmentManager(), picker.toString())

        picker.addOnNegativeButtonClickListener {
        }
        picker.addOnPositiveButtonClickListener {
            sharedViewModel.formatDueDate(it)
        }
    }

}




