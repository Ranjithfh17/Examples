package com.fh.examples.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fh.examples.R
import com.fh.examples.databinding.FragmentHomeBinding


class Home : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.banner.setOnClickListener {
            findNavController().navigate(R.id.banner2)
        }
    }
}