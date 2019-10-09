package com.fubon.zoomvvm

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import tw.ddt.ddt_zoo.GlideApp

object MyBindingAdapter {
    @BindingAdapter("profileImage")
    @JvmStatic
    fun loadImage(view: ImageView, imgUrl: String) {
        GlideApp.with(view.context)
            .load(imgUrl)
            .placeholder(R.drawable.ic_loading)
            .into(view)
    }

    @BindingAdapter("fromHtml")
    @JvmStatic
    fun setFromHtml(view: TextView, url: String) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            view.text = Html.fromHtml("<a href=\"${url}\">在網頁開啟</a>", Html.FROM_HTML_MODE_LEGACY)
        } else {
            view.text = Html.fromHtml("<a href=\"${url}\">在網頁開啟</a>")
        }
        view.movementMethod = LinkMovementMethod.getInstance()
    }
}