package com.giraf.telegramclone.ui.fragment

import androidx.fragment.app.Fragment
import com.giraf.telegramclone.MainActivity
import com.giraf.telegramclone.R
import com.giraf.telegramclone.activities.RegisterActivity
import com.giraf.telegramclone.models.User
import com.giraf.telegramclone.utility.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val phoneNumber: String,val id: String) : Fragment(R.layout.fragment_enter_code) {
    
    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code_number.addTextChangedListener( AppTextWatcher{
            val string = register_input_code_number.text.toString()
                if (string.length == 6){
                    enterCode()
                }
        })
    }
    
    private fun enterCode() {
      val code: String = register_input_code_number.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener{task ->
            if (task.isSuccessful){
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String,Any>()
                dateMap [CHILD_ID] = uid
                dateMap [CHILD_PHONE] = phoneNumber
                dateMap [CHILD_USERNAME] = uid
                
                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener{task2 ->
                        if (task2.isSuccessful){
                            showToast("Добро пожаловать")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        }else showToast(task2.exception?.message.toString())
                    }
                
               
            } else showToast(task.exception?.message.toString())
        }
    }
}