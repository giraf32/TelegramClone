package com.giraf.telegramclone.utility

import android.content.pm.PackageManager
import android.os.Build
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.jar.Manifest

const val READ_CONTACTS = android.Manifest.permission.READ_CONTACTS
const val PERMISSION_REQUEST = 200

fun checkPermission(permission : String):Boolean{
   return if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(APP_ACTIVITY,permission)!= PackageManager.PERMISSION_GRANTED){
        ActivityCompat.requestPermissions(APP_ACTIVITY, arrayOf(permission), PERMISSION_REQUEST)
         false
    }else true
}