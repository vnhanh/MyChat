package vn.com.tma.vo_ngoc_hanh.mychat.screen

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import vn.com.tma.vo_ngoc_hanh.mychat.R
import vn.com.tma.vo_ngoc_hanh.mychat.screen.login.LoginViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
