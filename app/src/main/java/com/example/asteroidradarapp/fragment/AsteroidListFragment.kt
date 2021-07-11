package com.example.asteroidradarapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.asteroidradarapp.adapter.AsteroidListAdapter
import com.example.asteroidradarapp.adapter.AsteroidListListener
import com.example.asteroidradarapp.databinding.FragmentAsteroidListBinding
import com.example.asteroidradarapp.viewmodel.AsteroidListViewModel

class AsteroidListFragment : Fragment() {
    private val viewModel: AsteroidListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAsteroidListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.nasaImage = viewModel
        val adapter = AsteroidListAdapter(AsteroidListListener { shoeData ->
            this.findNavController().navigate(
                AsteroidListFragmentDirections.actionAsteroidListFragmentToAsteroidDetailsFragment(
                    shoeData
                )
            )
        })
        binding.recyclerView.adapter = adapter

        viewModel.asteroidData.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it.toMutableList())
            }
        })
        return binding.root
    }
}