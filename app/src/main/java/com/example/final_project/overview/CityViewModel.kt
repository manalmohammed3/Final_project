package com.example.final_project.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.network.ResultsItem
import com.example.final_project.network.cityApi
import kotlinx.coroutines.launch
import java.lang.Exception


var gener = 0

enum class cityApiStatus { LOADING, ERROR, DONE }

class CityViewModel : ViewModel() {

    private val _status = MutableLiveData<cityApiStatus>()
    val status: LiveData<cityApiStatus> = _status

    val cityId = MutableLiveData<Int>()

    val cityTitle = MutableLiveData<String>()

    val cityDetail = MutableLiveData<String>()

    val cityPoster = MutableLiveData<String>()


    var cityGener: MutableList<List<Int>?> = mutableListOf()

    private val _cityInfo = MutableLiveData<List<ResultsItem?>?>()
    val cityInfo: LiveData<List<ResultsItem?>?> = _cityInfo

    private val _cityList = MutableLiveData<List<ResultsItem?>>()
    val cityList: LiveData<List<ResultsItem?>> = _cityList

    init {
        getCityList()
    }

    fun getCityList(type: String = "upcoming") {
        viewModelScope.launch {
            _status.value = cityApiStatus.LOADING
            try {
                val listResult = cityApi.retrofitService.getCityList(type).results
                _cityInfo.value = listResult
                _status.value = cityApiStatus.DONE
            } catch (e: Exception) {
                _status.value = cityApiStatus.ERROR
                _cityInfo.value = listOf()
            }
        }
    }
}