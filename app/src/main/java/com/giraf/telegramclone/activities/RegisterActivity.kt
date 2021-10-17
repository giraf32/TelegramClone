package com.giraf.telegramclone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.giraf.telegramclone.R
import com.giraf.telegramclone.databinding.ActivityRegisterBinding
import com.giraf.telegramclone.ui.fragment.EnterPhoneNumberFragment
import com.giraf.telegramclone.utility.initFirebase
import com.giraf.telegramclone.utility.replaceFragment

class RegisterActivity : AppCompatActivity() {
   private lateinit var mBinding: ActivityRegisterBinding
   private lateinit var mToolbar:androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
        
        
    }
    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
       replaceFragment(EnterPhoneNumberFragment(),false)
    }
}