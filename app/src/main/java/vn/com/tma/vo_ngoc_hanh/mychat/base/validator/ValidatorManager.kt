package vn.com.tma.vo_ngoc_hanh.mychat.base.validator

import android.databinding.ObservableBoolean
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.ConfirmPasswordValidator
import vn.com.tma.vo_ngoc_hanh.mychat.common.validator.PasswordValidator
import java.util.*

open class ValidatorManager : Observer {
    private val validators = ArrayList<IValidator>()
    var isValidate = ObservableBoolean(false)
    var hasConfirmPassword = false

    fun addNewValidator(newValidator: BaseValidator) {
        if (hasConfirmPassword) {
            combinePasswordsValidatorIfExist(newValidator)
        }

        validators.add(newValidator)
        newValidator.addObserver(this)
    }

    private fun combinePasswordsValidatorIfExist(newValidator: BaseValidator) {
        if (newValidator is ConfirmPasswordValidator) {
            val confirmPasswordValidator = newValidator as ConfirmPasswordValidator
            for (i in 0..(validators.size - 1)) {
                val validator = validators.get(i)
                if (validator is PasswordValidator) {
                    (validator as PasswordValidator).addObserver(confirmPasswordValidator)
                    return
                }
            }
        }else if (newValidator is PasswordValidator) {
            val passwordValidator = newValidator as PasswordValidator
            for (i in 0..(validators.size - 1)) {
                val validator = validators.get(i)
                if (validator is ConfirmPasswordValidator) {
                    passwordValidator.addObserver(validator as ConfirmPasswordValidator)
                    return
                }
            }
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        isValidate.set(checkValidate())
    }

    private fun checkValidate() : Boolean {
        for (i in 0..validators.size - 1) {
            if(!validators.get(i).isValidate())
                return false
        }
        return true
    }
}