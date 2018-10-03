package vn.com.tma.vo_ngoc_hanh.mychat.screen.register


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_register.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.base.custom_view.DialogCreater
import vn.com.tma.vo_ngoc_hanh.mychat.common.adapter.SpinnerStringAdapter
import vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture.ViewModelFactory
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.ValidatorManager
import vn.com.tma.vo_ngoc_hanh.mychat.databinding.FragmentRegisterBinding
import java.util.*
import kotlin.collections.ArrayList

class RegisterFragment : Fragment() {

    private lateinit var viewmodel:RegisterViewModel

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
            viewmodel = ViewModelFactory.getInstance(application).create(RegisterViewModel::class.java)
            setupObservation()
            binding.viewmodel = viewmodel
        }

        return root
    }

    private fun setupObservation() {
        observeRegisterResult()
        observeLoadingProgress()
    }

    private val prgMsg:String? by lazy { context?.getString(R.string.msg_registering) }
    private val prgDialog:AlertDialog? by lazy { DialogCreater.create(context, "", prgMsg?:"", false) }

    private fun observeLoadingProgress() {
        viewmodel.isLoading.observe(this, Observer<Boolean>{
            when (it) {
                true -> {
                    if (prgDialog != null) {
                        if (!prgDialog!!.isShowing) {
                            prgDialog!!.show()
                        }
                    }
                }

                false -> {
                    if (prgDialog != null) {
                        if (prgDialog!!.isShowing) {
                            prgDialog!!.dismiss()
                        }
                    }
                }
            }
        })
    }

    private fun observeRegisterResult() {
        viewmodel.registerResult.observe(this, Observer<Boolean>{
            if (it!= null) {
                if (viewmodel.isRegistered) {
                    if (it.equals(true)) {
                        Toast.makeText(context, R.string.toast_register_success, Toast.LENGTH_SHORT).show()
                        val navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment )
                        navController.navigate(R.id.nav_registerToLogin)
                    }else{
                        Toast.makeText(context, R.string.toast_register_failed, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
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

            tv_link_signup.setOnClickListener{
                it.findNavController().navigate(R.id.nav_registerToLogin)
            }
        }
    }
}