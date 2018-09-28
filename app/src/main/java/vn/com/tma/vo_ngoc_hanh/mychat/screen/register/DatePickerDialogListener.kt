package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.app.DatePickerDialog
import android.content.Context
import android.view.View
import android.widget.TextView
import java.lang.StringBuilder
import java.lang.ref.WeakReference
import java.util.*

class DatePickerDialogListener : View.OnClickListener {
    private lateinit var weakRefContext:WeakReference<Context>
    private lateinit var weakRefTextView:WeakReference<TextView>

    constructor(context:Context, textView:TextView){
        weakRefContext = WeakReference(context)
        weakRefTextView = WeakReference(textView)
    }

    override fun onClick(v: View?) {
        val context = weakRefContext.get()
        val tv = weakRefTextView.get()

        if (context != null && tv != null) {
            val calendar = Calendar.getInstance()

            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            val dialog = DatePickerDialog(context,android.R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener{
                datePicker, _year, monthOfYear, dayOfMonth ->

                tv.text = StringBuilder().append(dayOfMonth).append("/").append(monthOfYear+1).append("/").append(_year).toString()
            }, year, month, day)

            dialog.show()
        }
    }
}