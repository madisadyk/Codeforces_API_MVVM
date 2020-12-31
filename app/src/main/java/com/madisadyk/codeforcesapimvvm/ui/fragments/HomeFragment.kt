package com.madisadyk.codeforcesapimvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.ui.CFViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: CFViewModel
//    lateinit var

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}