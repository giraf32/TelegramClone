package com.giraf.telegramclone

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.giraf.telegramclone.activities.RegisterActivity
import com.giraf.telegramclone.databinding.ActivityMainBinding
import com.giraf.telegramclone.models.User
import com.giraf.telegramclone.ui.fragment.ChatsFragment
import com.giraf.telegramclone.ui.objects.AppDrawer
import com.giraf.telegramclone.utility.*
import com.theartofdev.edmodo.cropper.CropImage

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar
    
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
        initUser{
            initFields()
            initFunc()
        }
        APP_ACTIVITY = this
        
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(),false)
        } else{
            replaceActivity(RegisterActivity())
           
        }
    
    }
    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this,mToolbar)
      
    }
    
    override fun onStart() {
        AppStates.updateState(AppStates.ONLINE)
        super.onStart()
    }
    
    override fun onStop() {
        AppStates.updateState(AppStates.OFFLINE)
        super.onStop()
    }
}