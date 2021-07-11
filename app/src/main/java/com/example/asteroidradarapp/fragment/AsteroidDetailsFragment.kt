package com.example.asteroidradarapp.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.asteroidradarapp.R
import com.example.asteroidradarapp.databinding.FragmentAsteroidDetailsBinding

class AsteroidDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAsteroidDetailsBinding.inflate(inflater)
        val arguments = AsteroidDetailsFragmentArgs.fromBundle(requireArguments()).asteroidData
        binding.lifecycleOwner = this
        binding.asteroidData = arguments
        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}