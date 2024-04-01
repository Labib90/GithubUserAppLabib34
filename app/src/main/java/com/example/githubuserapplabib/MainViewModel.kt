package com.example.githubuserapplabib

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.query
import com.example.githubuserapplabib.api.ClientRetrofit
import com.example.githubuserapplabib.detail.UserDetailViewModel
import com.example.githubuserapplabib.model.ResponseUser
import com.example.githubuserapplabib.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainViewModel : ViewModel() {
    private val _listUsers = MutableLiveData<ArrayList<User>>()
    val listUsers : LiveData<ArrayList<User>> = _listUsers
    init {
        setSearchUsers(USERNAME)
    }


    fun setSearchUsers(query: String){
            ClientRetrofit.getApiService()
                .getSearchUsers(query)
                .enqueue(object : Callback<ResponseUser> {
                    override fun onResponse(
                        call: Call<ResponseUser>,
                        response: Response<ResponseUser>
                    ) {
                        if (response.isSuccessful){
                            _listUsers.postValue(response.body()?.items)
                        }
                    }

                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                        Log.d("Failure", t.message.toString())
                    }
                })
        }
    companion object{
        private const val USERNAME = "Labib"
    }

}