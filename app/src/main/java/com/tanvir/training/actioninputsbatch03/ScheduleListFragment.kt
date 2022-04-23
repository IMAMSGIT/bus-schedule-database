package com.tanvir.training.actioninputsbatch03

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanvir.training.actioninputsbatch03.databinding.FragmentScheduleListBinding

class ScheduleListFragment : Fragment() {
    private val viewModel: ScheduleViewModel by activityViewModels()
    private lateinit var binding: FragmentScheduleListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleListBinding
            .inflate(inflater, container, false)
        val adapter = ScheduleAdapter()
        binding.scheduleRV.layoutManager = LinearLayoutManager(requireActivity())
        binding.scheduleRV.adapter = adapter
        viewModel.getAllSchedule().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_scheduleListFragment_to_newScheduleFragment)
        }
        return binding.root
    }

}