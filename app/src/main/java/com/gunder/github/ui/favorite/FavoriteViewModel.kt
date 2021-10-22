package com.gunder.github.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gunder.github.data.local.FavoriteDao
import com.gunder.github.data.local.FavoriteUser
import com.gunder.github.data.local.UserDb

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: FavoriteDao? = null
    private var userDb: UserDb? = null

    init {
        userDb = UserDb.getInstanceDb(application)
        userDao = userDb?.FavoriteDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}