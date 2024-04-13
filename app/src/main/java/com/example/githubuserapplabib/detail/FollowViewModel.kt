package com.example.githubuserapplabib.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapplabib.api.ClientRetrofit
import com.example.githubuserapplabib.model.ResponseUser
import com.example.githubuserapplabib.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel : ViewModel() {
    private val _listFollow = MutableLiveData<java.util.ArrayList<User>>()
    val listFollow : LiveData<java.util.ArrayList<User>> = _listFollow

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getFollowers(query: String){
        _isLoading.value = true
        ClientRetrofit.getApiService()
            .getFollowers(query)
            .enqueue(object : Callback<java.util.ArrayList<User>> {
                override fun onResponse(
                    call: Call<java.util.ArrayList<User>>,
                    response: Response<java.util.ArrayList<User>>
                ) {
                    _isLoading.value = false
                   if(response != null){
                       _listFollow.value = response.body()
                   }
                }

                override fun onFailure(call: Call<java.util.ArrayList<User>>, t: Throwable) {
                    _isLoading.value = false
                }

            })
    }

    fun getFollowing(query: String){
        _isLoading.value = true
        ClientRetrofit.getApiService()
            .getFollowing(query)
            .enqueue(object : Callback<java.util.ArrayList<User>> {
                override fun onResponse(
                    call: Call<java.util.ArrayList<User>>,
                    response: Response<java.util.ArrayList<User>>
                ) {
                    _isLoading.value = false
                    if(response != null){
                        _listFollow.value = response.body()
                    }
                }

                override fun onFailure(call: Call<java.util.ArrayList<User>>, t: Throwable) {
                    _isLoading.value = false

                }

            })
    }

    companion object{
        private const val USERNAME = "Labib"
    }
}

