package com.example.githubuserapplabib.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapplabib.api.ClientRetrofit
import com.example.githubuserapplabib.model.ResponseUserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel : ViewModel(){
    private val _user = MutableLiveData<ResponseUserDetail>()
    val user:LiveData<ResponseUserDetail> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setUserDetail(username: String?){
        _isLoading.value = true
        ClientRetrofit.getApiService()
            .getUserDetail(username)
            .enqueue(object: Callback<ResponseUserDetail>{
                override fun onResponse(
                    call: Call<ResponseUserDetail>,
                    response: Response<ResponseUserDetail>
                ) {
                    _isLoading.value = false
                    if( response.isSuccessful){
                        _user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ResponseUserDetail>, t: Throwable) {
                    _isLoading.value = false
                    Log.d("Failure", t.message.toString())
                }

            })
    }


}