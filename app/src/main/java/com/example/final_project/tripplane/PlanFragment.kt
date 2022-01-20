package com.example.final_project.tripplane

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.R
import com.example.final_project.data.DataSource
import com.example.final_project.databinding.FragmentPlanBinding
import com.example.final_project.planmodel.Trip
import com.example.final_project.planmodel.TripViewModel
import com.example.final_project.tripadapter.Adapter
import java.text.SimpleDateFormat


class PlanFragment : Fragment() {


    private val sharedViewModel: TripViewModel by activityViewModels()
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    lateinit var data: List<Trip>
    lateinit var sortedList: List<Trip>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = DataSource.DataSource().loadData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sortIcon.setOnClickListener { showSortPopupMenu(binding.sortIcon) }
        binding.filterIcon.setOnClickListener { showFilterPopupMenu(binding.filterIcon) }

        sortedList = data

        binding.apply {
            binding.viewModel = sharedViewModel
            binding.lifecycleOwner = viewLifecycleOwner
            binding.planFragment = this@PlanFragment
        }

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = Adapter(this.requireContext(), sortedList)


    }

    // Add new plan to List
    fun addNewTrip() {
        findNavController().navigate(R.id.action_planFragment_to_addPlanFragment)
        sharedViewModel.getEmptyFields()
    }

    // create popupMenu for Sort
    @SuppressLint("SetTextI18n")
    private fun showSortPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.sort_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {

                R.id.sort_alpha -> {
                    sortedList = data.sortedBy { it.title.toLowerCase() }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)

                }
                R.id.sort_due -> {
                    sortedList = data.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it.dueDate) }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }
                R.id.sort_cration -> {
                    sortedList =
                        data.sortedBy { SimpleDateFormat("yyyy-MM-dd").parse(it.creationDate!!) }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }
                R.id.un_sorted -> {
                    sortedList = data
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }

            }

            true
        })

        popup.show()
    }
// create popupMenu for Filter

    private fun showFilterPopupMenu(view: View) {
        val popup = PopupMenu(this.requireContext(), view)
        popup.inflate(R.menu.filter_menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.sort_iscomplate -> {
                    sortedList = data.filter { it -> it.isComplete == true }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }

                R.id.is_coming -> {
                    sortedList = data.filter { it -> it.isPast == false }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }
                R.id.sort_isPast -> {
                    sortedList = data.filter { it -> it.isPast == true }
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }

                R.id.un_sorted -> {
                    sortedList = data
                    recyclerView.adapter = Adapter(this.requireContext(), sortedList)
                }

            }

            true
        })

        popup.show()
    }

    }