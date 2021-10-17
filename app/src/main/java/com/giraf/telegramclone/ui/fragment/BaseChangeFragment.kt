package com.giraf.telegramclone.ui.fragment

import android.os.Bundle
import android.text.Layout
import android.view.*
import androidx.fragment.app.Fragment
import com.giraf.telegramclone.MainActivity
import com.giraf.telegramclone.R
import com.giraf.telegramclone.utility.APP_ACTIVITY
import com.giraf.telegramclone.utility.hideKeyboard
import com.mikepenz.materialize.util.KeyboardUtil.hideKeyboard

open class BaseChangeFragment(layout: Int) : Fragment(layout) {
    override fun onStart() {
        super.onStart()
        hideKeyboard()
        setHasOptionsMenu(true)
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }
    
    override fun onStop() {
        super.onStop()
       
    }
    
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        
        (activity as MainActivity).menuInflater.inflate(R.menu.settings_menu_confirm,menu)
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_confirm_change -> change()
            
        }
        return true
    }
    
    open fun change() {
    
    }
}