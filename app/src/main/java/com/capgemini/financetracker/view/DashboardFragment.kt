package com.capgemini.financetracker.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.capgemini.financetracker.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDashboardBinding.inflate(layoutInflater)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentDashboardBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {

    }
}