package vn.com.tma.vo_ngoc_hanh.mychat.screen.login


import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_login.*

import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.base.validator.ValidatorManager
import vn.com.tma.vo_ngoc_hanh.mychat.common.android_architecture.ViewModelFactory
import vn.com.tma.vo_ngoc_hanh.mychat.databinding.FragmentLoginBinding
import java.lang.Exception

class LoginFragment : Fragment() {

    private lateinit var viewmodel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            viewmodel = ViewModelFactory.getInstance(activity!!.application).create(LoginViewModel::class.java)
            viewmodel.onCreate()
        }else{
            throw Exception("Not found activity contain this fragment")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)

        binding.viewmodel = viewmodel
        binding.validationManager = ValidatorManager()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_link_signup.setOnClickListener{
            it.findNavController().navigate(R.id.nav_loginToRegister)
        }

        viewmodel.signinResult.observe(this, Observer { isSigninSuccess ->
            Log.d("LOG", "viewmodel - signinResult: " + isSigninSuccess)
            if (isSigninSuccess != null && isSigninSuccess) {
                Navigation.findNavController(btn_login).navigate(R.id.nav_loginToHome)
            }
        })
    }
}
