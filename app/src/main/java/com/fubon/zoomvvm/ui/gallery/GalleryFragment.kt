package com.fubon.zoomvvm.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fubon.zoomvvm.R
import com.fubon.zoomvvm.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    private val args: GalleryFragmentArgs by navArgs()
    private var viewBinding: FragmentGalleryBinding? = null
    private lateinit var adapter: GalleryAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (viewBinding == null) {
            galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
            viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
            initView()

            galleryViewModel.getArgs(args)
            galleryViewModel.homeData.observe(this, Observer {

                viewBinding?.data = it

            })

            galleryViewModel.plantData.observe(this, Observer {
                progressBar.visibility = View.GONE
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            })

            galleryViewModel.questPlant()
        }
        return viewBinding?.root
    }

    private fun initView() {
        viewBinding?.let {
            recyclerView = it.recyclerView1
            progressBar = it.progressBar
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = GalleryAdapter()
        recyclerView.adapter = adapter

        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }
}