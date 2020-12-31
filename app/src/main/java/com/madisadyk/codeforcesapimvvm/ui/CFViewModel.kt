package com.madisadyk.codeforcesapimvvm.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madisadyk.codeforcesapimvvm.models.ContestsResponse
import com.madisadyk.codeforcesapimvvm.models.HandleResponse
import com.madisadyk.codeforcesapimvvm.repository.CFRepository
import com.madisadyk.codeforcesapimvvm.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CFViewModel(
        val repository: CFRepository
) : ViewModel() {

    val contests: MutableLiveData<Resource<ContestsResponse>> = MutableLiveData()
    val search: MutableLiveData<Resource<HandleResponse>> = MutableLiveData()

    init {
//        getContests()
    }

    fun getContests() = viewModelScope.launch {
        contests.postValue(Resource.Loading())
        val response = repository.getContests()
        contests.postValue(handleContestsResponse(response))
    }

    fun getHandle(handle: String) = viewModelScope.launch {
        search.postValue(Resource.Loading())
        val response = repository.getHandler(handle)
        Log.e("BLYA", "tutnet")
        search.postValue(handleSearchResponse(response))
    }

    private fun handleContestsResponse(response: Response<ContestsResponse>) : Resource<ContestsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchResponse(response: Response<HandleResponse>) : Resource<HandleResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}