/*
 * Copyright (c) 2019 FishWaffle.
 */

package com.fishwaffle.natureremo.taskerplugin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.StringRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

const val BUNDLE_NOTIFICATION_ID = "com.fishwaffle.natureremo.NOTIFICATION_ID"

enum class Channel(val id: String, @StringRes val title: Int) {
    SUCCESS("success", R.string.SendSuccess), ERROR("error", R.string.SendError)
}

fun createChannel(context: Context, channel: Channel) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(channel.id, context.getString(channel.title), NotificationManager.IMPORTANCE_HIGH)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}

fun showSendSuccessNotification(context: Context, content: String) {
    val channel = Channel.SUCCESS
    createChannel(context, channel)
    val mBuilder = NotificationCompat.Builder(context, channel.id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(channel.title))
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

    with(NotificationManagerCompat.from(context)) {
        notify(createID(), mBuilder.build())
    }
}

fun showSendErrorNotification(context: Context, content: String, intent: Intent) {
    val extraId = intent.getIntExtra(BUNDLE_NOTIFICATION_ID, -1)
    val id = if (extraId == -1) {
        val id = createID()
        intent.putExtra(BUNDLE_NOTIFICATION_ID, id)
        id
    } else {
        extraId
    }

    val channel = Channel.ERROR
    val pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_ONE_SHOT)
    createChannel(context, channel)
    val mBuilder = NotificationCompat.Builder(context, channel.id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(channel.title))
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(0, "再実行",
                    pendingIntent)
    with(NotificationManagerCompat.from(context)) {
        notify(id, mBuilder.build())
    }
}

fun showToSettingNotification(context: Context, content: String) {
    val intent = Intent(context, MainActivity::class.java)

    val id = createID()
    val channel = Channel.ERROR
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    createChannel(context, channel)
    val mBuilder = NotificationCompat.Builder(context, channel.id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(context.getString(channel.title))
            .setContentText("認証に失敗しました。:$content")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .addAction(0, "設定",
                    pendingIntent)
    with(NotificationManagerCompat.from(context)) {
        notify(id, mBuilder.build())
    }
}

fun createID(): Int = Date().time.toInt()
