package vn.com.tma.vo_ngoc_hanh.mychat.base.validator

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import java.lang.ref.WeakReference
import java.util.*

abstract class BaseValidator(protected val error:String) : Observable(), IValidator, TextWatcher {
    protected var isValidated = false
    protected var weakRefEditText: WeakReference<EditText>?= null

    override fun addView(view: View) {
        if (view is EditText) {
            val edt = view as EditText
            weakRefEditText = WeakReference(edt)
            edt.addTextChangedListener(this)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        isValidated = validate(s?.toString()?:"")
    }

    abstract fun validate(content: String) : Boolean

    override fun isValidate(): Boolean {
        return isValidated
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // do nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // do nothing
    }

    protected fun showError() {
        val edt = weakRefEditText?.get()
        if (edt != null) {
            edt.error = error
        }
        updateObservers()
    }

    protected fun hideError() {
        val edt = weakRefEditText?.get()
        if (edt != null) {
            edt.error = null
        }
        updateObservers()
    }

    protected open fun updateObservers() {
        setChanged()
        notifyObservers()
    }
}