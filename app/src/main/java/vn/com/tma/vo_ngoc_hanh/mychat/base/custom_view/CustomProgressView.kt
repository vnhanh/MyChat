package vn.com.tma.vo_ngoc_hanh.mychat.base.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import vn.com.tma.vo_ngoc_hanh.mychat.R

class CustomProgressView : LinearLayout {
    private var prgView:ProgressBar?=null
    private var msgView:TextView?=null
    var message:String?=""
    /*set(value) {
        this.message = value
        if (msgView != null) {
            msgView!!.setText(value?:"")
        }
    }*/
    var msgTextSize:Float = -1f
    var msgColor:Int = Color.BLACK
    var msgAlign:Int = Gravity.START
    var indicatorSize:Int = -1
    var indicatorAlignLeft:Boolean = true

    constructor(context:Context) : super(context)

    constructor(context: Context, attrs:AttributeSet) : super(context, attrs){

        context.theme.obtainStyledAttributes(attrs,R.styleable.CustomProgressView, 0, 0)
                .apply {
                    try{
                        if(hasValue(R.styleable.CustomProgressView_prg_view_message))
                            message = getString(R.styleable.CustomProgressView_prg_view_message)

                        if(hasValue(R.styleable.CustomProgressView_prg_view_text_size))
                            msgTextSize = getDimension(R.styleable.CustomProgressView_prg_view_text_size, -1f)

                        if(hasValue(R.styleable.CustomProgressView_prg_view_text_color))
                            msgColor = getColor(R.styleable.CustomProgressView_prg_view_text_color, Color.BLACK)

                        if(hasValue(R.styleable.CustomProgressView_prg_view_text_align))
                            msgAlign = getInt(R.styleable.CustomProgressView_prg_view_text_align, Gravity.START)

                        if(hasValue(R.styleable.CustomProgressView_prg_view_indicator_size))
                            indicatorSize = getDimensionPixelSize(R.styleable.CustomProgressView_prg_view_indicator_size, -1)

                        if(hasValue(R.styleable.CustomProgressView_prg_view_indicator_align_left))
                            indicatorAlignLeft = getBoolean(R.styleable.CustomProgressView_prg_view_indicator_align_left, true)
                    }finally {
                        recycle()
                    }
                }
        setup()
    }

    private fun setup() {
        val prg = setupIndicator()
        val msg = setupMessageView()
        when (indicatorAlignLeft) {
            true -> {
                addView(prg)
                setupMarginSecondView(msg)
                addView(msg)
            }

            else -> {
                addView(msg)
                setupMarginSecondView(prg)
                addView(prg)
            }
        }

        val padding = resources.getDimensionPixelSize(R.dimen.margin_normal)
        setPadding(padding,padding,padding,padding)
    }

    private fun setupMarginSecondView(view:View) {
        val lp = view.layoutParams as LinearLayout.LayoutParams
        lp.marginStart = context.resources.getDimensionPixelSize(R.dimen.margin_normal)
        lp.marginEnd = 0
    }

    private fun setupIndicator() : ProgressBar {
        val progressBar = ProgressBar(context)

        if (indicatorSize == -1) {
            indicatorSize = LinearLayout.LayoutParams.WRAP_CONTENT
        }

        val lp = LinearLayout.LayoutParams(indicatorSize, indicatorSize)
        lp.gravity = Gravity.CENTER_VERTICAL
        progressBar.layoutParams = lp

        return progressBar
    }

    private fun setupMessageView() : TextView {
        val msgView = TextView(context)
        val layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 1f
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        msgView.gravity = Gravity.LEFT
        msgView.layoutParams = layoutParams

        msgView.setText(message?:"")
        msgView.setTextColor(msgColor)

        if (msgTextSize <= 0) {
            msgTextSize = resources.getDimensionPixelSize(R.dimen.textSize_lb_small).toFloat()
        }

        msgView.setTextSize(TypedValue.COMPLEX_UNIT_PX, msgTextSize)

        return msgView
    }

    private constructor(builder:Builder) : this(builder.context){
        this.message = builder.message
        this.msgTextSize = builder.msgTextSize
        this.msgColor = builder.msgColor
        this.msgAlign = builder.msgAlign
        this.indicatorSize = builder.indicatorSize
        this.indicatorAlignLeft = builder.indicatorAlignLeft

        setup()
    }

    open class Builder(var context: Context){
        var message:String=""
        var msgTextSize:Float = -1f
        var msgColor:Int = Color.BLACK
        var msgAlign:Int = Gravity.START
        var indicatorSize:Int = -1
        var indicatorAlignLeft:Boolean = true

        fun _setMessage(msg:String) : Builder{
            message = msg
            return this
        }

        fun _setMessageTextSize(msgTextSize:Float) : Builder{
            this.msgTextSize = msgTextSize
            return this
        }

        fun _setMessageColor(msgColor:Int) : Builder{
            this.msgColor = msgColor
            return this
        }

        fun _setMessageAlign(msgAlign:Int) : Builder{
            this.msgAlign = msgAlign
            return this
        }

        fun _setIndicatorSize(indicatorSize:Int) : Builder{
            val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorSize.toFloat(), context.resources.displayMetrics)
            this.indicatorSize = size.toInt()
            return this
        }

        fun _setIndicatorAlignLeft(indicatorAlignLeft:Boolean) : Builder{
            this.indicatorAlignLeft = indicatorAlignLeft
            return this
        }

        fun create() : CustomProgressView{
            return CustomProgressView(this)
        }
    }
}