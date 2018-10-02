package vn.com.tma.vo_ngoc_hanh.mychat.base.validator

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import java.lang.ref.WeakReference
import java.util.*

abstract class BaseValidator(protected val error:String) : Observable(), IValidator, TextWatcher {
    protected var validate = false
    protected var weakRefTextInputLayout: WeakReference<TextInputLayout>?= null
    protected var weakRefEditText: WeakReference<EditText>?= null

    override fun addView(view: View) {
        if (view is TextInputLayout) {
            val til = view as TextInputLayout
            weakRefTextInputLayout = WeakReference(til)

            val childView = (til.getChildAt(0) as ViewGroup).getChildAt(0)
            if (childView is EditText) {
                val edt = childView as EditText
                weakRefEditText = WeakReference(edt)
                edt.addTextChangedListener(this)
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        val til = weakRefTextInputLayout?.get()
        if (til != null) {
            validate = validate(s?.toString()?:"")
        }
    }

    abstract fun validate(content: String) : Boolean

    override fun isValidate(): Boolean {
        return validate
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // do nothing
    }

    protected fun showError() {
        val til = weakRefTextInputLayout?.get()
        if (til != null) {
            til.isErrorEnabled = true
            til.error = error
        }
        updateObservers()
    }

    protected fun hideError() {
        val til = weakRefTextInputLayout?.get()
        if (til != null) {
            til.isErrorEnabled = false
        }
        updateObservers()
    }

    protected open fun updateObservers() {
        setChanged()
        notifyObservers()
    }
}