package vn.com.tma.vo_ngoc_hanh.mychat.common.adapter

import android.databinding.BindingAdapter
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Spinner
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.IValidator
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.ValidatorFactory
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.ValidatorManager

class BindingAdapter {
    companion object {
        @BindingAdapter("app:bindValidator")
        @JvmStatic
        fun bindValidator(view: View, validator: IValidator) {
            validator.addView(view)
        }

        @BindingAdapter("bindValidatorManager", "validatorTag", "error")
        @JvmStatic
        fun addValidator(view: View, manager: ValidatorManager, validatorTag:String, error:String) {
            val newValidator = ValidatorFactory.getInstance(view.resources).get(validatorTag, error)
            newValidator.addView(view)
            manager.addNewValidator(newValidator)
        }

        @BindingAdapter("app:checkEnable")
        @JvmStatic
        fun checkEnable(view: View, enable:Boolean) {
            view.isEnabled = enable
        }
    }
}