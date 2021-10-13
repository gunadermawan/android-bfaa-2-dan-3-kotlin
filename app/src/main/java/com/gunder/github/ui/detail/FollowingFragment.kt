package com.gunder.github.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gunder.github.R
import com.gunder.github.databinding.FragmentFolloweBinding

class FollowingFragment: Fragment(R.layout.fragment_followe) {
    private var _binding : FragmentFolloweBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFolloweBinding.bind(view)
        _binding = FragmentFolloweBinding.bind(view)
    }
}