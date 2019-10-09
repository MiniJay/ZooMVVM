package com.fubon.zoomvvm.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fubon.zoomvvm.R
import com.fubon.zoomvvm.databinding.ItemListPlantBinding
import tw.ddt.ddt_zoo.model.plant.PlantRebuildModel
import tw.ddt.ddt_zoo.model.plant.PlantResults


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {
    private var plantModel: PlantRebuildModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: ItemListPlantBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_plant, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        plantModel?.let {
            return it.result.count
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        plantModel?.let {
            holder.bind(it.result.results[position])
        }
    }

    fun setData(plantModel: PlantRebuildModel?) {
        this.plantModel = plantModel
    }

    class ViewHolder(private val view: ItemListPlantBinding) : RecyclerView.ViewHolder(view.root){

        fun bind(result: PlantResults) {
            view.data = result
        }
    }
}