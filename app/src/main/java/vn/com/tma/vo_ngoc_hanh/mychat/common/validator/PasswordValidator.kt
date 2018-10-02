package vn.com.tma.vo_ngoc_hanh.mychat.common.validator

import android.util.Log
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.BaseValidator

class PasswordValidator(error:String) : BaseValidator(error) {
    override fun validate(content: String): Boolean {
        if (content.length > 4) {
            hideError()
            return true
        }else{
            showError()
            return false
        }
    }

    override fun updateObservers() {
        var password = ""
        val edt = weakRefEditText?.get()
        if (edt != null) {
            password = edt.text.toString()

        }
        setChanged()
        notifyObservers(password)
    }
}