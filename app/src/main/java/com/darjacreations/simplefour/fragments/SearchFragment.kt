package com.darjacreations.simplefour.fragments

import android.R
import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.darjacreations.simplefour.databinding.FragmentSearchBinding
import com.darjacreations.simplefour.viewModel.SearchViewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var searchvm: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var ing: List<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchvm.getIngredients()
        observeIngredientsLiveData()

        setupSearchButtonListener()

        return binding.root
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
//                performRecipeSearch(ingredients)
            } else {
                // Show error message or feedback about needing to enter ingredients
                Toast.makeText(context, "Please enter at least one ingredient.", Toast.LENGTH_SHORT).show()
            }
        }
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

//        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence, strt: Int, before: Int, count: Int) {
//                if (s.isEmpty()) {
//                    autoCompleteTextView.showDropDown()
//                } else {
//                    // Enable the next AutoCompleteTextView if this one is not empty
//                    if (autoCompleteTextView.text.toString() in ing) {
//                        prevCompleteTextView?.tag = "complete"
//                    }
//                    else {
//                        prevCompleteTextView?.tag = ""
//                    }
//                }
//            }
//
//            override fun afterTextChanged(s: Editable) {}
//        })

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

        // Setup listeners to enable next AutoCompleteTextView based on selections, etc.
    }

    private fun updateIngredientAutoComplete(autoCompleteTextView: AutoCompleteTextView) {



        val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, this.ing)
        autoCompleteTextView.apply {
            setAdapter(adapter)
            threshold = 0
        }
    }

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


    private fun getInitialIngredientList() {
       val ingredients = searchvm.observeIngredientListLiveData()
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchvm = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }


}