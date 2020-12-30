package com.madisadyk.codeforcesapimvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madisadyk.codeforcesapimvvm.repository.CFRepository

class CFViewModelProviderFactory(
    val repository: CFRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CFViewModel(repository) as T
    }
}