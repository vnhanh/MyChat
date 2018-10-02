package vn.com.tma.vo_ngoc_hanh.mychat.screen.register

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import vn.com.tma.vo_ngoc_hanh.mychat.base.db.account.Account

class RegisterViewModel(app:Application) : AndroidViewModel(app) {
    private val auth = FirebaseAuth.getInstance()
    lateinit var isRegistered:MutableLiveData<Boolean>

    init{
        isRegistered = MutableLiveData<Boolean>()
        isRegistered.value = false
    }

    fun onSubmit(account: Account, password:String) {
        Log.d("LOG", "password: " + password)
        auth.createUserWithEmailAndPassword(account.email, password)
                .addOnCompleteListener{task ->
                    Log.d("LOG", "register is: " + task.isSuccessful)
                    isRegistered.value = task.isSuccessful
                }.addOnFailureListener{e ->
                    Log.d("LOG", "register fail - exception: " + e.message)
                    isRegistered.value = false
                }
    }
}