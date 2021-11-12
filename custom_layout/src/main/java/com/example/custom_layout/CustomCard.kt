package com.example.custom_layout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.custom_layout.databinding.CustomCardBinding

class CustomCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int=0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding = CustomCardBinding
        .inflate(LayoutInflater.from(context), this, true)

    fun setImage(view: View, path: String) {

        Glide.with(view)
            .load(path)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivItemPhoto)

    }

    fun setName(name: String) {
        binding.tvItemName.text = name
    }

}