package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.R
import android.app.DatePickerDialog
import android.databinding.BindingAdapter
import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.room.AccountLocal
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

class BindingAdapterRegister {
    companion object {

        @BindingAdapter("app:bindClearInputState")
        @JvmStatic
        fun bindClearInputState(edt: EditText, isClear: Boolean) {
            edt.setText("")
        }

        @BindingAdapter("app:bindShowState")
        @JvmStatic
        fun bindShowState(view: View, isShow: Boolean) {
            view.visibility = if(isShow) View.VISIBLE else View.GONE
        }

        @BindingAdapter("app:bindEnableState")
        @JvmStatic
        fun bindEnableState(view: View, isEnable: Boolean) {
            view.isEnabled = isEnable
        }

        @BindingAdapter("app:bindAccountGender")
        @JvmStatic
        fun bindGenderForAccount(spinner: Spinner, account: AccountLocal) {
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> account.gender = true

                        1 -> account.gender = false
                    }
                }
            }
        }

        @BindingAdapter("app:bindBirthDate")
        @JvmStatic
        fun bindBirthDate(view: TextView, account: AccountLocal) {
            view.setOnClickListener{
                val context = view.context

                val calendar = Calendar.getInstance()

                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)

                val dialog = DatePickerDialog(context, R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener{
                    datePicker, _year, monthOfYear, dayOfMonth ->

                    val builder = StringBuilder()
                    if (dayOfMonth < 10) {
                        builder.append("0")
                    }
                    builder.append(dayOfMonth)

                    view.text = builder.append("/").append(monthOfYear+1).append("/").append(_year).toString()
                    view.setTextColor(Color.WHITE)

                    val dateStr = StringBuilder().append(dayOfMonth).append("/").append(monthOfYear+1).append("/").append(_year).toString()
                    val sdf = SimpleDateFormat("dd/MM/yyyy")
                    account.birthDate = sdf.parse(dateStr)

                }, year, month, day)

                dialog.show()
            }
        }


        @BindingAdapter("app:bindEnableStateForButton")
        @JvmStatic
        fun bindEnableStateForButton(view:View, enable:Boolean){
            view.alpha = if(enable) 1f else 0.7f
        }
    }
}