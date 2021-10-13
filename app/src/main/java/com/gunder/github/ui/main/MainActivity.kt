package com.gunder.github.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gunder.github.R
import com.gunder.github.data.model.User
import com.gunder.github.databinding.ActivityMainBinding
import com.gunder.github.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {
    //    binding
    private lateinit var binding: ActivityMainBinding

    //    viewModel
    private lateinit var viewModel: UserViewModel

    //    adapter recycelerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        adapter.setOnItemClickCallBack(object : UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserViewModel::class.java)
//        set recycelerView
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
//      btnListener
            btnSearch.setOnClickListener {
                searchUser()
            }
//            tbl enter di keybaord
            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
        viewModel.getSearch().observe(this, {
            if (it != null) {
                adapter.setListUser(it)
                showLoading(false)
            }
        })
    }

    //    progressbar
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    //    search user
    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearch(query)
        }
    }
}