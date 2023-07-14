package broz.tito.usadebt.presentation.screens.custom_views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import broz.tito.usadebt.R
import kotlin.math.min
import kotlin.properties.Delegates

class CustomProgressBarArc(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context,attributeSet,defStyleAttr, defStyleRes) {

    var progressBarColor by Delegates.notNull<Int>()
    private var isLoading by Delegates.notNull<Boolean>()
    var arcPercentage by Delegates.notNull<Int>()
    var progressBarWidth by Delegates.notNull<Int>()

    private var progressRadius by Delegates.notNull<Int>()

    private var progressRect = RectF()

    private lateinit var progressBarPaint : Paint

    constructor(context: Context,attributeSet: AttributeSet?, defStyleAttr: Int) : this(context, attributeSet,defStyleAttr,0)
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context,null)

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        }
        else {
            initDefaultAttributes()
        }
        initPaints()
        initRect()
    }


    private fun initAttributes(attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomProgressBarArc,defStyleAttr, defStyleRes)
        progressBarColor = typedArray.getColor(R.styleable.CustomProgressBarArc_progressBarColor,
            DEFAULT_PROGRESS_BAR_COLOR)
        isLoading = typedArray.getBoolean(R.styleable.CustomProgressBarArc_isLoading,
            DEFAULT_IS_LOADING)
        arcPercentage = typedArray.getInt(R.styleable.CustomProgressBarArc_arcPercentage,
            DEFAULT_ARC_PERCENTAGE)
        progressBarWidth = typedArray.getInt(R.styleable.CustomProgressBarArc_progressBarWidth,
            DEFAULT_PROGRESS_BAR_WIDTH)
        progressRadius = min(width - paddingLeft - paddingRight,height - paddingTop - paddingBottom)/2
        typedArray.recycle()
    }

    private fun initDefaultAttributes() {
        progressBarColor = DEFAULT_PROGRESS_BAR_COLOR
        isLoading = DEFAULT_IS_LOADING
        arcPercentage = DEFAULT_ARC_PERCENTAGE
        progressBarWidth = DEFAULT_PROGRESS_BAR_WIDTH
        progressRadius = min(width - paddingLeft - paddingRight,height - paddingTop - paddingBottom)/2
    }

    private fun initPaints() {
        progressBarPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        progressBarPaint.color = progressBarColor
        progressBarPaint.style = Paint.Style.STROKE
        progressBarPaint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,progressBarWidth.toFloat(),resources.displayMetrics)
    }

    private fun initRect() {
        progressRect.left = paddingLeft + (width - paddingLeft - paddingRight).toFloat()/2f - progressRadius + 2*progressBarWidth/2
        progressRect.right = progressRect.left + 2*progressRadius - 4*progressBarWidth/2
        progressRect.top = paddingTop + (height - paddingTop - paddingBottom).toFloat()/2f - progressRadius + 2*progressBarWidth/2
        progressRect.bottom = progressRect.top + 2+progressRadius - 4*progressBarWidth/2
    }

    override fun onDraw(canvas: Canvas) {
        initPaints()
        canvas.drawArc(progressRect,0f,arcPercentage/100.toFloat()*360,false,progressBarPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateViewSize()
    }

    private fun updateViewSize() {
        val width = width - paddingLeft - paddingRight
        val height = height - paddingTop - paddingBottom
        progressRadius = min(width,height)/2
        progressRect.top = paddingTop + (height - paddingTop - paddingBottom).toFloat()/2f - progressRadius + 2*progressBarWidth/2
        progressRect.bottom = progressRect.top + progressRadius*2 - 4*progressBarWidth/2
        progressRect.left = paddingLeft + (width - paddingLeft - paddingRight).toFloat()/2f - progressRadius + 2*progressBarWidth/2
        progressRect.right = progressRect.left + progressRadius*2 - 4*progressBarWidth/2
        invalidate()
    }

    companion object {
        const val DEFAULT_PROGRESS_BAR_COLOR = Color.BLUE
        const val DEFAULT_IS_LOADING = true
        const val DEFAULT_ARC_PERCENTAGE = 75
        const val DEFAULT_PROGRESS_BAR_WIDTH = 3
    }
}