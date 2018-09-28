package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import java.lang.ref.WeakReference

class FullNameValidator(private var error:String) : IValidator, TextWatcher {
    private var weakRefTextInputLayout:WeakReference<TextInputLayout> ?= null
    private var weakRefEditText:WeakReference<EditText> ?= null

    override fun addView(view: View) {
        if (view is TextInputLayout) {
            weakRefTextInputLayout = WeakReference(view as TextInputLayout)
        } else if (view is EditText) {
            val edt = view as EditText
            weakRefEditText = WeakReference(edt)
            edt.addTextChangedListener(this)
        }
    }

    override fun afterTextChanged(s: Editable?) {

        val til = weakRefTextInputLayout?.get()
        if (til != null) {
            val str = s?.toString()?:""
            if (str.length < 2) {
                til.isErrorEnabled = true
                til.error = error
            }else{
                til.isErrorEnabled = false
                til.error = null
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // do nothing
    }

}