package broz.tito.usadebt.presentation.screens.custom_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import broz.tito.usadebt.R
import  broz.tito.usadebt.databinding.ImageProgressBarBinding



class ImageProgressBarView(context: Context, attrs : AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding : ImageProgressBarBinding

    var isLoading = false
    set(value) {
        field = value
        if (field) {
            binding.customProgressbar.visibility = View.VISIBLE
            val an = RotateAnimation(
                0.5f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            an.duration = 700
            an.interpolator = LinearInterpolator()
            an.repeatMode = Animation.ABSOLUTE
            an.repeatCount = -1
            binding.customProgressbar.startAnimation(an)
            invalidate()
        }else {
            binding.customProgressbar.clearAnimation()
            binding.customProgressbar.visibility = View.GONE
            invalidate()
        }
    }

    var imageResourceId : Int = 0


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context) : this(context,null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.image_progress_bar,this,true)
        binding = ImageProgressBarBinding.bind(this)
        initAttrs(attrs, defStyleAttr, defStyleRes)
    }


    private fun initAttrs(attrs : AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.ImageProgressBarView, defStyleAttr,defStyleRes)
        with(binding) {
            customProgressbar.progressBarColor = typedArray.getColor(R.styleable.ImageProgressBarView_imageProgressBarColor,
                CustomProgressBarArc.DEFAULT_PROGRESS_BAR_COLOR
            )
            isLoading = typedArray.getBoolean(R.styleable.ImageProgressBarView_imageIsLoading,
                CustomProgressBarArc.DEFAULT_IS_LOADING)
            customProgressbar.arcPercentage = typedArray.getInt(R.styleable.ImageProgressBarView_imageArcPercentage,
                CustomProgressBarArc.DEFAULT_ARC_PERCENTAGE)
            customProgressbar.progressBarWidth = typedArray.getInt(R.styleable.ImageProgressBarView_imageProgressBarWidth,
                CustomProgressBarArc.DEFAULT_PROGRESS_BAR_WIDTH
            )
            customProgressbar.invalidate()
            imageResourceId = typedArray.getResourceId(R.styleable.ImageProgressBarView_imageSource,0)
            shapeableImageview.setBackgroundResource(imageResourceId)
        }
        typedArray.recycle()
    }

}