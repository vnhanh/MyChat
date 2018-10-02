package vn.com.tma.vo_ngoc_hanh.mychat.common.validator

import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.BaseValidator
import java.util.*

class ConfirmPasswordValidator(error:String)  : BaseValidator(error), Observer {
    val TAG_CONFIRM_PASSWORD = "TAG_CONFIRM_PASSWORD"
    var password:String = ""
    private var confirmPassword:String = ""

    override fun validate(content: String): Boolean {
        confirmPassword = content
        if (password.equals(content)) {
            hideError()
            return true
        }else{
            showError()
            return true
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg is String) {
            password = arg
            validate(confirmPassword)

//            val edt =  weakRefEditText?.get()
//            if (edt != null) {
//                afterTextChanged(edt.text)
//            }
        }
    }
}