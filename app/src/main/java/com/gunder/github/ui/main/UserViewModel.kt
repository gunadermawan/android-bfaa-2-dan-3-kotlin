package com.gunder.github.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.gunder.github.api.RetrofitClient
import com.gunder.github.data.model.User
import com.gunder.github.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {
    val listUserViewModel = MutableLiveData<ArrayList<User>>()

    fun setSearch(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUserViewModel.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("fail", t.message!!)
                }

            })
    }

    fun getSearch(): LiveData<ArrayList<User>> {
        return listUserViewModel
    }

}