package vn.com.tma.vo_ngoc_hanh.mychat.screen.register


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_register.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.base.adapter.SpinnerStringAdapter
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.databinding.FragmentRegisterBinding
import java.util.*
import kotlin.collections.ArrayList

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        val root = binding.root
        binding.account = Account("", true, Date(), "")
        binding.fullnameValidator = FullNameValidator("Fullname is required!")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (context != null) {
            val _genders = resources.getStringArray(R.array.genders)
            val genders = ArrayList<String>()
            for (i in 0..(_genders.size - 1)) {
                genders.add(_genders[i])
            }
            val gendersAdapter = SpinnerStringAdapter(context!!, R.layout.item_gender, R.layout.item_gender, genders)
            gendersAdapter.setDropDownViewResource(R.layout.item_gender)
            sp_gender.adapter = gendersAdapter

            tv_birthdate.setOnClickListener(DatePickerDialogListener(context!!, tv_birthdate))


        }
    }
}