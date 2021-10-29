package com.example.marvelapp.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.marvelapp.R
import com.example.marvelapp.databinding.CustomHeroCardBinding

class CustomHeroCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int=0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var heroImage: String? = null
    private var heroName: String? = null

    private val binding = CustomHeroCardBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var state: ProgressButtonState = ProgressButtonState.Normal
        set(value) {
            field = value
            refreshState()
        }

    init {
        setLayout(attrs)
        refreshState()
    }

    private fun setLayout(attrs: AttributeSet?) {

        attrs?.let { attributeSet ->
            val atributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.CustomHeroCard
            )

            setBackgroundResource(R.drawable.hero_card_background)

            val heroImageId = atributes.getResourceId(R.styleable.CustomHeroCard_hero_image, 0)
            if (heroImageId != 0) {
                heroImage = context.getString(heroImageId)
            }

            val heroNameId = atributes.getResourceId(R.styleable.CustomHeroCard_hero_name, 0)
            if (heroNameId != 0) {
                heroName = context.getString(heroNameId)
            }

            atributes.recycle()

        }

    }

    private fun refreshState() {
        isEnabled = state.isEnabled
        isClickable = state.isEnabled
        refreshDrawableState()

        binding.ivHomeListingHeroPhoto.run {
            isEnabled = state.isEnabled
            isClickable = state.isEnabled
        }

        binding.pbHomeListingHeroPhoto.visibility = state.progressVisibility

//        when (state) {
//            ProgressButtonState.Normal -> binding.ivHomeListingHeroPhoto.drawable = title
//            ProgressButtonState.Loading -> binding.textTitle.text = loadingTitle
//        }
    }

    fun setLoading() {
        state = ProgressButtonState.Loading
    }

    fun setNormal() {
        state = ProgressButtonState.Normal
    }

    sealed class ProgressButtonState(val isEnabled: Boolean, val progressVisibility: Int) {
        object Normal : ProgressButtonState(true, View.GONE)
        object Loading: ProgressButtonState(false, View.VISIBLE)
    }


}