package com.gunder.github.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert
//    suspen fun untk ekesekusi di background
    suspend fun addToFavorite(favoriteUser: FavoriteUser)

    //    manampilkan list user favorite
    @Query("SELECT * FROM favoriteUser")
    fun getFavoriteUser(): LiveData<List<FavoriteUser>>

    //    menampilkan user yg di favoritkan
    @Query("SELECT count(*) FROM favoriteUser WHERE favoriteUser.id = :id")
    suspend fun checkedUser(id: Int): Int

    //    menghapus user dari list favorite
    @Query("DELETE FROM favoriteUser WHERE favoriteUser.id = :id")
    suspend fun deleteFromFavorite(id: Int): Int
}