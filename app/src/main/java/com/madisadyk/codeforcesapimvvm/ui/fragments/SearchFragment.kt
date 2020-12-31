package com.madisadyk.codeforcesapimvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.madisadyk.codeforcesapimvvm.R
import com.madisadyk.codeforcesapimvvm.adapters.HandleAdapter
import com.madisadyk.codeforcesapimvvm.ui.CFViewModel
import com.madisadyk.codeforcesapimvvm.ui.MainActivity
import com.madisadyk.codeforcesapimvvm.util.Constants.Companion.SEARCH_TIME_DELAY
import com.madisadyk.codeforcesapimvvm.util.Resource
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    lateinit var viewModel: CFViewModel
    lateinit var handleAdapter: HandleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getHandle(editable.toString())
                    }
                }
            }
        }

        viewModel.search.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { handleResponse ->
                        handleAdapter.differ.submitList(handleResponse.result)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("SEARCH", "An error occured: $message")
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
        handleAdapter = HandleAdapter()
        rvSearch.apply {
            adapter = handleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}