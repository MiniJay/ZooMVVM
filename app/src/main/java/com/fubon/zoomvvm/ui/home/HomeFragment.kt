package com.fubon.zoomvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fubon.zoomvvm.R
import com.fubon.zoomvvm.retrofit.model.HomeModel

class HomeFragment : Fragment(), HomeViewModel.HomeListener {
    override fun itemClick(model: HomeModel.Result.Results) {
        activity?.findNavController(R.id.nav_host_fragment)?.navigate(HomeFragmentDirections.actionNavHomeToNavGallery(model))
//        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.nav_gallery)
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var root: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (root == null) {
            homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            root = inflater.inflate(R.layout.fragment_home, container, false)
            initView(root)
            homeViewModel.data.observe(this, Observer {
                progressBar.visibility = View.GONE

                adapter.setData(it)
                adapter.notifyDataSetChanged()
            })

            homeViewModel.requestData()
        }

        return root
    }

    private fun initView(root: View?) {
        root?.let {
            recyclerView = it.findViewById(R.id.recyclerView1)
            progressBar = it.findViewById(R.id.progressBar)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HomeAdapter(this)
        recyclerView.adapter = adapter
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)
    }

}