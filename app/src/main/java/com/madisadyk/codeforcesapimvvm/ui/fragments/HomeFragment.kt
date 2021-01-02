package com.madisadyk.codeforcesapimvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.adapters.ContestAdapter
import com.madisadyk.codeforcesapimvvm.ui.CFViewModel
import com.madisadyk.codeforcesapimvvm.ui.MainActivity
import com.madisadyk.codeforcesapimvvm.util.Resource
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var viewModel: CFViewModel
    lateinit var contestAdapter: ContestAdapter

    override fun onViewCreated(view: View,savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.contests.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { contestResponse ->
                        contestAdapter.differ.submitList(contestResponse.result)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("HOME", "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        contestAdapter = ContestAdapter()
        rvHome.apply {
            adapter = contestAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}