package com.tanvir.training.actioninputsbatch03

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tanvir.training.actioninputsbatch03.customdialogs.DatePickerFragment
import com.tanvir.training.actioninputsbatch03.customdialogs.TimePickerFragment
import com.tanvir.training.actioninputsbatch03.databinding.FragmentNewScheduleBinding

class NewScheduleFragment : Fragment() {
    private val viewModel: ScheduleViewModel by activityViewModels()
    private lateinit var binding: FragmentNewScheduleBinding
    private var from = "Dhaka"
    private var to = "Dhaka"
    private var busType = busTypeEconomy
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewScheduleBinding.inflate(inflater, container, false)
        initSpinner()
        initBusTypeRadioGroup()
        binding.dateBtn.setOnClickListener {
            DatePickerFragment{
                binding.showDateTV.text = it
            }.show(childFragmentManager, null)
        }
        binding.timeBtn.setOnClickListener {
            TimePickerFragment {
                binding.showTimeTV.text = it
            }.show(childFragmentManager, null)
        }
        binding.saveBtn.setOnClickListener {
            saveInfo()
        }
        return binding.root
    }

    private fun saveInfo() {
        val name = binding.busNameInputET.text.toString()
        val date = binding.showDateTV.text.toString()
        val time = binding.showTimeTV.text.toString()
        // TODO: validate name, date, time
        if (from == to) {
            Toast.makeText(
                requireActivity(),
                "Origin and destination cannot be same",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val schedule = BusSchedule(
            id = System.currentTimeMillis(),
            busName = name,
            busType = busType,
            departureDate = date,
            departureTime = time,
            from = from,
            to = to
        )
        viewModel.addSchedule(schedule)
        findNavController().popBackStack()
        Log.d("NewScheduleFragment", "saveInfo: $schedule")
    }

    private fun initBusTypeRadioGroup() {
        binding.busTypeRG.setOnCheckedChangeListener { radioGroup, i ->
            val rb: RadioButton = radioGroup.findViewById(i)
            busType = rb.text.toString()
        }
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            cityList
        )
        binding.citySpinnerFrom.adapter = adapter
        binding.citySpinnerTo.adapter = adapter

        binding.citySpinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                from = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        binding.citySpinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                to = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}