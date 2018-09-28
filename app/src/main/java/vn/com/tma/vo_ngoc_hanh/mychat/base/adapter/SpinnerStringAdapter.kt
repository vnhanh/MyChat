package vn.com.tma.vo_ngoc_hanh.mychat.base.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class SpinnerStringAdapter(context: Context, layoutId:Int, private var dropdownLayoutId:Int, private var list:List<String>) : ArrayAdapter<String>(context, layoutId) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): String {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tv = super.getView(position, convertView, parent) as TextView

        tv.text = list.get(position)

        return tv
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tv = super.getView(position, convertView, parent) as TextView

        tv.text = list.get(position)

        return tv
    }
}