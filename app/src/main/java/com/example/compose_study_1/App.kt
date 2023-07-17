package com.example.compose_study_1

import android.app.Application
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.util.Log

class App : Application() {
    private val TAG = this.javaClass.simpleName

    override fun onCreate() {
        super.onCreate()

        Log.e(TAG, "ljhtest onCreate() called")
        val dpm = getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        val componentName = ComponentName(this, DeviceAdminSimple::class.java)
        val buffer = StringBuffer()
        buffer.appendLine("ljhtest")
            .appendLine("profile owner: ${dpm.isProfileOwnerApp(packageName)}")
            .appendLine("device owner: ${dpm.isDeviceOwnerApp(packageName)}")
            .appendLine("admin active: ${dpm.isAdminActive(componentName)}")

        Log.e(TAG, buffer.toString())
    }
}