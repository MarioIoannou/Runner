package com.marioioannou.runner.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.marioioannou.runner.R
import com.marioioannou.runner.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info) {
    //private lateinit var binding: FragmentSetupBinding
    private var fragmentInfoBinding: FragmentInfoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInfoBinding.bind(view)
        fragmentInfoBinding = binding

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_infoFragment_to_runFragment)
        }
    }

    override fun onDestroy() {
        fragmentInfoBinding = null
        super.onDestroy()
    }

//    binding = FragmentSetupBinding.inflate(inflater, container, false)
//    ...
//    return binding.root
}