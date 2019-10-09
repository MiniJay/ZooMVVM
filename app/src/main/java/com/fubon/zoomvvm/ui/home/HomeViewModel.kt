package com.fubon.zoomvvm.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fubon.zoomvvm.retrofit.ZooApiClient
import com.fubon.zoomvvm.retrofit.model.HomeModel
import io.reactivex.observers.DisposableObserver

class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    private val _data = MutableLiveData<HomeModel>().apply {
        value
    }
    var data: MutableLiveData<HomeModel> = MutableLiveData()

    fun requestData() {
        ZooApiClient.getCategoryResult().subscribe(object: DisposableObserver<HomeModel>(){

            override fun onComplete() {
                Log.d("API_Category", "onComplete")
            }

            override fun onNext(t: HomeModel) {
                Log.d("API_Category", "onNext")
                data.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("API_Category", "onError")
            }
        })
    }

    interface HomeListener {
        fun itemClick(model: HomeModel.Result.Results)
    }
}