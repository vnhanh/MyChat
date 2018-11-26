package vn.com.tma.vo_ngoc_hanh.mychat.base.custom_view

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

import vn.com.tma.vo_ngoc_hanh.mychat.R

class CustomSearchView : LinearLayout {
    lateinit var iconView:ImageView
    lateinit var inputView:EditText
    lateinit var closeIconView:ImageView

    private val MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT
    private val WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT

    private val MARGIN = context.resources.getDimensionPixelSize(R.dimen.margin_normal)

    constructor(context:Context) : super(context)

    constructor(context: Context, attrs:AttributeSet) : super(context, attrs){
        init()
    }

    private fun init() {
        initIconView()
        initInputView()
        initCloseIconView()
    }

    private fun initIconView() {
        iconView = ImageView(context)

        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        iconView.layoutParams = layoutParams

        iconView.setImageResource(R.drawable.ic_search_16_0)

        addView(iconView)
    }

    private fun initInputView() {
        inputView = EditText(context)

        val layoutParams = LinearLayout.LayoutParams(0, WRAP_CONTENT)
        layoutParams.weight = 1f
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        layoutParams.marginStart = MARGIN
        layoutParams.marginEnd = MARGIN
        inputView.layoutParams = layoutParams

        inputView.setHint(R.string.hint_input_custom_search_view)
        inputView.setBackgroundColor(Color.TRANSPARENT)

        addView(inputView)
    }

    private fun initCloseIconView() {
        closeIconView = ImageView(context)

        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        closeIconView.layoutParams = layoutParams

        closeIconView.setImageResource(R.drawable.ic_close_16_0)

        closeIconView.visibility = View.INVISIBLE

        addView(closeIconView)
    }

    fun setOnFocusListener(listener: OnOperateSearchViewListener){
        iconView.setOnClickListener{
            listener.onClickIconView(it)
        }
        closeIconView.setOnClickListener{
            listener.onClickCloseIconView(it)
        }
        inputView.setOnClickListener{
            listener.onClickInputView(it)
            Toast.makeText(it.context, "Clicked !", Toast.LENGTH_SHORT).show()
        }
        inputView.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                listener.onInputTextChanged(s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    fun removeOnFocusListener() {
        setOnClickListener(null)
    }

    open class OnOperateSearchViewListener(private val iconView:ImageView, private val inputView:EditText, private val closeIconView:ImageView){
        private var isFocusing = false

        fun onClickIconView(view: View) {
            if (isFocusing) {
                setSearchIconView()
                onRequestEscape()
            }else{
                setBackIconView()
                inputView.requestFocus()
            }
            isFocusing = !isFocusing
        }

        // On need to escape focus
        fun onRequestEscape() {

        }

        fun onClickCloseIconView(view: View?) {
            inputView.setText("")
            setSearchIconView()
            onRequestEscape()
        }

        fun onClickInputView(view: View?) {
            setBackIconView()
        }

        private fun setSearchIconView() {
            iconView.setImageResource(R.drawable.ic_search_16_0)
        }

        private fun setBackIconView() {
            iconView.setImageResource(R.drawable.ic_left_arrow_16_0)
        }

        fun onInputTextChanged(s: Editable?) {
            setBackIconView()
            if (s != null) {
                val text = s.toString()
                if (text.equals("")) {
                    closeIconView.visibility = View.INVISIBLE
                }else{
                    closeIconView.visibility = View.VISIBLE
                }
            }
        }
    }
}