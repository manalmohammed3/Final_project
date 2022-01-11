package com.example.final_project.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.CitiesItem
import com.example.final_project.network.cityApi
import com.google.firebase.database.*
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

    val cityImage = MutableLiveData<String>()

     var cityGener: MutableList<List<Int>?> = mutableListOf()

    private val _cityInfo = MutableLiveData<List<CitiesItem>>()
    val cityInfo: LiveData<List<CitiesItem>?> = _cityInfo

    private val _cityList = MutableLiveData<List<CitiesItem?>>()

      val cityList: LiveData<List<CitiesItem?>> = _cityList
    init {
        getData()
    }

//     init {
//     getcityList()
//   }
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
}


//    fun getcityList (index: Int) {
//        val item = _cityInfo.value?.get(index)
//        cityImage.value = item?.posterPath
//       // cityTitle.value = item?.originalTitle
//        cityDetail.value = item?.overview
//        //cityId.value = item?.id
//    }
//    }





