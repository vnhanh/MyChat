package vn.com.tma.vo_ngoc_hanh.mychat.common.validator

import android.support.design.widget.TextInputLayout
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.BaseValidator

class FullNameValidator(error:String) : BaseValidator(error) {
    override fun validate(content: String): Boolean {

        if (content.length < 2) {
            showError()
            return false
        }else{
            hideError()
            return true
        }
    }
}