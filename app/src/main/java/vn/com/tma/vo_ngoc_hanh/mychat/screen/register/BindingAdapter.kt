package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.databinding.BindingAdapter
import android.view.View

class BindingAdapter {
    companion object {
        @BindingAdapter("app:bindValidator")
        @JvmStatic
        fun bindValidator(view: View, validator: IValidator) {
            validator.addView(view)
        }
    }
}