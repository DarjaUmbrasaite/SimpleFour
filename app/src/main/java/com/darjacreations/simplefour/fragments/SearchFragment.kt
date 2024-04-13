package com.darjacreations.simplefour.fragments

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.darjacreations.simplefour.activities.SearchResultsActivity
import com.darjacreations.simplefour.databinding.FragmentSearchBinding
import com.darjacreations.simplefour.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    companion object {
        const val SEARCH_LIST = "com.darjacreations.simplefour.fragments.searchList"
    }

    private lateinit var searchvm: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var ing: List<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        observeIngredientsLiveData()
        searchvm.getIngredients()
        setupSearchButtonListener()

        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupIngredientAutocomplete(autoCompleteTextView: AutoCompleteTextView, prevCompleteTextView: AutoCompleteTextView?, nextCompleteTextView: AutoCompleteTextView?) {
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, this.ing)
        autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold=0
        }
        autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        autoCompleteTextView.setOnTouchListener { v, event ->
            if ((prevCompleteTextView == null) || (prevCompleteTextView.text.toString() in ing)) {
                false//false: do not consume the event - let it proceed
            } else {
                if (event.action == MotionEvent.ACTION_UP) {
                    Toast.makeText(context, "Please enter a valid ingredient in the previous box before continuing.", Toast.LENGTH_LONG).show()
                }
                true
            }
        }
    }

//    private fun updateIngredientAutoComplete(autoCompleteTextView: AutoCompleteTextView) {
//
//        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, this.ing)
//        autoCompleteTextView.apply {
//            setAdapter(adapter)
//            threshold = 0
//        }
//    }

    private fun observeIngredientsLiveData() {
        searchvm.observeIngredientListLiveData().observe(viewLifecycleOwner
        ) { ingredientList ->
            val ingredientsList: List<String> = ingredientList.map { it.strIngredient }
            this.ing = ingredientsList
            setupIngredientAutocomplete(binding.ingredient1, null, binding.ingredient2)
            setupIngredientAutocomplete(binding.ingredient2, binding.ingredient1, binding.ingredient3)
            setupIngredientAutocomplete(binding.ingredient3, binding.ingredient2, binding.ingredient4)
            setupIngredientAutocomplete(binding.ingredient4, binding.ingredient3, null)
        }
    }

    private fun setupSearchButtonListener(){
        binding.searchButton.setOnClickListener {
            val ingredients = listOf(
                binding.ingredient1.text.toString().trim(),
                binding.ingredient2.text.toString().trim(),
                binding.ingredient3.text.toString().trim(),
                binding.ingredient4.text.toString().trim()
            ).filter { it.isNotEmpty() } // Only use non-empty strings

            if (ingredients.isNotEmpty()) {
                val invalidButNotEmpty = ingredients.filter {it.isNotEmpty() && (it !in this.ing)}
                if (invalidButNotEmpty.isNotEmpty()) {
                    val invalids = invalidButNotEmpty.joinToString(",")
                    Toast.makeText(context, "Invalid Ingredients: $invalids", Toast.LENGTH_SHORT).show()
                }
                val validIngredient = ArrayList(ingredients.filter {it in this.ing})

                // Search for recipes with ingredient list
                val intent = Intent(activity, SearchResultsActivity::class.java)
                intent.putStringArrayListExtra(SEARCH_LIST, validIngredient)
                startActivity(intent)

            } else {
                // Ensure at least one ingredient is entered
                Toast.makeText(context, "Please enter at least one ingredient.", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchvm = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

}