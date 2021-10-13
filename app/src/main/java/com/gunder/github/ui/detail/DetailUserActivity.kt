package com.gunder.github.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gunder.github.R
import com.gunder.github.data.model.DetailUserResponse
import com.gunder.github.databinding.ActivityDetailUserBinding
import com.gunder.github.ui.main.UserViewModel

class DetailUserActivity : AppCompatActivity() {
//    binding
    private lateinit var binding : ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user: DetailUserResponse? = null
        supportActionBar?.apply {
            title = user?.name
            setDisplayHomeAsUpEnabled(true)
        }

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = "@${it.login}"
                    tvFollowers.text = "${it.followers} followers"
                    tvFollowing.text = "${it.following} following"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfile)
                }
            }
        })
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}