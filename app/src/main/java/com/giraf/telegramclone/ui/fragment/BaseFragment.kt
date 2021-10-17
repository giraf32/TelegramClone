package com.giraf.telegramclone.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.giraf.telegramclone.MainActivity
import com.giraf.telegramclone.utility.hideKeyboard


open class BaseFragment(layout: Int) : Fragment(layout) {
   
    
    override
    fun onStart() {
        super.onStart()
        hideKeyboard()
        (activity as MainActivity).mAppDrawer.disableDrawer()
    }
    
    override fun onStop() {
        super.onStop()
        (activity as MainActivity).mAppDrawer.enableDrawer()
    }
}