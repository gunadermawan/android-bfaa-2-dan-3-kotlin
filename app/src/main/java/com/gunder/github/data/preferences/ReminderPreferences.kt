package com.gunder.github.data.preferences

import android.content.Context
import com.gunder.github.data.model.Reminder

class ReminderPreferences(context: Context) {
    companion object {
        const val PREFS_NAME = "reminder"
        const val REMINDER = "isReminder"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    //    set reminder
    fun setReminder(value: Reminder) {
        val editor = preferences.edit()
        editor.putBoolean(REMINDER, value.isReminder)
        editor.apply()
    }

    //    get reminder
    fun getReminder(): Reminder {
        val model = Reminder()
        model.isReminder = preferences.getBoolean(REMINDER, false)
        return model
    }
}