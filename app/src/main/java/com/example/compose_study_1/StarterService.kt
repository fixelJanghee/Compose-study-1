package com.example.compose_study_1

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log

class StarterService : Service(), ServiceAction {
    private val TAG = this.javaClass.simpleName


    override fun onBind(intent: Intent?): IBinder? {
        Log.e(TAG, "onBind() called with: intent = $intent")
        return Binder()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand() called with: intent = $intent, flags = $flags, startId = $startId")
        intent?.let {
            val notification: Notification? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                it.getParcelableExtra("test", Notification::class.java)
            } else {
                it.getParcelableExtra("test")
            }

            notification ?: return@let

            startForeground(1, notification)
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate() called")
        startActivity(packageManager.getLaunchIntentForPackage(packageName))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy() called")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.e(TAG, "onUnbind() called with: intent = $intent")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.e(TAG, "onRebind() called with: intent = $intent")
        super.onRebind(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        Log.e(TAG, "onTaskRemoved() called with: rootIntent = $rootIntent")
        super.onTaskRemoved(rootIntent)
    }

    override fun close() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(STOP_FOREGROUND_DETACH)
        }
        stopSelf()
    }

}