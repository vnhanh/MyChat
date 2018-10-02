package vn.com.tma.vo_ngoc_hanh.mychat.screen.register


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_register.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.common.adapter.SpinnerStringAdapter
import vn.com.tma.vo_ngoc_hanh.mychat.base.android_architecture.ViewModelFactory
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.ValidatorManager
import vn.com.tma.vo_ngoc_hanh.mychat.databinding.FragmentRegisterBinding
import java.util.*
import kotlin.collections.ArrayList

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        val root = binding.root

        val account = Account("", true, Date(), "")
        binding.account = account

        val validationManager = ValidatorManager()
        validationManager.hasConfirmPassword = true
        binding.validationManager = validationManager

        binding.password = ""

        val application = activity?.application
        if (application != null) {
            val viewmodel = ViewModelFactory.getInstance(application).create(RegisterViewModel::class.java)
            viewmodel.isRegistered.observe(this, Observer<Boolean>{
                Log.d("LOG", "register")
                if (it!= null) {
                    if (it.equals(true)) {
                        Toast.makeText(context, "Register successfully!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "Register fail!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            binding.viewmodel = viewmodel
        }

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

            tv_link_signup.setOnClickListener{
                it.findNavController().navigate(R.id.nav_registerToLogin)
            }
        }
    }
}