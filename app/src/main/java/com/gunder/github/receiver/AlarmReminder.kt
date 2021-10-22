package com.gunder.github.receiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import com.gunder.github.R
import www.sanju.motiontoast.MotionToast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReminder : BroadcastReceiver() {
    companion object {
        private const val EXTRA_MESSAGE = "extra_type"
        private const val EXTRA_TYPE = "extra_message"
        private const val CHANNEL_ID = "channel_1"
        private const val REPEATING_ID = 101
        private const val CHANNEL_NAME = "Github Notification"
        private const val NOTIFICATION_ID = 1
        private const val FORMAT_TIME = "HH:mm"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        sendNotification(context)
    }

    private fun sendNotification(context: Context) {
        val intent = context.packageManager.getLaunchIntentForPackage("com.gunder.github")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
//        notification manageer
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notification_people)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Cari user favoritmu di github yuk \uD83D\uDE0D")
            .setAutoCancel(true)
//        set support android O
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(NotificationChannel)
        }
        val notification = builder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    //    rebuild alarm
    fun setRepeatAlarm(context: Context, type: String, time: String, msg: String) {
        if (isDateValid(time, FORMAT_TIME)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReminder::class.java)
        intent.putExtra(EXTRA_MESSAGE, msg)
        intent.putExtra(EXTRA_TYPE, type)
        val timeSplit = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeSplit[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeSplit[1]))
        calendar.set(Calendar.SECOND, 0)
        val pendingIntent = PendingIntent.getBroadcast(context, REPEATING_ID, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        MotionToast.createColorToast(
            context as Activity,
            "Yeay! \uD83D\uDE0D",
            "Reminder di aktifkan",
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular)
        )
    }

    private fun isDateValid(time: String, formatTime: String): Boolean {
        return try {
            val simpleDateFormat = SimpleDateFormat(formatTime, Locale.getDefault())
            simpleDateFormat.isLenient = false
            simpleDateFormat.parse(time)
            false
        } catch (e: ParseException) {
            true
        }
    }

    //    cancel alarm
    fun alarmCancel(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReminder::class.java)
        val requestCode = REPEATING_ID
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        MotionToast.createColorToast(
            context as Activity,
            "Yaah, \uD83D\uDE14",
            "Reminder di matikan",
            MotionToast.TOAST_WARNING,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.SHORT_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular)
        )
    }
}