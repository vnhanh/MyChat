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

       /* val adapter = LoginAdapter()
        rv_names.adapter = adapter
        rv_names.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_names.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.getAccounts()?.observe(this, Observer {
            if (it != null) {
                adapter.addList(it)
            }else{
                adapter.addList(ArrayList<Account>())
            }
        })

        btn_submit.setOnClickListener{_ ->
            viewModel.addName(edt_name.text.toString())
        }*/
    }
}
