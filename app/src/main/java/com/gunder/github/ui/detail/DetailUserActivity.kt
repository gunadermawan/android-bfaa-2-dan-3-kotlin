package com.gunder.github.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunder.github.R
import com.gunder.github.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
//    binding
    private lateinit var binding : ActivityDetailUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}