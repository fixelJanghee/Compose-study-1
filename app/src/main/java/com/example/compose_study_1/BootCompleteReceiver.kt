package com.example.compose_study_1

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf

class BootCompleteReceiver : BroadcastReceiver() {
    private val TAG = this.javaClass.simpleName

    override fun onReceive(context: Context, intent: Intent?) {
        Log.e(TAG, "onReceive: ljhtest - boot receiver")
//        context.startActivity(Intent(context, MainActivity::class.java).apply {
//            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        })
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setNotification(context)
        } else {
            context.startService(Intent(context, StarterService::class.java))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setNotification(context: Context) {
        Log.e(TAG, "setNotification: ljhtest")
        // Android 13 권한 확인
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PERMISSION_DENIED) {
                Toast.makeText(context, "알림 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val channel = NotificationChannel(CHANNEL_ID, "start", NotificationManager.IMPORTANCE_DEFAULT)
        val notiManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notiManager.createNotificationChannel(channel)


        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("시작")
            .setContentText("StarterService..")
            .setAutoCancel(true)
            .setVibrate(null)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()


        context.startForegroundService(Intent(context, StarterService::class.java).apply {
            bundleOf(
                Pair("test", notification)
            )
        })
    }


    companion object {
        const val CHANNEL_ID = "starter"
    }
}