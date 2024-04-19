package com.example.githubuserapplabib.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapplabib.R
import com.example.githubuserapplabib.UserAdapter
import com.example.githubuserapplabib.databinding.ActivityFavoriteBinding
import com.example.githubuserapplabib.detail.UserDetailActivity
import com.example.githubuserapplabib.local.FavoriteUser
import com.example.githubuserapplabib.model.User

class ActivityFavorite : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@ActivityFavorite, UserDetailActivity::class.java).also {
                    it.putExtra(UserDetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(UserDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(UserDetailActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }
        })
        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@ActivityFavorite)
            rvUser.adapter = adapter
        }
        viewModel.getFavoriteUser()?.observe(this, {
            if(it!=null){
                val list = mapList(it)
                adapter.setList(list)
            }
        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
            for(user in users){
                val userMapped = User(
                    user.login,
                    user.id,
                    user.avatar_url
                )
                listUsers.add(userMapped)
            }
            return listUsers

    }
}