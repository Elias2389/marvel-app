package com.ae.marvelappication.common.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ae.marvelappication.databinding.MarvelAppEmptyStateViewBinding
import com.ae.marvelappication.util.hide
import com.ae.marvelappication.util.show

/**
 * Component to show empty state
 */
class EmptyState : ConstraintLayout {

    private var binding: MarvelAppEmptyStateViewBinding = MarvelAppEmptyStateViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * Set message of empty state
     * @param message about empty state
     */
    fun setMessage(message: String) {
        binding.textviewEmptyStateMessage.text = message
    }

    /**
     * Method to show empty state
     */
    fun showEmptyState() {
        binding.shimmerLayoutContainer.hide()
        binding.textviewEmptyStateMessage.show()
    }

    /**
     * Method to reload empty state
     */
    fun loadingEmptyState() {
        binding.shimmerLayoutContainer.show()
        binding.textviewEmptyStateMessage.hide()
    }

    /**
     * Method to hide empty state
     */
    fun hideEmptyState() {
        binding.shimmerLayoutContainer.stopShimmer()
        binding.shimmerLayoutContainer.hide()
        binding.textviewEmptyStateMessage.hide()
    }
}
