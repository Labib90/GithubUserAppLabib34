package com.example.githubuserapplabib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubuserapplabib.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}