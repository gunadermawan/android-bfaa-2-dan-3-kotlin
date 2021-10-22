package com.gunder.github.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteUser::class],
    version = 1
)
abstract class UserDb : RoomDatabase() {
    companion object {
        private var INSTANCE: UserDb? = null

        fun getInstanceDb(context: Context): UserDb? {
            if (INSTANCE == null) {
                synchronized(UserDb::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserDb::class.java,
                        "userDatabase"
                    ).build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun FavoriteDao(): FavoriteDao
}