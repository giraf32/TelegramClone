package com.giraf.telegramclone.ui.fragment

import com.giraf.telegramclone.R
import com.giraf.telegramclone.utility.*
import kotlinx.android.synthetic.main.fragment_change_name.*


class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {
    
    override fun onResume() {
        super.onResume()
    
        initFullnamelist()
    }
    private fun initFullnamelist() {
        val fullNameList = USER.fullname.split(" ")
    
        if (fullNameList.size > 1) {
            settings_input_Name.setText(fullNameList[0])
            settings_input_serName.setText(fullNameList[1])
        } else settings_input_Name.setText(fullNameList[0])
    }
    
    override fun change() {
       val name : String = settings_input_Name.text.toString()
        val surName :String = settings_input_serName.text.toString()
        if (name.isEmpty()){
            showToast(getString(R.string.settings_toast_isEmpty))
        }else {
            val fullname = "$name $surName"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME)
                .setValue(fullname).addOnCompleteListener{
                    if (it.isSuccessful){
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname = fullname
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                        fragmentManager?.popBackStack()
                        
                    }
                }
        }
        
        
    }
}