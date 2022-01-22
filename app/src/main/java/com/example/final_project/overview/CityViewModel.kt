package com.example.final_project.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.final_project.data.CitiesItem
import com.google.firebase.database.*



var gener = 0

enum class cityApiStatus { LOADING, ERROR, DONE }
class CityViewModel : ViewModel() {

    //view the planing fragment
    private val _cityDetails = MutableLiveData<CitiesItem>()
    val cityDetails: LiveData<CitiesItem> = _cityDetails



    private val _status = MutableLiveData<cityApiStatus>()
    val status: LiveData<cityApiStatus> = _status



    private val _cityInfo = MutableLiveData<List<CitiesItem>>()
    val cityInfo: LiveData<List<CitiesItem>?> = _cityInfo

    private val _cityList = MutableLiveData<List<CitiesItem?>>()
    val cityList: LiveData<List<CitiesItem?>> = _cityList

    init {
        getData()
    }



    fun getData() {

        _status.value = cityApiStatus.LOADING
        val database1: DatabaseReference =
            FirebaseDatabase.getInstance("https://final-project-2cc60-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("data").child("cities")
        database1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("TAG", " jhjhjh: ${dataSnapshot}",)
                var cityList = mutableListOf<CitiesItem>()
                for (value in dataSnapshot.children) {
                    var item63 = value.getValue(CitiesItem::class.java)

                    Log.d("TAG", "onDataChange: ${item63?.cityName}")
                    cityList.add(item63!!)
                }
                _cityInfo.value = cityList

                _status.value = cityApiStatus.DONE
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("The read failed: " + databaseError.code)
                Log.e("TAG", "onDataChange: ${databaseError.code}",)
            }
        })
    }


    //view  second fragment
    fun getCitydetail(id: String) {
        for (city in cityInfo.value!!) {
            if (city.id == id) {
                _cityDetails.value = city
                Log.e("TAG", "details: ${ _cityDetails.value}",)

            }
        }
    }
}








