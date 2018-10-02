package vn.com.tma.vo_ngoc_hanh.mychat.common.validator

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.util.Patterns
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.BaseValidator

class EmailValidator(error:String) : BaseValidator(error) {

    override fun validate(content: String): Boolean {
        if (content.equals("") || !Patterns.EMAIL_ADDRESS.matcher(content).matches()) {
            showError()
            return false
        }else{
            hideError()
            return true
        }
    }
}