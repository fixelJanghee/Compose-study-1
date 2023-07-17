package com.example.compose_study_1

import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.os.UserManager
import android.util.Log

class DeviceAdminSimple: DeviceAdminReceiver() {
    private val TAG = this.javaClass.simpleName


    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        Log.e(TAG, "onReceive: ljhtest - " + intent.action)
    }

    override fun onEnabled(context: Context, intent: Intent) {
        super.onEnabled(context, intent)
        Log.e(TAG, "onEnabled: ljhtest - " + intent.action)
        val componentName = ComponentName(context, this::class.java)
//        getManager(context).addUserRestriction(componentName, UserManager.DISALLOW_BLUETOOTH_SHARING)
    }

    override fun onDisableRequested(context: Context, intent: Intent): CharSequence? {
        Log.e(TAG, "onDisableRequested: ljhtest - " + intent.action)
        return super.onDisableRequested(context, intent)
    }

    override fun onDisabled(context: Context, intent: Intent) {
        super.onDisabled(context, intent)
        Log.e(TAG, "onDisabled: ljhtest - " + intent.action)
    }

    override fun onPasswordChanged(context: Context, intent: Intent, user: UserHandle) {
        super.onPasswordChanged(context, intent, user)
        Log.e(TAG, "onPasswordChanged: ljhtest - " + intent.action)
    }

    override fun onProfileProvisioningComplete(context: Context, intent: Intent) {
        super.onProfileProvisioningComplete(context, intent)
        Log.e(TAG, "onProfileProvisioningComplete: ljhtest - " + intent.action)
    }


}