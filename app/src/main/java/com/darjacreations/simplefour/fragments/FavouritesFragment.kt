package com.darjacreations.simplefour.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.InvalidationTracker
import com.darjacreations.simplefour.R
import com.darjacreations.simplefour.activities.MainActivity
import com.darjacreations.simplefour.adapters.FavouritesMealsAdapter
import com.darjacreations.simplefour.databinding.FragmentFavouritesBinding
import com.darjacreations.simplefour.viewModel.HomeViewModel


class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var favouritesAdapter: FavouritesMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeFavourites()
    }

    private fun prepareRecyclerView() {
        favouritesAdapter = FavouritesMealsAdapter()
        binding.rviewFavourites.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = favouritesAdapter
        }
    }

    private fun observeFavourites() {
        viewModel.observeFavouritesMealsLiveData().observe(viewLifecycleOwner, Observer { meals->
          favouritesAdapter.differ.submitList(meals)

            })
    }


}