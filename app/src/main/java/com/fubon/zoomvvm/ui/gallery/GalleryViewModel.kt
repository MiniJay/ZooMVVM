package com.fubon.zoomvvm.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fubon.zoomvvm.retrofit.ZooApiClient
import com.fubon.zoomvvm.retrofit.model.HomeModel
import io.reactivex.observers.DisposableObserver
import tw.ddt.ddt_zoo.model.plant.PlantModel
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults

class GalleryViewModel : ViewModel() {

    var homeData: MutableLiveData<HomeModel.Result.Results> = MutableLiveData()
    var plantData: MutableLiveData<PlantRebuildModel> = MutableLiveData()

    fun getArgs(args: GalleryFragmentArgs) {
        homeData.value = args.result
    }

    fun questPlant() {
        ZooApiClient.getPlantResult().subscribe(object : DisposableObserver<PlantModel>(){
            override fun onComplete() {

            }

            override fun onNext(t: PlantModel) {
                var plantRebuildModel = PlantRebuildModel()
                t?.let {
                    plantRebuildModel.result.count = t.result.count

                    val results = t.result.results
                    var rebuildObject: PlantResults? = null
                    for (i in 0 until results.size) {
                        val source = results[i]

                        if (!source.F_Name_En.isNullOrEmpty()) {
                            if (rebuildObject != null) {
                                val insertData = rebuildObject
                                plantRebuildModel.result.results.add(insertData)
                            }
                            rebuildObject = PlantResults()

                            rebuildObject.F_Pic01_URL = source.F_Pic01_URL.toString()
                            rebuildObject.F_Name_Ch = source.F_Name_Ch.toString()
                            rebuildObject.F_Name_En = source.F_Name_En.toString()
                            rebuildObject.F_AlsoKnown = source.F_AlsoKnown.toString()
                            rebuildObject.F_Brief = source.F_Brief.toString()
                            rebuildObject.F_Feature = source.F_Feature.toString()
                            rebuildObject.F_Function = source.F_Function.toString()
                            rebuildObject.F_Update = source.F_Update.toString()
                        } else {
                            source.F_AlsoKnown?.let {
                                if (it.startsWith("http")) {
                                    rebuildObject?.F_Pic01_URL = it
                                }
                            }

                            source.F_Pic04_URL?.let {
                                rebuildObject?.F_Update = it
                            }

                            source.F_Name_Ch.let {
                                rebuildObject?.F_Function = rebuildObject?.F_Function + "\n" + it
                            }
                        }
                    }
                }

                plantData.value = plantRebuildModel
            }

            override fun onError(e: Throwable) {

            }
        })
    }
}