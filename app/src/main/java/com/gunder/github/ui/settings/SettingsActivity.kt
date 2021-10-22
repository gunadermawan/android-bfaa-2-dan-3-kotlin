package com.gunder.github.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunder.github.data.model.Reminder
import com.gunder.github.data.preferences.ReminderPreferences
import com.gunder.github.databinding.ActivitySettingsBinding
import com.gunder.github.receiver.AlarmReminder

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminder: Reminder
    private lateinit var alarmReminder: AlarmReminder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = "Settings"
            setDisplayHomeAsUpEnabled(true)
        }

        val reminderPreferences = ReminderPreferences(this)
        binding.switchReminder.isChecked = reminderPreferences.getReminder().isReminder
        alarmReminder = AlarmReminder()
        binding.switchReminder.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReminder.setRepeatAlarm(this, "RepeatingAlarm ", "04:53", "Github Reminder")
            } else {
                saveReminder(false)
                alarmReminder.alarmCancel(this)
            }
        }
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreferences = ReminderPreferences(this)
        reminder = Reminder()
        reminder.isReminder = state
        reminderPreferences.setReminder(reminder)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}