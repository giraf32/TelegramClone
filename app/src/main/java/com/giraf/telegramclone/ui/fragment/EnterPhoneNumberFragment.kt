package com.giraf.telegramclone.ui.fragment

import androidx.fragment.app.Fragment
import com.giraf.telegramclone.MainActivity
import com.giraf.telegramclone.R
import com.giraf.telegramclone.activities.RegisterActivity
import com.giraf.telegramclone.utility.AUTH
import com.giraf.telegramclone.utility.replaceActivity
import com.giraf.telegramclone.utility.replaceFragment
import com.giraf.telegramclone.utility.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit


class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {
    private lateinit var mPhoneNumber: String
    private lateinit var mCallback : PhoneAuthProvider.OnVerificationStateChangedCallbacks
   
    override fun onStart() {
        super.onStart()
        
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener{
                    if (it.isSuccessful){
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(it.exception?.message.toString())
                }
            }
    
            override fun onCodeSent(id : String, token : PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
    
            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }
    
        }
      register_btn_next.setOnClickListener{(sendCode())}
    }
    fun sendCode(){
        if (register_input_phone_number.text.toString().isEmpty()){
           showToast(getString(R.string.reister_toast_phone))
        }else{
            authUser()
        }
    }
    
    private fun authUser() {
        mPhoneNumber = register_input_phone_number.text.toString()
      /*  PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        
        )*/
        val options = PhoneAuthOptions.newBuilder(AUTH)
            .setPhoneNumber(mPhoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity as RegisterActivity)                 // Activity (for callback binding)
            .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}