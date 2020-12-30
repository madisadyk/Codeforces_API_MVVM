package com.madisadyk.codeforcesapimvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.db.HandlerDatabase
import com.madisadyk.codeforcesapimvvm.repository.CFRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: CFViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = CFRepository(HandlerDatabase(this))
        val viewModelProviderFactory = CFViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(CFViewModel::class.java)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
    }
}