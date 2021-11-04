package com.example.marvelapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.marvelapp.databinding.CustomDetailsFragmentBinding

class CustomDetailsFragment @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = CustomDetailsFragmentBinding
        .inflate(LayoutInflater.from(context), this, true)

}