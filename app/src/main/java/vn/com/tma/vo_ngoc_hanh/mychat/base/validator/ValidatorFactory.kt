package vn.com.tma.vo_ngoc_hanh.mychat.base.validator

import android.content.res.Resources

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.ConfirmPasswordValidator
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.EmailValidator
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.FullNameValidator
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.PasswordValidator

class ValidatorFactory(res: Resources) {

    init {
        FULLNAME = res.getString(R.string.tag_val_fullname)
        EMAIL = res.getString(R.string.tag_val_email)
        PHONE = res.getString(R.string.tag_val_phone)
        PASSWORD = res.getString(R.string.tag_val_password)
        CONFIRM_PASSWORD = res.getString(R.string.tag_val_confirm_password)
    }

    companion object {

        lateinit var FULLNAME:String
        lateinit var EMAIL:String
        lateinit var PHONE:String
        lateinit var PASSWORD:String
        lateinit var  CONFIRM_PASSWORD:String

        var INSTANCE:ValidatorFactory?=null

        fun getInstance(res: Resources) : ValidatorFactory {
            if (INSTANCE == null) {
                INSTANCE = ValidatorFactory(res)
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    fun get(tag: String, error:String): BaseValidator {
        when (tag) {
            FULLNAME ->
                return FullNameValidator(error)

            EMAIL ->
                return EmailValidator(error)

            PASSWORD ->
                return PasswordValidator(error)

            else ->
                return ConfirmPasswordValidator(error)
        }
    }
}