package com.example.githubuserapplabib.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapplabib.api.ClientRetrofit
import com.example.githubuserapplabib.local.FavoriteUser
import com.example.githubuserapplabib.local.FavoriteUserDao
import com.example.githubuserapplabib.local.UserDatabase
import com.example.githubuserapplabib.model.ResponseUserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailViewModel(application: Application) : AndroidViewModel(application){
    private val _user = MutableLiveData<ResponseUserDetail>()
    val user:LiveData<ResponseUserDetail> = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }
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

    fun addToFavorite(username: String, id: Int){
        CoroutineScope(Dispatchers.IO).launch{
            var user = FavoriteUser(
                username,
                id
            )
            userDao?.addToFavorite(user)
        }
    }
suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFavorite(id)
        }
    }

}