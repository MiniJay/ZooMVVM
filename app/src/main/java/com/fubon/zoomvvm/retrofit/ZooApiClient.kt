package com.fubon.zoomvvm.retrofit

import com.fubon.zoomvvm.retrofit.model.HomeModel
import io.reactivex.Observable
import tw.ddt.ddt_zoo.model.plant.PlantModel

class ZooApiClient {
    companion object {
        fun getCategoryResult(): Observable<HomeModel> {
            return ServiceFactory.getZooService().getCategory().compose(ObservableUtils.schedulersHandling())
        }

        fun getPlantResult(): Observable<PlantModel> {
            return ServiceFactory.getZooService().getPlant().compose(ObservableUtils.schedulersHandling())
        }
    }
}