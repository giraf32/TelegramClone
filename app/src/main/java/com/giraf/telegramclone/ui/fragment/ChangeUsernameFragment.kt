package com.giraf.telegramclone.ui.fragment

import com.giraf.telegramclone.R
import com.giraf.telegramclone.utility.*
import kotlinx.android.synthetic.main.fragment_change_username.*
import java.util.*


class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {
    lateinit var mNewUsername :String
    
    
    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)
    }
  
    override fun change() {
    mNewUsername = settings_input_username.text.toString().toLowerCase(Locale.getDefault() )
        if (mNewUsername.isEmpty()){
            showToast("Поле пустое")
        }else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener{
                    if (it.hasChild(mNewUsername)){
                        showToast("Такой пользователь уже существует")
                    }else{
                        changeUsername()
                    }
                })
           
        }
    }
    
    private fun changeUsername() {
    REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(CURRENT_UID)
        .addOnCompleteListener{
            if (it.isSuccessful){
                updateCurrentUsername()
            }else{
                showToast(it.exception?.message.toString())
            }
        }
    }
    
    fun updateCurrentUsername(){
        REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_USERNAME)
            .setValue(mNewUsername)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    deleteOldUsername()
                }else{
                    showToast(it.exception?.message.toString())
                }
            }
    }
    fun deleteOldUsername(){
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(USER.username).removeValue()
            .addOnCompleteListener{
                if (it.isSuccessful){
                    showToast(getString(R.string.toast_data_update))
                    fragmentManager?.popBackStack()
                    USER.username = mNewUsername
                }else{
                    showToast(it.exception?.message.toString())
                }
            }
    }
}