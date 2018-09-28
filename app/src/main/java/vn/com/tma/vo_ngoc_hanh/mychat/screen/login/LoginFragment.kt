package vn.com.tma.vo_ngoc_hanh.mychat.screen.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

import vn.com.tma.vo_ngoc_hanh.mychat.R

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_link_signup.setOnClickListener{
            it.findNavController().navigate(R.id.nav_loginToRegister)
        }
        btn_login.setOnClickListener{
            it.findNavController().navigate(R.id.nav_loginToHome)
        }
    }

}
