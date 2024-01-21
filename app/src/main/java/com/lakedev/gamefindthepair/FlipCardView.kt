package com.lakedev.gamefindthepair

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.INVISIBLE
import androidx.appcompat.widget.AppCompatImageView

class FlipCardView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : AppCompatImageView(context, attr) {
    private val backSideImageId = R.drawable.pngegg
    var frontSideImageId = 0

    fun openFlipCard() = this.setImageResource(frontSideImageId)

    fun closeFlipCard() = this.setImageResource(backSideImageId)

    fun hideFlipCard() {
        this.visibility = INVISIBLE
    }

    fun showFlipCard() {
        this.visibility = VISIBLE
    }
}