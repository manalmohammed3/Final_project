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
import com.example.final_project.databinding.FragmentShowPlanBinding
import com.example.final_project.planmodel.TripViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ShowPlanFragment : Fragment() {


    private var _binding: FragmentShowPlanBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: TripViewModel by activityViewModels()
    var taskIndex: Int = 0

    // receive Argument from from planFragment based on index of task in DatasetList to access task details
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            showPlanFragment = this@ShowPlanFragment
        }
        sharedViewModel.currentTaskPosition.value = taskIndex

        showIfComplete()
        showIsPast()
        sharedViewModel.displayInformation()
    }


    // when user click on the EDIT ICON, navigate to edit fragment and call function to display his plan details
    fun goToEditPlanPage() {
//    val action = ShowplanFragmentDirections.actionShowplanFragmentToEditFragment(planIndex)
        findNavController().navigate(R.id.action_showPlanFragment_to_editPlanFragment)
        sharedViewModel.displayInformation()

    }

    // delete plan from List
    fun deleteTask() {
        sharedViewModel.removeTask()
        findNavController().navigate(R.id.action_showPlanFragment_to_planFragment)
    }

    //Dialog to confirm delete plan
    fun showConfirmDeletionDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message)).setCancelable(false)
            .setNegativeButton(getString(R.string.confirm)) { _, _ ->
                deleteTask()
            }
            .setPositiveButton(getString(R.string.cancel)) { _, _ ->
                findNavController().navigate(R.id.action_showPlanFragment_to_planFragment)
            }
            .show()
    }

    // show tag if plan completed
    fun showIfComplete() {
        sharedViewModel.isComplete.observe(viewLifecycleOwner, {
            if (it) {
                binding.iconDone.setImageResource(R.drawable.plan_check)
            }
        })
    }

    // show tag if plan is past or coming
    fun showIsPast() {
        sharedViewModel.isPast.observe(viewLifecycleOwner, {

            if (it) {
                binding.pastComing.setTextColor(Color.parseColor("#E6392D"))
                binding.pastComing.text = getString(R.string.past)
            }
        })
    }

}






