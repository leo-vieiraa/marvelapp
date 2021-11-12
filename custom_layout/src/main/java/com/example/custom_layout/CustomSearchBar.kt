package com.example.custom_layout

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.custom_layout.databinding.CustomSearchBarBinding
import com.google.android.material.textfield.TextInputLayout

class CustomSearchBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextInputLayout(context, attrs) {

    private val binding = CustomSearchBarBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun setSearchListener(textWatcher: TextWatcher) {
        binding.etHomeListingHeroSearch.editText?.addTextChangedListener(textWatcher)
    }

}