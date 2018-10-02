package vn.com.tma.vo_ngoc_hanh.mychat.base.validator

import android.view.View

interface IValidator {
    fun addView(view:View)

    fun isValidate() : Boolean
}