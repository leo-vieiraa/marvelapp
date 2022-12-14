package com.example.custom_layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.custom_layout.databinding.CustomDetailsFragmentBinding

class CustomDetailsFragment @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = CustomDetailsFragmentBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun setImage(context: Context, path: String) {

        Glide.with(context)
            .load(path)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivDetailsItemPhoto)

    }

    fun setData(name: String, description: String) {

        binding.tvDetailsItemName.text = name
        binding.tvDetailsItemDescription. text = description

    }

}